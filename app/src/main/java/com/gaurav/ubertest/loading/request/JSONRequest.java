package com.gaurav.ubertest.loading.request;

import android.os.Handler;
import android.support.annotation.NonNull;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gauravn on 05/07/18.
 */

public class JSONRequest extends Request {
  public JSONRequest(
      @NonNull final String url,
      @NonNull final Priority priority,
      @NonNull final Callback dataCallback,
      @NonNull final Handler handler) {
    super(url, priority, dataCallback, handler);
  }

  @Override public void download() {
    try {
      URL urlConn = new URL(this.url);
      HttpURLConnection connection = (HttpURLConnection) urlConn.openConnection();

      decode(connection.getInputStream());
    } catch (IOException e) {
      mainThreadHandler.postAtFrontOfQueue(() -> dataCallback.error(e.getMessage()));
      e.printStackTrace();
    }
  }

  @Override public void decode(final InputStream inputStream) {
    try {
      BufferedReader
          streamReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
      StringBuilder responseStrBuilder = new StringBuilder();

      String inputStr;
      while( (inputStr = streamReader.readLine()) != null ) {
        responseStrBuilder.append(inputStr);
      }

      JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
      mainThreadHandler.postAtFrontOfQueue(() -> dataCallback.onDataLoaded(jsonObject, this.url));
    } catch (IOException | JSONException e) {
      mainThreadHandler.postAtFrontOfQueue(() -> dataCallback.error(e.getMessage()));
      e.printStackTrace();
    }
  }

  @Override public Priority getPriority() {
    return priority;
  }
}
