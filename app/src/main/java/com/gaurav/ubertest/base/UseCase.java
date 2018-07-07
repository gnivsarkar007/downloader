package com.gaurav.ubertest.base;

/**
 * Created by gauravn on 05/07/18.
 */

public interface UseCase<REQUEST, RESPONSE> {
  void execute(REQUEST request, Callback<RESPONSE> callback);

  interface Callback<R> {
    void onResponse(R response);

    void onError(String error);
  }
}
