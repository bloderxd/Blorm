package br.com.bloder.blormlib.validation;


import br.com.bloder.blormlib.validation.validations.Checked;
import br.com.bloder.blormlib.validation.validations.Filled;

/**
 * Created by bloder on 05/06/16.
 */
public class Validations {

  public static Validation filled = new Filled();
  public static Validation checked = new Checked();
}
