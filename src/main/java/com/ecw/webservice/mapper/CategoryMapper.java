package com.ecw.webservice.mapper;

import com.ecw.webservice.entity.Category;
import com.ecw.webservice.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    CategoryDTO toCategoryDTO(Category category);
    Iterable<CategoryDTO> toCategoryDTOs(Iterable<Category> category);
    Category toCategory(CategoryDTO category);
}
