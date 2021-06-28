package com.jiratec.farmbits.shared.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Rizauddin Mohammad
 * It is a POJO class which is used to as intermediate object passing from business logic to client  and vice versa
 * 
 */
@Getter
@Setter
public class CategoryDto  implements Serializable{

	private static final long serialVersionUID = -7645772958876780278L;
	private long id;
	private String categoryName;
	@Override
	public String toString() {
		return "CategoryDto [id=" + id + ", categoryName=" + categoryName + "]";
	}
	

}
