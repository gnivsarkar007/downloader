package com.gaurav.ubertest.usecase;

import android.util.Pair;
import com.gaurav.ubertest.base.Repository;
import com.gaurav.ubertest.base.UseCase;
import com.gaurav.ubertest.loading.model.PhotoModel;
import com.gaurav.ubertest.loading.parser.Parser;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by gauravn on 07/07/18.
 */

public class ImageFetchUseCaseTest {
  private ImageFetchUseCase imageFetchUseCase;
  @Mock Repository imageFetchRepo;
  @Mock UseCase.Callback useCaseCallback;
  @Mock Parser<List<PhotoModel>> parser;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    imageFetchUseCase = new ImageFetchUseCase(imageFetchRepo, parser);
  }

  @Test
  public void testRepoAccess() {
    doAnswer(invocation -> {
      Repository.Callback<JSONObject> callback = invocation.getArgument(1);
      callback.onDataLoaded(new JSONObject());
      return null;
    }).when(imageFetchRepo).get(any(), any(Repository.Callback.class));
    when(parser.parse()).thenReturn(new ArrayList<>());
    imageFetchUseCase.execute(new Pair<>("test", 1), useCaseCallback);
    verify(useCaseCallback, times(1)).onResponse(any());
  }
}
