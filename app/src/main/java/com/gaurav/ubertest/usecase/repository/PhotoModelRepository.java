package com.gaurav.ubertest.usecase.repository;

import android.util.Log;
import android.util.Pair;
import com.gaurav.ubertest.base.Repository;
import com.gaurav.ubertest.loading.download.DataDownloader;
import com.gaurav.ubertest.loading.request.Request;
import com.gaurav.ubertest.loading.request.RequestCreator;
import org.json.JSONObject;

/**
 * Created by gauravn on 07/07/18.
 */

public class PhotoModelRepository implements Repository<Pair<String, Integer>, JSONObject> {

  public static final int PAGE_SIZE = 10;
  private String url = "https://api.flickr.com/services/rest/"
      + "?method=flickr.photos.search&api_key=3e7cc266ae2b0e0d78e279ce8e361736&\n"
      + "format=json&nojsoncallback=1&safe_search=1&per_page=%d&page=%s&text=%s";

  public PhotoModelRepository() {
  }

  @Override public void get(Pair<String, Integer> params, Callback<JSONObject> callback) {
    DataDownloader
        .get()
        .loadData(String.format(url, PAGE_SIZE, params.second, params.first),
            RequestCreator.Source.DATA, new Request.Callback<JSONObject>() {
              @Override public void onDataLoaded(final JSONObject data, final String url) {
                callback.onDataLoaded(data);
              }

              @Override public void error(final String errMsg) {
                callback.onError(errMsg);
              }
            }
        );

    Log.d("DOWNLOAD", "URL:::" + String.format(url, PAGE_SIZE, params.second, params.first));
  }
}
