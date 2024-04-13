package com.co.unitravel.application.exceptions.general

import lombok.Getter
import org.springframework.context.support.ResourceBundleMessageSource
import java.util.*
import kotlin.collections.ArrayList

@Getter
open class ApiException(
    override var message: String,
    open var code: String,
) : Exception(message) {

    private val localeDefault = Locale.ENGLISH;

    private val messageSource = ResourceBundleMessageSource()

    private val errors: MutableList<String> = ArrayList()

    fun addError(errorRoute: String, params: Array<Any>?) {
        messageSource.setBasename("messages")
        messageSource.setDefaultEncoding("UTF-8")
        processInfo()
        val error = getMessage(errorRoute, params)
        errors.add(error)
    }

    fun errors(): MutableList<String> {
        return errors
    }

    private fun processInfo(){
        message = getMessage(message, null)
        code = getMessage(code, null)
    }

    private fun getMessage(route:String, params: Array<Any>?): String{
        return messageSource.getMessage(route, params, localeDefault);
    }
}