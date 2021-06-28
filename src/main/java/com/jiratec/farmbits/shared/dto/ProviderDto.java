package com.jiratec.farmbits.shared.dto;



import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProviderDto implements Serializable {

	private static final long serialVersionUID = -1152751167763117809L;
	private long Id;
	private String providerName;
	private String email;
	@Override
	public String toString() {
		return "ProviderDto [Id=" + Id + ", providerName=" + providerName + ", email=" + email + "]";
	}
	
	

}
