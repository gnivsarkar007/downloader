package com.gaurav.ubertest.loading.request;

import android.os.Handler;
import com.gaurav.ubertest.util.HandlerProvider;

/**
 * Created by gauravn on 04/07/18.
 */

public class RequestCreator {

  private Handler mainThreadHandler;

  public RequestCreator(final HandlerProvider handlerProvider) {
    this.mainThreadHandler = handlerProvider.provideHandler();
  }

  public Request createRequest(
          final Source source, final String url,
          Request.Callback callback) {
    switch (source) {
      case DATA:
        return new JSONRequest(url, Request.Priority.NORMAL, callback, mainThreadHandler);
      case FLICKR:
      default:
        return new FlickrRequest(
            url,
            callback, mainThreadHandler
        );

    }
  }

  public enum Source {
    FLICKR,
    DATA;
  }
}
