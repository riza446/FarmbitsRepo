package com.jiratec.farmbits.ui.model.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Rizauddin Mohammad
 * It is a POJO class which send response  to Client Or UI
 * 
 */
@Getter
@Setter
public class ProviderResponse {
	
	private String api;
	private String message;
	private List<ProviderDetails> providers;
	
	
	public ProviderResponse() {
		
	}
	public ProviderResponse(String api, String message) {
		super();
		this.api = api;
		this.message = message;
		
	}
	public ProviderResponse(String api, String message,  List<ProviderDetails> providers) {
		super();
		this.api = api;
		this.message = message;
		this.providers = providers;
	}
	

}
