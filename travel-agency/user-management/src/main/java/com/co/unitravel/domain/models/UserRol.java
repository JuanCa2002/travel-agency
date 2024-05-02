package com.co.unitravel.domain.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRol {

    private Long id;

    private Rol rol;

    private User user;
}
