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

    private List<Validation> validations;
    private List<View> field;
    private List<String> errorMessages;
    private List<Validate> validates;
    private Action onSuccess;
    private Action onError;

    public Builder() {
      this.validates = new ArrayList<>();
      this.errorMessages = new ArrayList<>();
      this.validations = new ArrayList<>();
      this.field = new ArrayList<>();
    }

    public Builder(List<Validation> validations, List<View> field, List<String> errorMessages, List<Validate> validates, Action onSuccess, Action onError) {
      this.validations = validations;
      this.field = field;
      this.errorMessages = errorMessages;
      this.validates = validates;
      this.onSuccess = onSuccess;
      this.onError = onError;
    }

    public Builder field(View field) {
      this.field.add(field);
      return this;
    }

    public Builder validate(Validate validation) {
      this.validates.add(validation);
      return this;
    }

    public PhraseSequenceBuilder is(Validation validation) {
      this.errorMessages.add(null);
      this.validations.add(validation);
      return new PhraseSequenceBuilder(this.validations, this.field, this.errorMessages, this.validates, this.onSuccess, this.onError);
    }

    public PhraseSequenceBuilder is(String errorMessage, Validation validation) {
      this.errorMessages.add(errorMessage);
      this.validations.add(validation);
      return new PhraseSequenceBuilder(this.validations, this.field, this.errorMessages, this.validates, this.onSuccess, this.onError);
    }
  }

  public static class PhraseSequenceBuilder {

    private List<Validation> validations;
    private List<View> field;
    private List<String> errorMessages;
    private List<Validate> validates;
    private Action onSuccess;
    private Action onError;

    public PhraseSequenceBuilder(List<Validation> validations, List<View> field, List<String> errorMessages, List<Validate> validates, Action onSuccess, Action onError) {
      this.validations = validations;
      this.field = field;
      this.errorMessages = errorMessages;
      this.validates = validates;
      this.onSuccess = onSuccess;
      this.onError = onError;
    }

    public PhraseSequenceBuilder and(Validation validation) {
      this.errorMessages.add(null);
      this.validations.add(validation);
      return this;
    }

    public PhraseSequenceBuilder and(String errorMessage, Validation validation) {
      this.errorMessages.add(errorMessage);
      this.validations.add(validation);
      return this;
    }

    public PhraseSequenceBuilder and(Validate validate) {
      this.validates.add(validate);
      return this;
    }

    public Builder andField(View field) {
      this.field.add(field);
      return new Builder(this.validations, this.field, this.errorMessages, this.validates, this.onSuccess, this.onError);
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
          new Blorm(validations, field, errorMessages, validates, onSuccess, onError).onSubmitted();
        }
      });
    }
  }

  private List<Validation> validations;
  private List<View> fields;
  private List<String> errorMessages;
  private List<Validate> validates;
  private Action onSuccess;
  private Action onError;
  private Boolean allValidationsPassed = true;
  
  public Blorm(List<Validation> validations, List<View> fields, List<String> errorMessages, List<Validate> validates, Action onSuccess, Action onError) {
    this.validations = validations;
    this.fields = fields;
    this.errorMessages = errorMessages;
    this.validates = validates;
    this.onSuccess = onSuccess;
    this.onError = onError;
  }

  private void onSubmitted() {
    if(validates != null) {
      for(int i = 0; i < validates.size(); i++) {
        if(validates.get(i).validate()) {
          validates.get(i).onSuccess();
        } else {
          validates.get(i).onError();
        }
      }
    }

    for(int i = 0; i < fields.size(); i ++) {
      Validation currentValidation = validations.get(i);
      View currentField = fields.get(i);
      String currentErrorMessage = errorMessages.get(i);
      currentValidation.errorMessage = currentErrorMessage != null ? currentErrorMessage : null;
      currentValidation.field = currentField;
      if(currentValidation.validate().validate()) {
        currentValidation.validate().onSuccess();
      } else {
        allValidationsPassed = false;
        currentValidation.validate().onError();
      }
    }
    if(allValidationsPassed && onSuccess != null) {
      this.onSuccess.call();
    } else if(!allValidationsPassed && onError != null) {
      this.onError.call();
    }
  }
}
