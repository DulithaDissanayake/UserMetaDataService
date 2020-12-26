package io.connect2.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class UserMetaData {
	@Id
	private String id;
	private String businessName;
	private String contactOne;
	private String contactTwo;
	private String companyLogoUrl;
	private String district;
	private String town;
	
	
}
