package br.com.bloder.blormlib.validation

/**
 * Created by bloder on 03/09/16.
 */

interface Validate {

    fun validateIt() : Boolean
    fun onError()
    fun onSuccess()
}