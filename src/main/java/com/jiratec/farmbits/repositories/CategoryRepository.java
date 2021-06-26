package com.jiratec.farmbits.repositories;

import org.springframework.data.repository.CrudRepository;

import com.jiratec.farmbits.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Long> {

	Category findByCategoryName(String categoryName);

}
