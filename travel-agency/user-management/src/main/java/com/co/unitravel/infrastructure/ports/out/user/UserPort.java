package com.co.unitravel.infrastructure.ports.out.user;

import com.co.unitravel.domain.models.User;

public interface UserPort {
     User save(User user);

     boolean existsByDocument(String documentNumber);
}
