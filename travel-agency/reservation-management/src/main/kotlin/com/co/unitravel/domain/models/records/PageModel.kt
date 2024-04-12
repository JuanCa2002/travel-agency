package com.co.unitravel.domain.models.records

import java.math.BigInteger

data class PageModel<T>(val data: T, val total: BigInteger)
