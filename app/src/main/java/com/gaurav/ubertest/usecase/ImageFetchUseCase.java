package com.gaurav.ubertest.usecase;

import android.support.annotation.VisibleForTesting;
import android.util.Pair;
import com.gaurav.ubertest.base.Repository;
import com.gaurav.ubertest.base.UseCase;
import com.gaurav.ubertest.loading.model.PhotoModel;
import com.gaurav.ubertest.loading.parser.Parser;
import java.util.List;
import org.json.JSONObject;

/**
 * Created by gauravn on 05/07/18.
 */

public class ImageFetchUseCase
    implements UseCase<Pair<String, Integer>, List<PhotoModel>> {

  private Parser<List<PhotoModel>> photoModelParser;
  private Repository<Pair<String, Integer>, JSONObject> repository;

  ImageFetchUseCase(Repository repository, Parser parser) {
    this.repository = repository;
    this.photoModelParser = parser;
  }

  @Override public void execute(
      final Pair<String, Integer> pair, final Callback<List<PhotoModel>> callback) {
    repository.get(pair, new Repository.Callback<JSONObject>() {
      @Override public void onDataLoaded(final JSONObject data) {
        setLoadedData(data, callback);
      }

      @Override public void onError(final String err) {
        callback.onError(err);
      }
    });
  }

  @VisibleForTesting
  private void setLoadedData(final JSONObject data, Callback<List<PhotoModel>> callback) {
    this.photoModelParser.setRootObject(data);
    callback.onResponse(photoModelParser.parse());
  }
}
