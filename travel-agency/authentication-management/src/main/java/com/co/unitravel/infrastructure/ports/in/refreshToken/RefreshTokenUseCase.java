package com.co.unitravel.infrastructure.ports.in.refreshToken;

import com.co.unitravel.domain.models.RefreshToken;
import com.co.unitravel.domain.models.Token;

public interface RefreshTokenUseCase {

    Token refreshToken(RefreshToken refreshToken);
}
