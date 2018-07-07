package com.gaurav.ubertest.loading.request;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.annotation.NonNull;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by gauravn on 04/07/18.
 */

public class FlickrRequest extends Request<Bitmap> {

  public FlickrRequest(
      @NonNull final String url,
      @NonNull final Request.Callback<Bitmap> callback,
      @NonNull final Handler handler) {
    super(url, Priority.NORMAL, callback, handler);
  }

  @Override public void download() {
    try {
      URL urlConn = new URL(this.url);
      HttpURLConnection connection = (HttpURLConnection) urlConn.openConnection();
      decode(connection.getInputStream());
    } catch (IOException e) {
      dataCallback.error(e.getMessage());
      e.printStackTrace();
    }
  }

  @Override public void decode(InputStream stream) {
    BufferedInputStream bufferedInputStream = new BufferedInputStream(stream);
    final Bitmap bmp = BitmapFactory.decodeStream(bufferedInputStream);
    dataCallback.onDataLoaded(bmp, url);
  }

  @Override public Priority getPriority() {
    return this.priority;
  }

}
