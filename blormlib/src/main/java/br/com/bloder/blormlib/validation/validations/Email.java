package br.com.bloder.blormlib.validation.validations;

import android.widget.EditText;

import br.com.bloder.blormlib.validation.Validate;
import br.com.bloder.blormlib.validation.Validation;

/**
 * Created by bloder on 17/07/16.
 */
public class Email extends Validation {

  private EditText editText;

  @Override
  public Validate validate() {
    return new Validate() {
      @Override
      public boolean validate() {
        editText = (EditText) field;
        return isEmail(editText.getText().toString());
      }

      @Override
      public void onError() {
        editText.setError(errorMessage != null && !errorMessage.isEmpty() ? errorMessage : "invalid email address");
      }

      @Override
      public void onSuccess() {
        editText.setError(null);
      }
    };
  }

  private boolean isEmail(String email) {
    String emailCase = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
    java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(emailCase);
    java.util.regex.Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }
}
