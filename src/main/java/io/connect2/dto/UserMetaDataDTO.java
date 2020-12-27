package io.connect2.dto;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class UserMetaDataDTO implements Serializable {

	private String id;
	private String businessName;
	private String contactOne;
	private String contactTwo;
	private String companyLogoUrl;
	private String district;
	private String town;
	

	
}
