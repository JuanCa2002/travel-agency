package com.co.unitravel.domain.models;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class User implements Serializable {

   private String username;

   private Boolean enabled;

   private String token;

   private String firstName;

   private String lastName;

   private String email;

   private Boolean emailVerified;

   private List<Credential> credentials;
}
