package com.gaurav.ubertest.loading.download;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import com.gaurav.ubertest.loading.request.Request;
import com.gaurav.ubertest.loading.request.RequestCreator;
import com.gaurav.ubertest.util.HandlerProvider;
import java.util.concurrent.ExecutorService;
import org.json.JSONObject;

/**
 * Created by gauravn on 04/07/18.
 */

public class DataDownloader extends Downloader<JSONObject> {
  private static DataDownloader instance;

  @VisibleForTesting
  public DataDownloader(ExecutorService service) {
    super(service);
  }

  @Override public HandlerProvider setHandlerProvider(HandlerProvider provider) {
    return handlerProvider = provider;
  }

  public static DataDownloader get() {
    if (instance == null) {
      synchronized (DataDownloader.class) {
        if (instance == null) {
          instance = new DataDownloader(DownloaderExecutor.get());
          instance.handlerProvider = new
              HandlerProvider.MainThreadHandlerProvider();
        }
      }
    }
    return instance;
  }

  /**
   * Method to intitiate the loading of a data resource.
   *
   * @param url the url to  be loaded
   * @param source source of the data
   * @param targetCallback callback to return the data in
   */
  public void loadData(
      @NonNull final String url,
      @NonNull final RequestCreator.Source source,
      final Request.Callback<JSONObject> targetCallback) {

    submit(createRequest(url, source, targetCallback));
  }

  @Override public Request<JSONObject> createRequest(
      @NonNull final String url,
      @NonNull final RequestCreator.Source source,
      final Request.Callback<JSONObject> targetCallback) {

    return new RequestCreator(handlerProvider).createRequest(
        source,
        url, targetCallback
    );
  }
}
