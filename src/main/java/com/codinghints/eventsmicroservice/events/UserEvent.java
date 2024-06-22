package com.codinghints.eventsmicroservice.events;

import com.codinghints.eventsmicroservice.entity.UserEntity;
import com.codinghints.eventsmicroservice.enumeration.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class UserEvent {
    private UserEntity user;
    private EventType eventType;
    private Map<?, ?> data;
}
