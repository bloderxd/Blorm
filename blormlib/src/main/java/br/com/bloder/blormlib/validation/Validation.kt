package br.com.bloder.blormlib.validation

import android.view.View

/**
 * Created by bloder on 03/09/16.
 */
abstract class Validation {

    var field: View? = null
    var errorMessage: String? = null
    abstract fun validate() : Validate
}