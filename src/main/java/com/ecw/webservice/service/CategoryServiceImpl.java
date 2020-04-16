package com.ecw.webservice.service;

import com.ecw.webservice.entity.Category;
import com.ecw.webservice.mapper.CategoryMapper;
import com.ecw.webservice.dto.CategoryDTO;
import com.ecw.webservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Iterable<CategoryDTO> findByAll() {
        return categoryMapper.toCategoryDTOs(categoryRepository.findAll());
    }

    @Override
    public CategoryDTO findById(Long id) {
        return categoryMapper.toCategoryDTO(categoryRepository.findById(id).get());
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO category) {
            Category category1 = categoryRepository.save(categoryMapper.toCategory(category));
        return categoryMapper.toCategoryDTO(category1);
    }
}
