package com.ecw.webservice.controller;

import com.ecw.webservice.dto.CategoryDTO;
import com.ecw.webservice.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

class CategoryControllerTest {

    @Mock
    CategoryService categoryService;

    @InjectMocks
    CategoryController categoryController;

    MockMvc mockMvc;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void findAllCategory() throws Exception {

        //given
        ArrayList<CategoryDTO> list = new ArrayList<>();
        list.add(CategoryDTO.builder().name("Fruit").build());
        list.add(CategoryDTO.builder().name("Nuts").build());
        //when
        when(categoryService.findByAll()).thenReturn(list);

        //then
        MvcResult mvcResult = mockMvc.perform(
                get("/api/v1/categories")
                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String str = mvcResult.getResponse().getContentAsString();
        assertNotNull(str);
    }

    @Test
    void findById() throws Exception {
        //given
        CategoryDTO cat1 = CategoryDTO.builder().id(1L).name("Fruit").build();
        //when
        when(categoryService.findById(1L)).thenReturn(cat1);

        //then
        MvcResult mvcResult = mockMvc.perform(
                get("/api/v1/categories/1").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String str = mvcResult.getResponse().getContentAsString();
        assertNotNull(str);

    }

    @Test
    void creatCategory() throws Exception {
        //given
        CategoryDTO cat1 = CategoryDTO.builder().name("Fruit").build();

        //when
        when(categoryService.createCategory(any())).thenReturn(cat1);

        //then
        MvcResult mvcResult = mockMvc.perform(
                post("/api/v1/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cat1.toJsonString())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();


        String str = mvcResult.getResponse().getContentAsString();
        assertNotNull(str);
    }
}