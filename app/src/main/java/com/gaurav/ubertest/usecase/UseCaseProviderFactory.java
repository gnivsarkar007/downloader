package com.gaurav.ubertest.usecase;

import android.support.annotation.NonNull;
import com.gaurav.ubertest.base.UseCase;
import com.gaurav.ubertest.loading.parser.PhotoModelParser;
import com.gaurav.ubertest.usecase.repository.PhotoModelRepository;

/**
 * Created by gauravn on 07/07/18.
 */

public class UseCaseProviderFactory {

  public enum UseCases {
    ImageFetch("com.gaurav.ubertest.usecase.ImageFetchUseCase");
    String canonicalName;

    UseCases(final String canonicalName) {
      this.canonicalName = canonicalName;
    }
  }

  public static UseCase getUseCase(@NonNull UseCases usecase) {
    switch (usecase) {
      case ImageFetch:
      default:
        return new ImageFetchUseCase(new PhotoModelRepository(), new PhotoModelParser(null));
    }
  }
}
