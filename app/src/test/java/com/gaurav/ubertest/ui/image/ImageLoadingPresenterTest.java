package com.gaurav.ubertest.ui.image;

import com.gaurav.ubertest.base.UseCase;
import com.gaurav.ubertest.loading.model.PhotoModel;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;

/**
 * Created by gauravn on 07/07/18.
 */

public class ImageLoadingPresenterTest {

  @Mock ImageLoadingActivityContract.View view;
  @Mock UseCase useCase;
  private ImageLoadingActivityContract.Presenter presenter;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    presenter = new ImageLoadingPresenter(view, useCase);
  }

  @Test
  public void testPresenterSetup() {
    doAnswer(invocation -> {
      UseCase.Callback<List<PhotoModel>> callback = invocation.getArgument(1);
      callback.onResponse(new ArrayList<>());
      return null;
    }).when(useCase)
        .execute(any(), any(UseCase.Callback.class));
    presenter.search("test", 1);
    InOrder inOrder = inOrder(view);
    inOrder.verify(view, times(1)).onDataLoaded(
        anyList());
  }
}
