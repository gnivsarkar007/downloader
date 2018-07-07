package com.gaurav.ubertest.util;

/**
 * Created by gauravn on 06/07/18.
 */

import android.os.Handler;

public class Debouncer {

  private Handler handler = null;

  public Debouncer() {
    this.handler = new android.os.Handler();
  }

  public void reset() {
    // Remove any previous runnable callbacks
    this.handler.removeCallbacksAndMessages(null);
  }

  public void debounce(Runnable callback, int delay) {
    reset();
    this.handler.postDelayed(callback, delay);
  }
}
