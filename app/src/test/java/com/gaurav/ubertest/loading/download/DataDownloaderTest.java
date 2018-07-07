package com.gaurav.ubertest.loading.download;

import android.os.Handler;
import android.os.Message;
import com.gaurav.ubertest.loading.request.JSONRequest;
import com.gaurav.ubertest.loading.request.Request;
import java.util.concurrent.ExecutorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by gauravn on 07/07/18.
 */

public class DataDownloaderTest {

  private DataDownloader downloader;
  @Mock ExecutorService service;
  @Mock Request request;

  private Handler mockHandler;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    downloader = new DataDownloader(service);
    downloader.setHandlerProvider(this::createHandler);
  }

  //when(downloader.createRequest(anyString(), any(RequestCreator.Source.class), any(Request.Callback.class))).thenReturn(request);
  //doAnswer(invocation -> {return null;}).when(request).download();
  //doAnswer(invocation -> {return null;}).when(request).decode(any(InputStream.class));

  @Test
  public void testSubmitRequest() {
    request = mock(JSONRequest.class);
    when(service.submit(any(Request.class))).then(invocation -> {
      Request runnable = invocation.getArgument(0);
      runnable.run();
      runnable.download();
      return null;
    }).thenReturn(null);

    downloader.submit(request);//loadData("http://www.google.com", RequestCreator.Source.DATA, jsonObjectCallback);
    verify(request, times(1)).run();
    verify(request, times(1)).download();
  }

  private Handler createHandler() {
    if (mockHandler == null) {
      mockHandler = mock(Handler.class);
      when(mockHandler.postAtFrontOfQueue(any(Runnable.class))).thenAnswer(invocation ->
      {
        Message msg = invocation.getArgument(0);
        msg.getCallback().run();
        return null;
      });
    }
    return mockHandler;
  }
}
