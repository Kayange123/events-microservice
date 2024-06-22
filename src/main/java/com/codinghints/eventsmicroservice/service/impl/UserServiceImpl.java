package com.codinghints.eventsmicroservice.service.impl;

import com.codinghints.eventsmicroservice.dao.ConfirmationRepository;
import com.codinghints.eventsmicroservice.dao.CredentialRepository;
import com.codinghints.eventsmicroservice.dao.RoleRepository;
import com.codinghints.eventsmicroservice.dao.UserRepository;
import com.codinghints.eventsmicroservice.entity.Confirmation;
import com.codinghints.eventsmicroservice.entity.CredentialEntity;
import com.codinghints.eventsmicroservice.entity.RoleEntity;
import com.codinghints.eventsmicroservice.entity.UserEntity;
import com.codinghints.eventsmicroservice.enumeration.Authority;
import com.codinghints.eventsmicroservice.enumeration.EventType;
import com.codinghints.eventsmicroservice.events.UserEvent;
import com.codinghints.eventsmicroservice.exception.ApiException;
import com.codinghints.eventsmicroservice.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.codinghints.eventsmicroservice.utils.UserUtil.createUserEntity;

@Service
@Transactional(rollbackOn = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ConfirmationRepository confirmationRepository;
    private final CredentialRepository credentialRepository;
    //private final BcryptPasswordEncoder encoder;
    private final ApplicationEventPublisher publisher;

    @Override
    public void createUser(String firstName, String lastName, String email, String password) {
        var createdUser = userRepository.save(createNewUser(firstName, lastName, email));
        CredentialEntity credentialEntity = new CredentialEntity(createdUser,password);
        credentialRepository.save(credentialEntity);
        var confirmationEntity = new Confirmation(createdUser);
        confirmationRepository.save(confirmationEntity);
        publisher.publishEvent(new UserEvent(createdUser, EventType.REGISTRATION, Map.of("key",confirmationEntity.getKey())));
    }

    @Override
    public RoleEntity getRoleName(String name){
        var role = roleRepository.findByNameIgnoreCase(name);
        return role.orElseThrow(()->new ApiException("Role Not Found"));
    }
    private UserEntity createNewUser(String firstName, String lastName, String email) {
        var role = getRoleName(Authority.USER.name());
        return createUserEntity(firstName, lastName, email, role);
    }
}
