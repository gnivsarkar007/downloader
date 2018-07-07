package com.gaurav.ubertest.loading.parser;

import org.json.JSONObject;

/**
 * Created by gauravn on 07/07/18.
 */

public interface Parser<T> {
  T parse();

  void setRootObject(JSONObject rootObject);
}
