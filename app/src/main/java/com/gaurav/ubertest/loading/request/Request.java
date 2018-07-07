package com.gaurav.ubertest.loading.request;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import java.io.InputStream;

/**
 * Created by gauravn on 04/07/18.
 */

public abstract class Request<T> implements Runnable {

  protected String url;
  protected Handler mainThreadHandler;
  protected Priority priority;
  protected Callback<T> dataCallback;

  public Request(
      @NonNull final String url,
      @NonNull final Priority priority,
      @NonNull final Callback<T> dataCallback,
      @NonNull final Handler handler) {
    this.url = url;
    this.priority = priority;
    this.dataCallback = dataCallback;
    this.mainThreadHandler = handler;
  }

  public String getUrl() {
    return url;
  }

  @Override public void run() {
    download();
  }

  @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
  public abstract void download();

  @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
  public abstract void decode(InputStream inputStream);



  public abstract Priority getPriority();

  public enum Priority {
    LOW,
    NORMAL,
    HIGH
  }

  public interface Callback<T> {
    void onDataLoaded(T data, String url);

    void error(String errMsg);
  }
}
