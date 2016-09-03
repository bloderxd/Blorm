package br.com.bloder.blormlib.validation.validations

import android.widget.EditText
import br.com.bloder.blormlib.validation.Validate
import br.com.bloder.blormlib.validation.Validation
import java.util.regex.Matcher
import java.util.regex.Pattern

/**
 * Created by bloder on 03/09/16.
 */
class EmailKotlin : Validation(), Validate {

    private var editText : EditText? = null
    override fun validateIt(): Boolean {
        editText = field as EditText
        return isEmail(editText?.text.toString())
    }
    override fun onError() { editText?.error = errorMessage ?: "invalid email address" }
    override fun onSuccess() { editText?.error = null }
    override fun validate(): Validate = this
}

fun isEmail(email: String) : Boolean {
    val emailCase : String = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$"
    val pattern : Pattern = Pattern.compile(emailCase)
    val matcher : Matcher = pattern.matcher(email)
    return matcher.matches()
}