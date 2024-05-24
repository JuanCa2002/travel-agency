package com.co.unitravel.application.usecases.refreshToken;

import com.co.unitravel.domain.models.RefreshToken;
import com.co.unitravel.domain.models.Token;
import com.co.unitravel.infrastructure.ports.in.refreshToken.RefreshTokenUseCase;
import com.co.unitravel.infrastructure.ports.out.client.token.TokenPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenUseCaseImpl implements RefreshTokenUseCase {

    private final TokenPort tokenPort;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public Token refreshToken(RefreshToken refreshToken) {
        return tokenPort.getRefreshToken(refreshToken);
    }
}
