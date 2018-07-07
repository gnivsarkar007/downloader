package com.gaurav.ubertest.loading.parser;

import com.gaurav.ubertest.loading.model.PhotoModel;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by gauravn on 07/07/18.
 */

public class PhotoModelParserTest {

  private static final String DUMMY_RESPONSE = "{\"photos\":{\"page\":3,\"pages\":10845,\""
      + "perpage\":10,\"total\":\"108450\",\"photo\":[{\"id\":\"29371977688\",\"owner\":"
      + "\"149561324@N03\",\"secret\":\"202499969f\",\"server\":\"834\",\"farm\":1,\""
      + "title\":\"Skedaddle has had acquisition talks with Uber and Lyft\",\"ispublic"
      + "\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"28372828527\",\"owner\":\"157787834@N08"
      + "\",\"secret\":\"50df2b840c\",\"server\":\"915\",\"farm\":1,\"title\":\"World's Largest "
      + "Sheet Music Selection\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\"id\":\"29370765658"
      + "\",\"owner\":\"134015846@N08\",\"secret\":\"1f75068831\",\"server\":\"845\",\"farm"
      + "\":1,\"title\":\"\\u2724347\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},"
      + "{\"id\":\"41430546110\",\"owner\":\"157787834@N08\",\"secret\":\"706df56cbe\",\"server\""
      + ":\"1828\",\"farm\":2,\"title\":\"SHEET MUSIC 163741352503 vn\",\"ispublic\":1,\"isfriend\""
      + ":0,\"isfamily\":0},{\"id\":\"43190882722\",\"owner\":\"57613855@N06\",\"secret\""
      + ":\"d1422ca1ff\",\"server\":\"1768\",\"farm\":2,\"title\":\"How an Uber Driver Became "
      + "THE RIDE SHARE GUY - Tactics in a Tesla\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},"
      + "{\"id\":\"42335606485\",\"owner\":\"137573462@N02\",\"secret\":\"f0b2668d9c\",\"server\":"
      + "\"1821\",\"farm\":2,\"title\":\"LOTD 442\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},"
      + "{\"id\":\"41428592660\",\"owner\":\"67082768@N03\",\"secret\":\"cb5de8ff2e\",\""
      + "server\":\"1808\",\"farm\":2,\"title\":\"n_day_1645\",\"ispublic\":1,\"isfriend\":0,\""
      + "isfamily\":0},{\"id\":\"43242059321\",\"owner\":\"55392804@N06\",\"secret\":\"222d6f5d61\","
      + "\"server\":\"1768\",\"farm\":2,\"title\":\"Uber de lujo con pasajeros de lujo "
      + "@giselahialita @marianioh @caro_brambila @rennluc\",\"ispublic\":1,\"isfriend\":0,"
      + "\"isfamily\":0},{\"id\":\"43192087142\",\"owner\":\"110947313@N02\",\"secret\":\""
      + "506ee6f9ce\",\"server\":\"1789\",\"farm\":2,\"title\":\"Some things never change, and some "
      + "feelings never go away.\",\"ispublic\":1,\"isfriend\":0,\"isfamily\":0},{\""
      + "id\":\"29370469388\",\"owner\":\"133565676@N02\",\"secret\":\"80927e10ea\",\"server"
      + "\":\"1786\",\"farm\":2,\"title\":\"the flow\",\"ispublic\":1,\"isfriend\""
      + ":0,\"isfamily\":0}]},\"stat\":\"ok\"}";
  private PhotoModelParser parser;

  @Before
  public void setup() {
    parser = new PhotoModelParser(null);
  }

  @Test
  public void testDataParsing() throws JSONException {
    //Cant write this test since JSONObject cannot be mocked

    JSONObject jsonObject = new JSONObject(DUMMY_RESPONSE);
    assertNotNull(jsonObject);
    assertNotNull(parser);
    parser.setRootObject(jsonObject);
    List<PhotoModel> photoModelList = parser.parse();
    assertNotNull(photoModelList);
    assertEquals(10, photoModelList.size());
  }
}
