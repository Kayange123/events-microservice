package com.codinghints.eventsmicroservice.events;

import com.codinghints.eventsmicroservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserEventListener {
    private final EmailService emailService;

    @EventListener
    public void onUserEvent(UserEvent event){
        switch (event.getEventType()){
            case REGISTRATION -> emailService.sendNewAccountEmail(event.getUser().getFirstName(), event.getUser().getEmail(), (String) event.getData().get("key"));
            case PASSWORD_RESET -> emailService.sendPasswordResetEmail(event.getUser().getFirstName(), event.getUser().getEmail(), (String) event.getData().get("key"));
            default -> {}
        }
    }
}
