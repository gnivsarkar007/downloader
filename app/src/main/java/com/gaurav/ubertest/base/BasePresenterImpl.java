package com.gaurav.ubertest.base;

/**
 * Created by gauravn on 05/07/18.
 */

public abstract class BasePresenterImpl<V extends MvpView> implements BaseMvpPresenter {

  protected V view;

  public BasePresenterImpl(final V view) {
    this.view = view;
  }
}
