package com.co.unitravel.infrastructure.ports.in.login;

import com.co.unitravel.domain.models.Login;
import com.co.unitravel.domain.models.Token;

public interface LoginUseCase {

    Token login(Login login);
}
