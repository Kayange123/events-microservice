package com.codinghints.eventsmicroservice.service;

import com.codinghints.eventsmicroservice.dao.UserRepository;
import com.codinghints.eventsmicroservice.dto.UserRequestDTO;
import com.codinghints.eventsmicroservice.dto.UserResponseDTO;
import com.codinghints.eventsmicroservice.exception.EventServiceBusinessException;
import com.codinghints.eventsmicroservice.exception.GlobalException;
import com.codinghints.eventsmicroservice.model.User;
import com.codinghints.eventsmicroservice.util.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(value = "user")
    public List<UserResponseDTO> getUsers() throws EventServiceBusinessException{
        try{
            log.info("UserService: getUsers -> Execution started");
            List<User> users = userRepository.findAll();
            log.debug("UserService: getUsers -> Retrieved users from DB. Users, {}", users);

            return users.stream().map(ObjectMapper::convertToUserResponseDTO)
                    .collect(Collectors.toList()
                    );
        }catch (Exception e){
            log.error("Exception occurred while getting users from the database. Exception Message {}", e.getMessage());
            throw new EventServiceBusinessException("Internal Server Error");
        }
    }

    public UserResponseDTO createNewUser(UserRequestDTO userRequest){
        User user = ObjectMapper.convertToUserEntity(userRequest);
        log.info("UserService: createNewUser. Execution started");
        try{
            User userDB = userRepository.save(user);
            return ObjectMapper.convertToUserResponseDTO(userDB);
        }catch (Exception e){
            throw new EventServiceBusinessException("Exception occurred while creating new user");
        }
    }

    public UserResponseDTO findUserById(UUID userId) throws GlobalException {
        try {
            var user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("No such user"));
            return ObjectMapper.convertToUserResponseDTO(user);
        } catch (RuntimeException e) {
            throw new EventServiceBusinessException("Could not find user");
        } catch (Exception e) {
            throw new GlobalException("Internal Server Error");
        }
    }

    public UserResponseDTO findUserByEmail(String email){
        try {
            User user = userRepository.findByEmail(email).orElseThrow(()-> new NoSuchElementException("Could Not Find user with this email"));
            return ObjectMapper.convertToUserResponseDTO(user);
        }catch (Exception e) {
            throw new EventServiceBusinessException("Could not find user by Email - "+ email);
        }
    }

    public void deleteUser(UUID id) throws GlobalException{
        try{
            var existsById =userRepository.existsById(id);
            if (existsById){
                userRepository.deleteById(id);
            }
        }catch (Exception e){
            throw new GlobalException("Internal Server Error");
        }
    }
}
