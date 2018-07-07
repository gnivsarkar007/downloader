package com.gaurav.ubertest.loading.parser;

import com.gaurav.ubertest.loading.model.PhotoModel;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gauravn on 05/07/18.
 */

public class PhotoModelParser implements Parser<List<PhotoModel>> {

  //{"id":"42309299885","owner":"165570135@N05",""
  //    + "secret":"5066e2cae3","server":"1827","farm":2,"title":"Good","ispublic":1,"isfriend":0,"isfamily":0}

  private JSONObject rootObject;

  public PhotoModelParser(JSONObject object) {
    this.rootObject = object;
  }

  public void setRootObject(final JSONObject rootObject) {
    this.rootObject = rootObject;
  }

  public final List<PhotoModel> parse() {
    List<PhotoModel> modelList = new ArrayList<>();
    try {
      JSONObject photoData = rootObject.getJSONObject("photos");
      JSONArray photosArray = (JSONArray) photoData.get("photo");
      for (int i = 0; i < photosArray.length(); i++) {
        JSONObject jsonObject = (JSONObject) photosArray.get(i);
        PhotoModel model = new PhotoModel();
        model.setFarm(jsonObject.getInt("farm"));
        model.setId(jsonObject.getString("id"));
        model.setSecret(jsonObject.getString("secret"));
        model.setServer(jsonObject.getString("server"));
        model.setTitle(jsonObject.getString("title"));
        modelList.add(model);
      }
    } catch (JSONException e) {
      e.printStackTrace();
    }
    //Log.d("TAG", "data size : "+modelList.size());
    return modelList;
  }
}
