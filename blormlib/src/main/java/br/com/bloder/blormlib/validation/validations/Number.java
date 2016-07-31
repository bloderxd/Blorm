package br.com.bloder.blormlib.validation.validations;

import android.text.TextUtils;
import android.widget.EditText;

import br.com.bloder.blormlib.validation.Validate;
import br.com.bloder.blormlib.validation.Validation;

/**
 * Created by bloder on 31/07/16.
 */
public class Number extends Validation {

  private int maxSize = -1;
  private int minSize = -1;
  private boolean isOnlyPositive = false;
  private boolean isOnlyNegative = false;

  public Number withMaxSize(int maxSize) {
    this.maxSize = maxSize;
    return this;
  }

  public Number withMinimumSize(int minSize) {
    this.minSize = minSize;
    return this;
  }

  public Number andPositive() {
    isOnlyPositive = true;
    isOnlyNegative = false;
    return this;
  }

  public Number andNegative() {
    isOnlyNegative = true;
    isOnlyPositive = false;
    return this;
  }

  EditText editText;

  @Override
  public Validate validate() {
    return new Validate() {
      @Override
      public boolean validate() {
        editText = (EditText) field;
        return checkNumber(editText);
      }

      @Override
      public void onError() {
        editText.setError(errorMessage != null ? errorMessage : "This field only accept numbers!");
      }

      @Override
      public void onSuccess() {

      }
    };
  }

  private boolean checkNumber(EditText editText) {
    if (editText.getText().toString().isEmpty()) {
      return false;
    } else if (TextUtils.isDigitsOnly(editText.getText().toString()) && isOnlyPositive && !isOnlyNegative && maxSize == -1 && minSize == -1) {
      return Integer.parseInt(editText.getText().toString()) > 0;
    } else if (TextUtils.isDigitsOnly(editText.getText().toString().substring(1)) && isOnlyNegative && !isOnlyPositive && maxSize == -1 && minSize == -1) {
      return String.valueOf(editText.getText().charAt(0)).equals("-") && editText.getText().toString().length() > 1 && TextUtils.isDigitsOnly(editText.getText().toString().substring(1));
    } else if (TextUtils.isDigitsOnly(editText.getText().toString()) && (maxSize > 0 && minSize > 0) && (!isOnlyNegative && !isOnlyPositive)) {
      return editText.getText().toString().length() >= minSize && editText.getText().toString().length() <= maxSize;
    } else if (TextUtils.isDigitsOnly(editText.getText().toString()) && (maxSize == -1 && minSize >= 0) && (!isOnlyNegative && !isOnlyPositive)) {
      return editText.getText().toString().length() >= minSize;
    } else if (TextUtils.isDigitsOnly(editText.getText().toString()) && (maxSize >= 0 && minSize == -1) && (!isOnlyNegative && !isOnlyPositive)) {
      return editText.getText().toString().length() <= maxSize;
    } else if (TextUtils.isDigitsOnly(editText.getText().toString()) && isOnlyPositive && !isOnlyNegative && maxSize >= 0 && minSize >= 0) {
      return Integer.parseInt(editText.getText().toString()) > 0 && editText.getText().toString().length() >= minSize && editText.getText().toString().length() <= maxSize;
    } else if (TextUtils.isDigitsOnly(editText.getText().toString().substring(1)) && isOnlyNegative && !isOnlyPositive && maxSize >= 0 && minSize >= 0) {
      return String.valueOf(editText.getText().charAt(0)).equals("-")
              && editText.getText().toString().length() > 1
              && TextUtils.isDigitsOnly(editText.getText().toString().substring(1))
              && editText.getText().toString().substring(1).length() >= minSize
              && editText.getText().toString().substring(1).length() <= maxSize;
    } else if (TextUtils.isDigitsOnly(editText.getText().toString()) && isOnlyPositive && !isOnlyNegative && maxSize == -1 && minSize >= 0) {
      return Integer.parseInt(editText.getText().toString()) > 0 && editText.getText().toString().length() >= minSize;
    } else if (TextUtils.isDigitsOnly(editText.getText().toString()) && isOnlyPositive && !isOnlyNegative && maxSize >= 0 && minSize == -1) {
      return Integer.parseInt(editText.getText().toString()) > 0 && editText.getText().toString().length() <= maxSize;
    } else if (TextUtils.isDigitsOnly(editText.getText().toString().substring(1)) && isOnlyNegative && !isOnlyPositive && maxSize == -1 && minSize >= 0) {
      return String.valueOf(editText.getText().charAt(0)).equals("-")
              && editText.getText().toString().length() > 1
              && TextUtils.isDigitsOnly(editText.getText().toString().substring(1))
              && editText.getText().toString().substring(1).length() >= minSize;
    } else if (TextUtils.isDigitsOnly(editText.getText().toString()) && isOnlyNegative && !isOnlyPositive && maxSize >= 0 && minSize == -1) {
      return String.valueOf(editText.getText().charAt(0)).equals("-")
              && editText.getText().toString().length() > 1
              && TextUtils.isDigitsOnly(editText.getText().toString().substring(1))
              && editText.getText().toString().substring(1).length() <= maxSize;
    }
    return TextUtils.isDigitsOnly(editText.getText().toString());
  }
}
