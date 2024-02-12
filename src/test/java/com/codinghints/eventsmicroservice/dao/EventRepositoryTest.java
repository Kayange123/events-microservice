package com.codinghints.eventsmicroservice.dao;

import com.codinghints.eventsmicroservice.model.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@DataJpaTest
class EventRepositoryTest {
    @Autowired
    private EventRepository eventRepository;

    @Test
    public void saveEvent(){
        Event event = Event.builder()
                .description("Event description")
                .price(BigDecimal.TEN)
                .title("Event title")
                .imageUrl("http://mydemo.com/images")
                .url("http://mydemo.com")
                .isFree(true)
                .build();
        Event save = eventRepository.save(event);
    }

    @Test
    public void printPagination(){
        Pageable firstPageable = PageRequest.of(0,3);
        Pageable lastPageable = PageRequest.of(1, 5);

        Page<Event> page = eventRepository.findAll(firstPageable);
        long totalElements = page.getTotalElements();
        long totalPages = page.getTotalPages();
        List<Event> events = page.getContent();

        System.out.println("events = " + events);

        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements = " + totalElements);

    }

    @Test
    public void getAllEvents(){
        List<Event> events = eventRepository.findAll();

        System.out.println(events);
    }

    @Test
    public void getEventContainingText(){
       Optional<Event> event = eventRepository.findByTitleContainingIgnoreCase("take");
        System.out.println(event);

    }
}