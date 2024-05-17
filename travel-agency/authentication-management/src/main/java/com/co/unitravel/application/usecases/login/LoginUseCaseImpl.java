package com.co.unitravel.application.usecases.login;

import com.co.unitravel.domain.models.Login;
import com.co.unitravel.domain.models.Token;
import com.co.unitravel.infrastructure.ports.in.login.LoginUseCase;
import com.co.unitravel.infrastructure.ports.out.client.token.TokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginUseCaseImpl implements LoginUseCase {

    private final TokenPort tokenPort;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Token login(Login login) {
        return tokenPort.getToken(login);
    }
}
