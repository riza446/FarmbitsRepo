package com.jiratec.farmbits.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "provider")
@Getter
@Setter
public class Provider implements Serializable {

	private static final long serialVersionUID = -8972483857355112104L;

	@Id
	@GeneratedValue
	private long Id;

	@Column(nullable = false, length = 50, unique = true)
	private String providerName;

	@Column(nullable = false, length = 150, unique = true)
	private String email;

	@ManyToMany(mappedBy = "provider", fetch = FetchType.LAZY)
	private Set<Product> products = new HashSet<>();

	public Provider() {
	}

	public Provider(String providerName, String email) {

		this.providerName = providerName;
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (Id ^ (Id >>> 32));
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((providerName == null) ? 0 : providerName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Provider other = (Provider) obj;
		if (Id != other.Id)
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (providerName == null) {
			if (other.providerName != null)
				return false;
		} else if (!providerName.equals(other.providerName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Provider [Id=" + Id + ", providerName=" + providerName + ", email=" + email + "]";
	}

}
