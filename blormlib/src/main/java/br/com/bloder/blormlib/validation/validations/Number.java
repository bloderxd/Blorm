package br.com.bloder.blormlib.validation.validations;

import android.text.TextUtils;
import android.widget.EditText;

import br.com.bloder.blormlib.validation.Validate;
import br.com.bloder.blormlib.validation.Validation;

/**
 * Created by bloder on 31/07/16.
 */
public class Number extends Validation {

  EditText editText;

  @Override
  public Validate validate() {
    return new Validate() {
      @Override
      public boolean validate() {
        editText = (EditText) field;
        if (editText.getText().toString().isEmpty()) return false;
        return TextUtils.isDigitsOnly(editText.getText().toString());
      }

      @Override
      public void onError() {
        editText.setError(errorMessage != null || !errorMessage.isEmpty() ? errorMessage : "This field only accept numbers!");
      }

      @Override
      public void onSuccess() {

      }
    };
  }
}
