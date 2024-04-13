package com.co.unitravel.application.exceptions.general

open class NotFoundException(override var message: String, override var code: String ):
    ApiException(message, code)