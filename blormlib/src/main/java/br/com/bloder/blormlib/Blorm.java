package br.com.bloder.blormlib;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import br.com.bloder.blormlib.validation.Validate;
import br.com.bloder.blormlib.validation.Validation;

/**
 * Created by bloder on 05/06/16.
 */
public class Blorm {

  public static class Builder {

    private List<Validate> validations;
    private View field;

    public Builder() {
      validations = new ArrayList<>();
    }

    public Builder field(View field) {
      this.field = field;
      return this;
    }

    public Builder is(Validate validation) {
      this.validations.add(validation);
      return this;
    }

    public Builder is(Validation validation) {
      validation.field = this.field;
      this.validations.add(validation.validate());
      return this;
    }

    public void onSubmit(View submittedItem) {
      new Blorm(this.validations).onSubmitted();
    }
  }

  private List<Validate> validations;
  
  public Blorm(List<Validate> validations) {
    this.validations = validations;
  }

  private void onSubmitted() {
    for(Validate validate : validations) {
      if(validate.validate()) {
      } else {
      }
    }
  }
}
