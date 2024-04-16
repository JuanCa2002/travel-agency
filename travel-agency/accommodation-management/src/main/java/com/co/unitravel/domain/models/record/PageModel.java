package com.co.unitravel.domain.models.record;

import java.math.BigInteger;

public record PageModel<T>(T data, BigInteger total) {}
