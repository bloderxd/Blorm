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

    public Builder(List<Validate> validations, View field, Action onSuccess, Action onError) {
      this.validations = validations;
      this.field = field;
      this.onSuccess = onSuccess;
      this.onError = onError;
    }

    public Builder field(View field) {
      this.field = field;
      return this;
    }

    public Builder is(Validate validation) {
      this.validations.add(validation);
      return this;
    }

    public PhraseSequenceBuilder is(Validation validation) {
      validation.field = this.field;
      this.validations.add(validation.validate());
      return new PhraseSequenceBuilder(this.validations, this.field, this.onSuccess, this.onError);
    }

    public PhraseSequenceBuilder is(String errorMessage, Validation validation) {
      validation.field = this.field;
      validation.errorMessage = errorMessage;
      this.validations.add(validation.validate());
      return new PhraseSequenceBuilder(this.validations, this.field, this.onSuccess, this.onError);
    }
  }

  public static class PhraseSequenceBuilder {

    private List<Validate> validations;
    private View field;
    private Action onSuccess;
    private Action onError;

    public PhraseSequenceBuilder(List<Validate> validations, View field, Action onSuccess, Action onError) {
      this.validations = validations;
      this.field = field;
      this.onSuccess = onSuccess;
      this.onError = onError;
    }

    public PhraseSequenceBuilder and(Validation validation) {
      validation.field = this.field;
      this.validations.add(validation.validate());
      return this;
    }

    public PhraseSequenceBuilder and(String errorMessage, Validation validation) {
      validation.field = this.field;
      validation.errorMessage = errorMessage;
      this.validations.add(validation.validate());
      return this;
    }

    public PhraseSequenceBuilder and(Validate validate) {
      this.validations.add(validate);
      return this;
    }

    public Builder andField(View field) {
      return new Builder(this.validations, field, this.onSuccess, this.onError);
    }

    public PhraseSequenceBuilder onSuccess(Action action) {
      this.onSuccess = action;
      return this;
    }

    public PhraseSequenceBuilder onError(Action action) {
      this.onError = action;
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
      if(validate.validate()) {
        validate.onSuccess();
      } else {
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
