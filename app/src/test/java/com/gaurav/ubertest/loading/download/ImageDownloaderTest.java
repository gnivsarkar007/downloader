package com.gaurav.ubertest.loading.download;

import android.os.Handler;
import android.os.Message;
import com.gaurav.ubertest.loading.request.FlickrRequest;
import com.gaurav.ubertest.loading.request.Request;
import java.util.concurrent.ExecutorService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by gauravn on 08/07/18.
 */

public class ImageDownloaderTest {

  private ImageDownloader downloader;
  @Mock ExecutorService service;
  @Mock Request request;

  private Handler mockHandler;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    downloader = new ImageDownloader(service);
    downloader.setHandlerProvider(this::createHandler);
  }

  @Test
  public void testSubmitRequest() {
    request = mock(FlickrRequest.class);
    when(service.submit(any(FlickrRequest.class))).then(invocation -> {
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
