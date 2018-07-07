package com.gaurav.ubertest.loading.download;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.gaurav.ubertest.loading.request.Request;
import com.gaurav.ubertest.loading.request.RequestCreator;
import com.gaurav.ubertest.util.HandlerProvider;
import java.util.concurrent.ExecutorService;

/**
 * Created by gauravn on 07/07/18.
 */

abstract class Downloader<T> {
  ExecutorService downloadExecutorService;
  HandlerProvider handlerProvider;

  Downloader(final ExecutorService downloadExecutorService) {
    this.downloadExecutorService = downloadExecutorService;
  }

  public abstract HandlerProvider setHandlerProvider(HandlerProvider provider);

  public HandlerProvider getHandlerProvider() {
    return handlerProvider;
  }

  @VisibleForTesting(otherwise = VisibleForTesting.PROTECTED)
  public void submit(Request request) {
    downloadExecutorService.submit(request);
  }

  protected abstract Request<T> createRequest(
      @NonNull String url,
      @NonNull RequestCreator.Source source,
      Request.Callback<T> targetCallback);
}
