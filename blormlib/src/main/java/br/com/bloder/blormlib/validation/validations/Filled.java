package br.com.bloder.blormlib.validation.validations;

import android.widget.EditText;

import br.com.bloder.blormlib.validation.Validate;
import br.com.bloder.blormlib.validation.Validation;

/**
 * Created by bloder on 05/06/16.
 */
public class Filled extends Validation {

  public EditText editText;

  @Override
  public Validate validate() {
    return new Validate() {
      @Override
      public boolean validate() {
        editText = (EditText) field;
        return !String.valueOf(editText.getText()).trim().isEmpty();
      }
    };
  }
}
