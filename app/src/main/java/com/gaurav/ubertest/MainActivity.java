package com.gaurav.ubertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ImageView img = findViewById(R.id.image);
//    HashMap<String, String> map = new HashMap<>();
//    map.put(FlickrRequest.FARM, "1");
//    map.put(FlickrRequest.SERVER, "839");
//    map.put(FlickrRequest.ID, "42480969234");
//    map.put(FlickrRequest.SECRET, "4843c53cbb");


    ImageView img2 = findViewById(R.id.image2);
//    HashMap<String, String> map2 = new HashMap<>();
//    map2.put(FlickrRequest.FARM, "1");
//    map2.put(FlickrRequest.SERVER, "839");
//    map2.put(FlickrRequest.ID, "43199167721");
//    map2.put(FlickrRequest.SECRET, "1bdf4d5d53");
    //    DataDownloader
//        .get()
//        .createRequest(FlickrRequest.BASEURL,
//            RequestCreator.Source.FLICKR,
//            map,
//            new WeakReference<ImageView>(img),
//            null
//        );
    //    DataDownloader
//        .get()
//        .createRequest(FlickrRequest.BASEURL,
//            RequestCreator.Source.FLICKR,
//            map2,
//            new WeakReference<ImageView>(img2),
//            null
//        );
//
    //    DataDownloader.get().createRequest("https://api.flickr.com/services/rest/"
//            + "?method=flickr.photos.search&api_key=3e7cc266ae2b0e0d78e279ce8e361736&\n"
//            + "format=json&nojsoncallback=1&safe_search=1&text=kittens",
//        RequestCreator.Source.DATA,
//        null,
//        null,
//        (data, url) -> {assert data != null;}
//    );//per_page=10&page=2

  }
}
