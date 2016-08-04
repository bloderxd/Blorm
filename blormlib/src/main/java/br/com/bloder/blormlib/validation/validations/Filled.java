package br.com.bloder.blormlib.validation.validations;

import android.widget.EditText;

import br.com.bloder.blormlib.validation.Validate;
import br.com.bloder.blormlib.validation.Validation;

/**
 * Created by bloder on 05/06/16.
 */
public class Filled extends Validation {

  public EditText editText;
  private int maxSize = -1;
  private int minSize = -1;
 
  public Filled withMaxSize(int maxSize) {
    this.maxSize = maxSize;
    return this;
  }
 
  public Filled withMinimumSize(int minSize) {
    this.minSize = minSize;
    return this;
  }
 
  @Override public Validate validate() {
    return new Validate() {
      @Override public boolean validate() {
        editText = (EditText) field;
        String text = String.valueOf(editText.getText()).trim();
        int textLength = text.length();
        if (!text.isEmpty()) {
          if (maxSize != -1) {
            if (textLength > maxSize) {
              return false;
            }
          }
          if (minSize != -1) {
            if (textLength < minSize) {
              return false;
            }
          }
          return true;
        }
        return false;
      }

      @Override
      public void onError() {
        editText.setError(errorMessage != null && !errorMessage.isEmpty() ? errorMessage : "This field can't be blank");
      }

      @Override public void onSuccess() {
        editText.setError(null);
      }
    };
  }
}
