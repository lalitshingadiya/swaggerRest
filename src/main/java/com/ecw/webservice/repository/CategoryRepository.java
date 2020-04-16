package com.ecw.webservice.repository;

import com.ecw.webservice.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category,Long> {
}
