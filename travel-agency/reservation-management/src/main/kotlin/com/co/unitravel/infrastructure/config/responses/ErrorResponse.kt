package com.co.unitravel.infrastructure.config.responses

data class ErrorResponse(var code:String?, var message:String?, var errors: MutableList<String> )