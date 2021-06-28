package com.jiratec.farmbits.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jiratec.farmbits.entity.Category;

/**
 * @author Rizauddin Mohammad
 * It is an interface which is implementing CrudRepository 
 * 
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

	Category findByCategoryName(String categoryName);

}
