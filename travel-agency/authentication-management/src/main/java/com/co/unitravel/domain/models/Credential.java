package com.co.unitravel.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Credential implements Serializable {

    private String type;

    private String value;

    private Boolean temporary;
}
