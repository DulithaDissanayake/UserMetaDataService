package io.connect2.service;

import java.util.List;

import io.connect2.dto.ResponseDto;
import io.connect2.model.UserMetaData;
import io.connect2.dto.UserMetaDataDTO;

public interface UserMetaDataService {
	Object addUserMetaData(UserMetaDataDTO userMetaDataDTO);
	Object updateUserMetaData(UserMetaDataDTO userMetaDataDTO, String id);
	ResponseDto deleteUserMeta(String id);
	Object getUserMetaData(String id);
	List<UserMetaData> getAllUserMetaData();
}
