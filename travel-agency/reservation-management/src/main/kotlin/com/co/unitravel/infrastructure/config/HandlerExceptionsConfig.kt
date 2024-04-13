package com.co.unitravel.infrastructure.config

import com.co.unitravel.application.exceptions.general.BusinessException
import com.co.unitravel.application.exceptions.general.GeneralApiErrorCodes
import com.co.unitravel.application.exceptions.general.NotFoundException
import com.co.unitravel.infrastructure.config.responses.ErrorResponse
import org.springframework.context.MessageSource
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.util.*

@RestControllerAdvice
open class HandlerExceptionsConfig(private val messageSource: MessageSource){

    private val localeDefault = Locale.ENGLISH;

    @ExceptionHandler(BusinessException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleBusinessException(ex: BusinessException): ErrorResponse {
        return ErrorResponse(ex.message, ex.code, ex.errors())
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    fun handleNotFoundException(ex: NotFoundException): ErrorResponse {
        return ErrorResponse(ex.message, ex.code, ex.errors())
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun handleMethodArgumentNotValidException(ex: MethodArgumentNotValidException): ErrorResponse {
        val fieldError = ex.bindingResult.fieldErrors.firstOrNull()
        val code:String
        val message:String

        if (fieldError != null) {
            val fieldName = fieldError.field
            val constraintType = fieldError.code

            val error: String = when (constraintType) {
                "NotNull" -> {
                    getMessage(GeneralApiErrorCodes.FIELD_FIELD_NOT_NULL, arrayOf(fieldName))
                }
                //Here you can personalize the message of constraints, you have to add a new constraint case to do that
                else -> {
                    getMessage(GeneralApiErrorCodes.GENERIC_ERROR_MESSAGE, null)
                }
            }

            code = getMessage(GeneralApiErrorCodes.VALIDATION_CONFIG, null)
            message = getMessage(GeneralApiErrorCodes.INVALID_TRANSACTION, null)
            return ErrorResponse(code, message, mutableListOf(error))
        }
        code = getMessage(GeneralApiErrorCodes.VALIDATION_CONFIG, null)
        message = getMessage(GeneralApiErrorCodes.INVALID_TRANSACTION, null)
        return ErrorResponse(code, message, mutableListOf())
    }

    private fun getMessage(route:String, params: Array<Any>?): String{
        return messageSource.getMessage(route, params, localeDefault);
    }

}