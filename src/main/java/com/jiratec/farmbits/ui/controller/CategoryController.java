package com.jiratec.farmbits.ui.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jiratec.farmbits.service.CategoryService;
import com.jiratec.farmbits.service.ProductService;
import com.jiratec.farmbits.shared.dto.CategoryDto;
import com.jiratec.farmbits.shared.dto.ProductDto;
import com.jiratec.farmbits.ui.model.response.CategoryDetails;
import com.jiratec.farmbits.ui.model.response.CategoryResponse;
import com.jiratec.farmbits.ui.model.response.ProductDetails;
import com.jiratec.farmbits.util.ApiNameUntil;
import com.jiratec.farmbits.util.ConstantUtil;

/**
 * @author Rizauddin Mohmmad
 * This is the controller class which has all the Crud Restapi  calls
 * like adding category, get categories, get category by name, update category  name and delete category
 * 
 *
 */
@RestController
@RequestMapping("/riza-farmbits")
public class CategoryController {

	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;
	
	

	@PostMapping("/addCategory")
	public CategoryResponse addCategory(@RequestParam(value = "categoryName") String categoryName) {

		if (!StringUtils.hasLength(categoryName)) {
			return new CategoryResponse(ApiNameUntil.API_ADD_CATEGORY, ConstantUtil.INPUTS_EMPTY);
		}

		return new CategoryResponse(ApiNameUntil.API_ADD_CATEGORY, categoryService.addCategory(categoryName));

	}

	@PutMapping("/changeCategoryName")
	public CategoryResponse updateCategory(@RequestParam(value = "categoryOldName") String categoryOldName,
			@RequestParam(value = "categoryNewName") String categoryNewName) {
		if (!(StringUtils.hasLength(categoryNewName) && StringUtils.hasLength(categoryOldName))) {
			return new CategoryResponse(ApiNameUntil.API_UPDATE_CATEGORY,
					ConstantUtil.INPUTS_EMPTY);
		}

		return new CategoryResponse(ApiNameUntil.API_UPDATE_CATEGORY,
				categoryService.updateCategory(categoryNewName, categoryOldName));

	}
	
	@DeleteMapping("/deleteCategory")
	public CategoryResponse deleteCategory(@RequestParam(value = "categoryName") String categoryName) {

		if (!StringUtils.hasLength(categoryName)) {
			return new CategoryResponse(ApiNameUntil.API_ADD_CATEGORY, ConstantUtil.INPUTS_EMPTY);
		}

		return new CategoryResponse(ApiNameUntil.API_ADD_CATEGORY, categoryService.deleteCategory(categoryName));

	}

	@GetMapping("/getAllCategories")
	public CategoryResponse getAllCategories() {
		CategoryResponse keyVal;
		List<CategoryDetails> categoryDetails = new ArrayList<CategoryDetails>();

		List<CategoryDto> categories = categoryService.getCategories();
		if (!CollectionUtils.isEmpty(categories)) {
			categories.forEach(i -> {
				CategoryDetails category = new CategoryDetails();
				BeanUtils.copyProperties(i, category);
				categoryDetails.add(category);

			});

			keyVal = new CategoryResponse(ApiNameUntil.API_GET_CATEGORIES, ConstantUtil.SUCCESS, categoryDetails);
		} else
			keyVal = new CategoryResponse(ApiNameUntil.API_GET_CATEGORIES, ConstantUtil.CATEGORY_NOT_AVAILABLE,
					categoryDetails);
		return keyVal;

	}

	@GetMapping("/getCategory/{Id}")
	public CategoryResponse getCategory(@PathVariable("Id") String id) {
		CategoryResponse keyVal = null;
		if (!StringUtils.hasLength(id) )
			return new CategoryResponse(ApiNameUntil.API_GET_PRODUCTS_BY_CATEGORY, ConstantUtil.INPUTS_EMPTY);
		List<CategoryDetails> categoryDetails = new ArrayList<>();

		List<CategoryDto> categories = categoryService.getCategoryByName(id);
		if (!CollectionUtils.isEmpty(categories)) {
			categories.forEach(i -> {
				CategoryDetails category = new CategoryDetails();
				BeanUtils.copyProperties(i, category);
				categoryDetails.add(category);

			});

			keyVal = new CategoryResponse(ApiNameUntil.API_GET_PRODUCTS_BY_CATEGORY, ConstantUtil.SUCCESS,
					categoryDetails);
			return keyVal;
		} else {
			keyVal = new CategoryResponse(ApiNameUntil.API_GET_PRODUCTS_BY_CATEGORY, ConstantUtil.PRODUCT_NOT_AVAILABLE,
					categoryDetails);
			return keyVal;
		}

	}

}
