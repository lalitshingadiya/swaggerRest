package com.ecw.webservice.service;
import com.ecw.webservice.dto.CategoryDTO;

public interface CategoryService {

    Iterable<CategoryDTO> findByAll();

    CategoryDTO findById(Long id);

    CategoryDTO createCategory(CategoryDTO category);
}
