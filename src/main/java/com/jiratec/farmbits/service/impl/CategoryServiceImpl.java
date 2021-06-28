package com.jiratec.farmbits.service.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.jiratec.farmbits.entity.Category;
import com.jiratec.farmbits.repositories.CategoryRepository;
import com.jiratec.farmbits.service.CategoryService;
import com.jiratec.farmbits.shared.dto.CategoryDto;
import com.jiratec.farmbits.util.ConstantUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CategoryServiceImpl  implements CategoryService{
	
	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public String addCategory(String categoryName) {

		try {
			Category categoryEntity = categoryRepository.findByCategoryName(categoryName);
			if (categoryEntity != null) {
				return ConstantUtil.CATEGORY_ALREADY_AVAILABLE;
			}
			Category category = new Category();
			category.setCategoryName(categoryName);
			categoryEntity = categoryRepository.save(category);
			return categoryEntity != null ? ConstantUtil.ADD_SUCCESS : ConstantUtil.FAILURE;

		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.FAILURE, e);
			return ConstantUtil.FAILURE;
		}
	}

	@Override
	public String updateCategory(String categoryNewName, String categoryOldName) {
		try {
			Category categoryEntity = categoryRepository.findByCategoryName(categoryOldName);
			if (categoryEntity == null) {
				return ConstantUtil.CATEGORY_NOT_AVAILABLE;
			}
			categoryEntity.setCategoryName(categoryNewName);
			
			Category category = categoryRepository.save(categoryEntity);
			return category != null ? ConstantUtil.UPDATE_SUCCESS : ConstantUtil.FAILURE;
		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.CATEGORY_NOT_AVAILABLE + "for categoryOldName {} and category {}", categoryOldName, categoryNewName,
					e);
			return ConstantUtil.FAILURE;
		}
	}
	
	@Override
	public String deleteCategory(String categoryName) {
		try {
			Category categoryEntity = categoryRepository.findByCategoryName(categoryName);
			if (categoryEntity == null) {
				return ConstantUtil.CATEGORY_NOT_AVAILABLE;
			}

			categoryRepository.delete(categoryEntity);;
			return ConstantUtil.UPDATE_SUCCESS;
		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.CATEGORY_NOT_AVAILABLE + "for categoryOldName {} and category {}", categoryName, e);
			return ConstantUtil.FAILURE;
		}
	}

	@Override
	public List<CategoryDto> getCategories() {
		List<CategoryDto> returnValues=new ArrayList<CategoryDto>();
		List<Category> categories= (List<Category>) categoryRepository.findAll();
		categories.forEach(i->{
			CategoryDto category=new CategoryDto();
			BeanUtils.copyProperties(i, category);
			returnValues.add(category);
		});
		return returnValues;
	}

	@Override
	public List<CategoryDto> getCategoryByName(String categoryName) {
		List<CategoryDto> returnValue = new ArrayList<>();
		Category categoryEntity = null;
		
		try {
			categoryEntity = categoryRepository.findByCategoryName(categoryName);
			
				CategoryDto category=new CategoryDto();
				BeanUtils.copyProperties(categoryEntity, category);
				returnValue.add(category);
			
			
		} catch (EmptyResultDataAccessException e) {
			log.error(ConstantUtil.CATEGORY_NOT_AVAILABLE + "for category {}", categoryEntity, e);
		}
		return returnValue;
	}

	

}
