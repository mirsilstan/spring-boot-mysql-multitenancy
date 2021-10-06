package com.mirsilstan.multitenant.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{tenantId}/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers(@PathVariable final String tenantId) {
        log.info("Tenant id {}", tenantId);
        return userService.getUsers();
    }
}
