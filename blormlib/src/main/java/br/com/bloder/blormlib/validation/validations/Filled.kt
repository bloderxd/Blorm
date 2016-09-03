package br.com.bloder.blormlib.validation.validations

import android.widget.EditText
import br.com.bloder.blormlib.validation.Validate
import br.com.bloder.blormlib.validation.Validation

/**
 * Created by bloder on 03/09/16.
 */
class Filled : Validation(), Validate {

    private var editText: EditText? = null
    var maxSize: Int = -1
    var minSize: Int = -1

    fun withMinimunSize(minSize: Int) : Filled {
        this.minSize = minSize
        return this
    }
    fun withMaxSize(maxSize: Int) : Filled {
        this.maxSize = maxSize
        return this
    }

    override fun validate(): Validate = this
    override fun validateIt(): Boolean {
        editText = field as EditText
        val text: String = editText?.text.toString()
        val textLength: Int = text.length
        if (minSize != -1 && maxSize != -1) {
            return !text.isEmpty() && textLength >= minSize || !text.isEmpty() && textLength <= maxSize
        } else {
            return text != ""
        }
    }
    override fun onError() {
        editText?.error = errorMessage ?: "This field can't be blank"
    }
    override fun onSuccess() {
        editText?.error = null
    }
}