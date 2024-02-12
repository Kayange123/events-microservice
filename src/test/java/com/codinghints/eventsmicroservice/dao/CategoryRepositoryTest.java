package com.codinghints.eventsmicroservice.dao;

import com.codinghints.eventsmicroservice.model.Category;
import com.codinghints.eventsmicroservice.model.Event;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepositoryTest {


    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void printCategory(){
        Pageable pages = PageRequest.of(0,2);
        Page<Category> categoriesPage = categoryRepository.findAll(pages);

        List<Category> categories = categoriesPage.getContent();
        long items = categoriesPage.getTotalPages();
        long total = categoriesPage.getTotalElements();

        System.out.println("categories = " + categories);
        System.out.println("items = " + items);
        System.out.println("total = " + total);
    }

    @Test
    public void printSorting(){
        Pageable pages = PageRequest.of(1, 5, Sort.by("name"));
        Page<Category> categories = categoryRepository.findAll(pages);
        System.out.println("categories = " + categories);
    }

    private Stream<Event> propagateStream(Category category) {
        return category.getEvents().stream();
    }
}