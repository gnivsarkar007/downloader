package com.gaurav.ubertest.base;

/**
 * Created by gauravn on 07/07/18.
 */

public interface Repository<P, T> {
  void get(P params, Callback<T> callback);

  interface Callback<R> {
    void onDataLoaded(R data);

    void onError(String err);
  }
}
