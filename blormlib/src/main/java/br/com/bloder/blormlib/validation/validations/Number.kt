package br.com.bloder.blormlib.validation.validations

import android.text.TextUtils
import android.widget.EditText
import br.com.bloder.blormlib.validation.Validate
import br.com.bloder.blormlib.validation.Validation

/**
 * Created by bloder on 03/09/16.
 */
class Number : Validation(), Validate {

    var editText: EditText? = null
    private var maxSize = -1
    private var minSize = -1
    private var isOnlyPositive = false
    private var isOnlyNegative = false

    override fun validate(): Validate = this
    override fun validateIt(): Boolean {
        editText = field as EditText
       return checkNumber(editText!!)
    }
    override fun onError() { editText?.error = errorMessage ?: "This field only accept numbers!" }
    override fun onSuccess() { editText?.error = null }

    fun withMaxSize(maxSize: Int): Number {
        this.maxSize = maxSize
        return this
    }

    fun withMinimumSize(minSize: Int): Number {
        this.minSize = minSize
        return this
    }

    fun andPositive(): Number {
        isOnlyPositive = true
        isOnlyNegative = false
        return this
    }

    fun andNegative(): Number {
        isOnlyNegative = true
        isOnlyPositive = false
        return this
    }

    fun checkNumber(editText: EditText): Boolean {
        if (editText?.text.toString().isEmpty()) {
            return false
        } else if (TextUtils.isDigitsOnly(editText.text.toString()) && isOnlyPositive && !isOnlyNegative && maxSize == -1 && minSize == -1) {
            return Integer.parseInt(editText.text.toString()) > 0
        } else if (TextUtils.isDigitsOnly(editText.text.toString().substring(1)) && isOnlyNegative && !isOnlyPositive && maxSize == -1 && minSize == -1) {
            return editText?.text[0].toString() == "-" && editText.text.toString().length > 1 && TextUtils.isDigitsOnly(editText.text.toString().substring(1))
        } else if (TextUtils.isDigitsOnly(editText.text.toString()) && maxSize > 0 && minSize > 0 && !isOnlyNegative && !isOnlyPositive) {
            return editText?.text.toString().length >= minSize && editText.text.toString().length <= maxSize
        } else if (TextUtils.isDigitsOnly(editText.text.toString()) && maxSize == -1 && minSize >= 0 && !isOnlyNegative && !isOnlyPositive) {
            return editText?.text.toString().length >= minSize
        } else if (TextUtils.isDigitsOnly(editText.text.toString()) && maxSize >= 0 && minSize == -1 && !isOnlyNegative && !isOnlyPositive) {
            return editText?.text.toString().length <= maxSize
        } else if (TextUtils.isDigitsOnly(editText.text.toString()) && isOnlyPositive && !isOnlyNegative && maxSize >= 0 && minSize >= 0) {
            return Integer.parseInt(editText.text.toString()) > 0 && editText.text.toString().length >= minSize && editText.text.toString().length <= maxSize
        } else if (TextUtils.isDigitsOnly(editText.text.toString().substring(1)) && isOnlyNegative && !isOnlyPositive && maxSize >= 0 && minSize >= 0) {
            return editText?.text[0].toString() == "-"
                    && editText?.text.toString().length > 1
                    && TextUtils.isDigitsOnly(editText?.text.toString().substring(1))
                    && editText?.text.toString().substring(1).length >= minSize
                    && editText?.text.toString().substring(1).length <= maxSize
        } else if (TextUtils.isDigitsOnly(editText?.text.toString()) && isOnlyPositive && !isOnlyNegative && maxSize == -1 && minSize >= 0) {
            return Integer.parseInt(editText?.text.toString()) > 0 && editText.text.toString().length >= minSize
        } else if (TextUtils.isDigitsOnly(editText?.text.toString()) && isOnlyPositive && !isOnlyNegative && maxSize >= 0 && minSize == -1) {
            return Integer.parseInt(editText?.text.toString()) > 0 && editText.text.toString().length <= maxSize
        } else if (TextUtils.isDigitsOnly(editText?.text.toString().substring(1)) && isOnlyNegative && !isOnlyPositive && maxSize == -1 && minSize >= 0) {
            return editText?.text[0].toString() == "-"
                    && editText?.text.toString().length > 1
                    && TextUtils.isDigitsOnly(editText?.text.toString().substring(1))
                    && editText?.text.toString().substring(1).length >= minSize
        } else if (TextUtils.isDigitsOnly(editText?.text.toString()) && isOnlyNegative && !isOnlyPositive && maxSize >= 0 && minSize == -1) {
            return editText?.text[0].toString() == "-"
                    && editText?.text.toString().length > 1
                    && TextUtils.isDigitsOnly(editText?.text.toString().substring(1))
                    && editText?.text.toString().substring(1).length <= maxSize
        }
        return TextUtils.isDigitsOnly(editText?.text.toString())
    }
}
