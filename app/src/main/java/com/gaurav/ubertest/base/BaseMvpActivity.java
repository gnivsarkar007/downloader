package com.gaurav.ubertest.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by gauravn on 05/07/18.
 */

public abstract class BaseMvpActivity<P extends BasePresenterImpl> extends AppCompatActivity
    implements MvpView {

  protected P presenter;
  @Override protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    createPresenter();
  }

  @Override public void showError(final String error) {
    hideKeyboard();
    Toast toast = Toast.makeText(this, error, Toast.LENGTH_SHORT);
    toast.setGravity(Gravity.TOP, 0, 0);
    toast.show();
  }

  public final void hideKeyboard() {
    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
    //Find the currently focused view, so we can grab the correct window token from it.
    View view = getCurrentFocus();
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
      view = new View(this);
    }
    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
  }

  protected abstract P createPresenter();
}
