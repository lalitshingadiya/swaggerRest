package com.ecw.webservice.controller;

import com.ecw.webservice.dto.CategoryDTO;
import com.ecw.webservice.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(value = "My Category Controller",produces = "application/json")
public class CategoryController {

    CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("api/v1/categories")
    @ApiOperation(value = "API to get all categories.", notes = "This is a notes section.")
    public Iterable<CategoryDTO> findAllCategory(){
        return categoryService.findByAll();
    }

    @GetMapping("api/v1/categories/{id}")
    @ApiOperation(value = "API to get category by Id.", notes = "This is a notes section.")
    public CategoryDTO findAllCategory(@PathVariable Long id){
        return categoryService.findById(id);
    }

    @PostMapping("api/v1/categories")
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "API to create category.", notes = "This is a notes section.")
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO){
        return categoryService.createCategory(categoryDTO);
    }
}
