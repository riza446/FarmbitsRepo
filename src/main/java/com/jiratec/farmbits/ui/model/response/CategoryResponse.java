package com.jiratec.farmbits.ui.model.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Rizauddin Mohammad It is a POJO class which send response to Client
 *         Or UI
 * 
 */
@Getter
@Setter
public class CategoryResponse {

	private String api;
	private String message;

	private List<CategoryDetails> categories;

	public CategoryResponse() {

	}

	public CategoryResponse(String api, String message) {
		super();
		this.api = api;
		this.message = message;

	}

	public CategoryResponse(String api, String message, List<CategoryDetails> categories) {
		super();
		this.api = api;
		this.message = message;
		this.categories = categories;
	}

}
