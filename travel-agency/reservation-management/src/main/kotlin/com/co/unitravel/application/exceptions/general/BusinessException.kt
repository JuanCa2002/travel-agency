package com.co.unitravel.application.exceptions.general

open class BusinessException(override var message: String, override var code: String) :
    ApiException(message, code)