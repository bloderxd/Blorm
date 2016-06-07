package br.com.bloder.blormlib;

import android.view.View;

import java.util.ArrayList;
import java.util.List;

import br.com.bloder.blormlib.validation.Action;
import br.com.bloder.blormlib.validation.Validate;
import br.com.bloder.blormlib.validation.Validation;

/**
 * Created by bloder on 05/06/16.
 */
public class Blorm {

  public static class Builder {

    private List<Validate> validations;
    private View field;
    private Action onSuccess;
    private Action onError;

    public Builder() {
      validations = new ArrayList<>();
    }

    public Builder onSuccess(Action action) {
      this.onSuccess = action;
      return this;
    }

    public Builder onError(Action action) {
      this.onError = action;
      return this;
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

    public Builder is(String errorMessgae, Validation validation) {
      validation.field = this.field;
      validation.errorMessage = errorMessgae;
      this.validations.add(validation.validate());
      return this;
    }

    public void submitOn(View submittedItem) {
      submittedItem.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          new Blorm(validations, onSuccess, onError).onSubmitted();
        }
      });
    }
  }

  private List<Validate> validations;
  private Action onSuccess;
  private Action onError;
  private Boolean allValidationsPassed = true;
  
  public Blorm(List<Validate> validations, Action onSuccess, Action onError) {
    this.validations = validations;
    this.onSuccess = onSuccess;
    this.onError = onError;
  }

  private void onSubmitted() {
    for(Validate validate : validations) {
      if(!validate.validate()) {
        allValidationsPassed = false;
        validate.onError();
      }
    }
    if(allValidationsPassed && onSuccess != null) {
      this.onSuccess.call();
    } else if(!allValidationsPassed && onError != null) {
      this.onError.call();
    }
  }
}
