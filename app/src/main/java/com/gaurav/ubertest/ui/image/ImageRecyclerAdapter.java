package com.gaurav.ubertest.ui.image;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.gaurav.ubertest.R;
import com.gaurav.ubertest.loading.download.ImageDownloader;
import com.gaurav.ubertest.loading.model.PhotoModel;
import com.gaurav.ubertest.usecase.repository.PhotoModelRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gauravn on 06/07/18.
 */

public class ImageRecyclerAdapter
    extends RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder> {

  private List<PhotoModel> photoModels = new ArrayList<>();
  private Context context;
  private LoadMoreCallback loadMoreCallback;

  public ImageRecyclerAdapter(
      final List<PhotoModel> photoModels,
      final Context context,
      final LoadMoreCallback loadMoreCallback) {
    if (photoModels != null) {
      this.photoModels.addAll(photoModels);
    }
    this.context = context;
    this.loadMoreCallback = loadMoreCallback;
  }

  public void setPhotoModels(final List<PhotoModel> photoModels) {
    if (photoModels == null) {
      this.photoModels.clear();
    } else {
      this.photoModels.addAll(photoModels);
    }
    notifyDataSetChanged();
  }

  public void swapData(final List<PhotoModel> photoModels) {
    this.photoModels.clear();
    if (photoModels != null) {
      this.photoModels.addAll(photoModels);
    }

    notifyDataSetChanged();
  }

  @NonNull @Override public ImageRecyclerAdapter.ImageViewHolder onCreateViewHolder(
      @NonNull final ViewGroup viewGroup, final int i) {
    View view = LayoutInflater.from(context).inflate(R.layout.image_item, null);
    return new ImageViewHolder(view);
  }

  @Override public void onBindViewHolder(
      @NonNull final ImageRecyclerAdapter.ImageViewHolder viewHolder,
      final int i) {
    PhotoModel model = photoModels.get(i);
    viewHolder.attachData(model);

    if (getItemCount() > 0 && i == getItemCount() - 2) {
      loadMoreCallback.loadMore(getItemCount() / PhotoModelRepository.PAGE_SIZE);
    }
  }

  @Override public int getItemCount() {
    return photoModels.size();
  }

  class ImageViewHolder extends RecyclerView.ViewHolder {

    private ImageView photo;
    private TextView title;

    public ImageViewHolder(@NonNull final View itemView) {
      super(itemView);
      photo = itemView.findViewById(R.id.image);
      title = itemView.findViewById(R.id.title);
    }

    public void attachData(PhotoModel model) {
      ImageDownloader.get().loadImage(photo,model.buildUrl());
      title.setText(model.getTitle());
      Log.d("DOWNLOAD", "URL:::" + model.buildUrl());

    }
  }
}
