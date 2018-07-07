package com.gaurav.ubertest.util;

import android.os.Handler;
import android.os.Looper;

/**
 * Created by gauravn on 07/07/18.
 */

public interface HandlerProvider {
  Handler provideHandler();

  class MainThreadHandlerProvider implements HandlerProvider {
    private Handler handler;

    @Override public Handler provideHandler() {
      if (handler != null) {
        return handler;
      }
      return handler = new Handler(Looper.getMainLooper());
    }
  }
}
