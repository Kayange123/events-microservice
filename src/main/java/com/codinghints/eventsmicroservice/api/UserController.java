package com.codinghints.eventsmicroservice.api;


import com.codinghints.eventsmicroservice.dto.Response;
import com.codinghints.eventsmicroservice.dto.UserRequest;
import com.codinghints.eventsmicroservice.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

import static com.codinghints.eventsmicroservice.utils.RequestUtils.getResponse;
import static java.util.Collections.emptyMap;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> saveUser(@Valid @RequestBody UserRequest userRequest, HttpServletRequest request){
        userService.createUser(userRequest.getFirstName(), userRequest.getLastName(), userRequest.getEmail(), userRequest.getPassword());

        return ResponseEntity.created(geUri()).body(getResponse(request, emptyMap(), "Account Created. Check your email to enable your account", HttpStatus.CREATED));
    }

    private URI geUri() {
        return URI.create("");
    }
}
