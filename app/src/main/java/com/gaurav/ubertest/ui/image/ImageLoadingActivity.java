package com.gaurav.ubertest.ui.image;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import com.gaurav.ubertest.R;
import com.gaurav.ubertest.base.BaseMvpActivity;
import com.gaurav.ubertest.loading.model.PhotoModel;
import com.gaurav.ubertest.usecase.UseCaseProviderFactory;
import com.gaurav.ubertest.util.Debouncer;
import java.util.List;

/**
 * Created by gauravn on 05/07/18.
 */
public class ImageLoadingActivity extends BaseMvpActivity<ImageLoadingPresenter> implements
    ImageLoadingActivityContract.View, LoadMoreCallback {

  public static final int MIN_CHAR_TO_SEARCH = 3;
  public static final int SEARCH_DELAY = 500;
  public static final int GRID_SIZE = 3;
  private RecyclerView photoGrid;
  private EditText searchField;
  private ProgressBar progressBar;
  private ImageRecyclerAdapter imageRecyclerAdapter;
  private Debouncer debouncer = new Debouncer();
  private String currentKey;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_image);
    photoGrid = findViewById(R.id.photo_recycler);
    searchField = findViewById(R.id.edt_search);
    progressBar = findViewById(R.id.progress);
    imageRecyclerAdapter = new ImageRecyclerAdapter(null, this, this);
    photoGrid.setLayoutManager(new GridLayoutManager(this, GRID_SIZE));
    photoGrid.setAdapter(imageRecyclerAdapter);

    searchField.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(
          final CharSequence charSequence,
          final int i,
          final int i1,
          final int i2) {

      }

      @Override public void onTextChanged(
          final CharSequence charSequence,
          final int i,
          final int i1,
          final int i2) {

        if (charSequence.length() >= MIN_CHAR_TO_SEARCH) {
          currentKey = charSequence.toString();
          imageRecyclerAdapter.setPhotoModels(null); // new valid search term, clear current data
          debouncer.debounce(() -> presenter.search(currentKey, 1), SEARCH_DELAY);
        }
      }

      @Override public void afterTextChanged(final Editable editable) {

      }
    });
  }

  @NonNull
  @Override
  public ImageLoadingPresenter createPresenter() {
    return presenter = new ImageLoadingPresenter(
        this,
        UseCaseProviderFactory.getUseCase(UseCaseProviderFactory.UseCases.ImageFetch)
    );
  }

  @Override public void onDataLoaded(final List<PhotoModel> data) {
    imageRecyclerAdapter.setPhotoModels(data);
  }

  @Override public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgress() {
    progressBar.setVisibility(View.GONE);
  }

  @Override public void showError(final String error) {
    super.showError(error);
  }

  @Override public void loadMore(int pageNo) {
    presenter.search(currentKey, pageNo);
  }
}

interface LoadMoreCallback {
  void loadMore(int pageNo);
}