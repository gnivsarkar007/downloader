package com.gaurav.ubertest.ui.image;

import com.gaurav.ubertest.base.BaseMvpPresenter;
import com.gaurav.ubertest.base.MvpView;
import com.gaurav.ubertest.loading.model.PhotoModel;
import java.util.List;

/**
 * Created by gauravn on 05/07/18.
 */

public interface ImageLoadingActivityContract {

  interface Presenter extends BaseMvpPresenter {
    void search(String key, int pageno);
  }

  interface View extends MvpView {
    void onDataLoaded(List<PhotoModel> data);

    void showProgress();

    void hideProgress();
  }
}
