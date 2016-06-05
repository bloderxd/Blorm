package br.com.bloder.blorm.validation.validations;

import android.widget.EditText;

import br.com.bloder.blorm.validation.Validate;
import br.com.bloder.blorm.validation.Validation;

/**
 * Created by bloder on 05/06/16.
 */
public class Filled extends Validation {

  private final EditText editText;

  public Filled() {
    this.editText = (EditText) field;
  }

  @Override
  public Validate validate() {
    return new Validate() {
      @Override
      public boolean validate() {
        return !String.valueOf(editText.getText()).trim().isEmpty();
      }
    };
  }
}
