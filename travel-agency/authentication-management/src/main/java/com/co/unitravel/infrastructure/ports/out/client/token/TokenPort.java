package com.co.unitravel.infrastructure.ports.out.client.token;

import com.co.unitravel.domain.models.Login;
import com.co.unitravel.domain.models.Token;

public interface TokenPort {

    Token getToken(Login login);
}
