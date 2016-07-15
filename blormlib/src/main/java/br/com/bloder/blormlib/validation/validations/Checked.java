package br.com.bloder.blormlib.validation.validations;

import android.widget.CheckBox;

import br.com.bloder.blormlib.validation.Validate;
import br.com.bloder.blormlib.validation.Validation;

public class Checked extends Validation {

    private CheckBox checkBox;

    @Override
    public Validate validate() {
        return new Validate() {
            @Override
            public boolean validate() {
               checkBox = (CheckBox) field;
               return checkBox.isChecked();
            }

            @Override
            public void onError() {
                checkBox.setError("This field can't be unchecked");
            }

            @Override public void onSuccess() {
                checkBox.setError(null);
            }
        };
    }
}
