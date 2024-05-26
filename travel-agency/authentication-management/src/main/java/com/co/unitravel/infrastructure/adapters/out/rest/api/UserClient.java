package com.co.unitravel.infrastructure.adapters.out.rest.api;

import com.co.unitravel.domain.models.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(name = "user-client",  url = "${keycloak.admin-url}")
public interface UserClient {

    @PostMapping("/users"
    )
    void createNewUser(@RequestBody User user, @RequestHeader("Authorization") String token);
}
