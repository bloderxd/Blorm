package br.com.bloder.blormlib.validation;


import br.com.bloder.blormlib.validation.validations.Checked;
import br.com.bloder.blormlib.validation.validations.Email;
import br.com.bloder.blormlib.validation.validations.Filled;
import br.com.bloder.blormlib.validation.validations.Number;

/**
 * Created by bloder on 05/06/16.
 */
public class Validations {

  public static Filled filled = new Filled();
  public static Validation checked = new Checked();
  public static Validation email = new Email();
  public static Number number = new Number();
}
