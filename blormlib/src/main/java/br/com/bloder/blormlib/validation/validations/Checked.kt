package br.com.bloder.blormlib.validation.validations

import android.widget.CheckBox
import br.com.bloder.blormlib.validation.Validate
import br.com.bloder.blormlib.validation.Validation

/**
 * Created by bloder on 03/09/16.
 */

class Checked : Validation(), Validate {

    private var checkBox: CheckBox? = null
    override fun validateIt(): Boolean {
        checkBox = field as CheckBox
        return checkBox?.isChecked as Boolean
    }
    override fun onError() { checkBox?.error = "This field can't be unchecked" }
    override fun onSuccess() { checkBox?.error = null }
    override fun validate(): Validate { return this }
}