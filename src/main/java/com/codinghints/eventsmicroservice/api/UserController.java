package com.codinghints.eventsmicroservice.api;

import com.codinghints.eventsmicroservice.dto.APIResponse;
import com.codinghints.eventsmicroservice.dto.UserRequestDTO;
import com.codinghints.eventsmicroservice.dto.UserResponseDTO;
import com.codinghints.eventsmicroservice.exception.EventServiceBusinessException;
import com.codinghints.eventsmicroservice.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController @Slf4j
@RequestMapping("/api/v1")
@AllArgsConstructor
public class UserController {
    private static final String STATUS = "SUCCESS";
    private final UserService userService;

    @PostMapping("/users/register")
    public ResponseEntity<APIResponse<UserResponseDTO>> createNewUser(@RequestBody UserRequestDTO userRequestDTO){
        UserResponseDTO user = userService.createNewUser(userRequestDTO);
        APIResponse<UserResponseDTO> apiResponse = APIResponse
                .<UserResponseDTO>builder()
                .status(STATUS)
                .results(user)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }
    @GetMapping("/users")
    public ResponseEntity<APIResponse<List<UserResponseDTO>>> findAllUsers(){
        List<UserResponseDTO> users;
        try {
            users = userService.findAllUsers();
        }catch (Exception e){
            throw new EventServiceBusinessException("Error getting users");
        }
        APIResponse<List<UserResponseDTO>> responseAPI = APIResponse
                .<List<UserResponseDTO>>builder()
                .status(STATUS)
                .results(users)
                .build();
        return new ResponseEntity<>(responseAPI, HttpStatus.OK);
    }

    @GetMapping(name = "/users/{id}")
    @ResponseStatus(value = HttpStatus.OK, reason = "Successfully")
    public ResponseEntity<?> getUserById(@PathVariable(name = "id") UUID id){
        UserResponseDTO userResponseDTO;
        try {
            userResponseDTO = userService.findUserById(id);
        }catch (Exception e){
            throw new EventServiceBusinessException("Error getting user");
        }
        APIResponse<UserResponseDTO> apiResponse = APIResponse
                .<UserResponseDTO>builder()
                .status("SUCCESS")
                .results(userResponseDTO)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
