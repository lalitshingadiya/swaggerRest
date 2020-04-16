package com.ecw.webservice.mapper;

import com.ecw.webservice.entity.Category;
import com.ecw.webservice.dto.CategoryDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    CategoryMapper categoryMapper;

    @BeforeEach
    void setUp() {
        categoryMapper = CategoryMapper.INSTANCE;
    }

    @Test
    void toCategoryDTO() {
        //given
        Category category = Category.builder().name("Fruit").build();

        //when
        CategoryDTO categoryDTO = categoryMapper.toCategoryDTO(category);

        //then
        assertNotNull(categoryDTO);
        assertEquals(category.getName(),categoryDTO.getName());
    }

    @Test
    void toCategoryDTOs() {
        //given
        List<Category> list = Arrays.asList(
                Category.builder().name("Fruit").build(),
                Category.builder().name("Nuts").build()
            );

        //when
        ArrayList<CategoryDTO> resultList = (ArrayList<CategoryDTO>)categoryMapper.toCategoryDTOs(list);

        //then
        assertNotNull(resultList);
        assertEquals(resultList.size(),2);
    }

    @Test
    void toCategory() {
        //given
        CategoryDTO categoryDTO = CategoryDTO.builder().name("Fruit").build();

        //when
        Category category = categoryMapper.toCategory(categoryDTO);

        //then
        assertNotNull(category);
        assertEquals(categoryDTO.getName(),category.getName());
    }
}