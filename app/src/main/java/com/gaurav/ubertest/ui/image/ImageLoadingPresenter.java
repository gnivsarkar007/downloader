package com.gaurav.ubertest.ui.image;

import android.util.Pair;
import com.gaurav.ubertest.base.BasePresenterImpl;
import com.gaurav.ubertest.base.UseCase;
import com.gaurav.ubertest.loading.model.PhotoModel;
import java.util.List;

/**
 * Created by gauravn on 05/07/18.
 */

public class ImageLoadingPresenter extends BasePresenterImpl<ImageLoadingActivityContract.View>
    implements ImageLoadingActivityContract.Presenter {

  private UseCase<Pair<String, Integer>, List<PhotoModel>> imageFetchUseCase;

  public ImageLoadingPresenter(
      final ImageLoadingActivityContract.View view,
      UseCase<Pair<String, Integer>, List<PhotoModel>> imageFetchUseCase) {
    super(view);
    this.imageFetchUseCase = imageFetchUseCase;
  }

  @Override public void unsubscribe() {

  }

  @Override public void search(final String key, final int pageno) {
    if (view != null) {
      view.showProgress();
    }
    imageFetchUseCase.execute(new Pair<>(key, pageno), new UseCase.Callback<List<PhotoModel>>() {
      @Override public void onResponse(final List<PhotoModel> response) {
        if (view != null) {
          view.onDataLoaded(response);
          view.hideProgress();
        }
      }

      @Override public void onError(final String error) {
        if (view != null) {
          view.showError(error);
          view.hideProgress();
        }
      }
    });
  }
}
