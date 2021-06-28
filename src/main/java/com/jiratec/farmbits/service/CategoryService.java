package com.jiratec.farmbits.service;

import java.util.List;

import com.jiratec.farmbits.shared.dto.CategoryDto;

public interface CategoryService {
	String addCategory(String categoryName);
	String updateCategory(String categoryNewName, String categoryOldName);
	List<CategoryDto> getCategories();
	List<CategoryDto> getCategoryByName(String categoryName);
	String deleteCategory(String categoryName);
	

}
