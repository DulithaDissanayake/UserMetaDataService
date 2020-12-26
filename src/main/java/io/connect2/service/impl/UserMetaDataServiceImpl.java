package io.connect2.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import io.connect2.dto.ResponseDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import io.connect2.model.UserMetaData;
import io.connect2.dto.UserMetaDataDTO;
import io.connect2.repository.UserMetaDataReporsitory;
import io.connect2.service.UserMetaDataService;

@RestController
public class UserMetaDataServiceImpl implements UserMetaDataService {

	@Autowired
	private UserMetaDataReporsitory userMetaDataReporsitory;

	@Transactional
	public Object addUserMetaData(UserMetaDataDTO userMetaDataDTO) {
		try{
			UserMetaData userMetaData = new UserMetaData();
			BeanUtils.copyProperties(userMetaDataDTO, userMetaData);
			UserMetaData saved = userMetaDataReporsitory.save(userMetaData);
			return saved.getId();
		}catch (Exception e){
			return ResponseDto.builder()
					.status("failed")
					.data("User Meta Data Creation Failed")
					.build();
		}

	}

	@Transactional
	public Object updateUserMetaData(UserMetaDataDTO userMetaDataDTO, String id) {
		try {
			UserMetaData userMetaData = new UserMetaData();
			BeanUtils.copyProperties(userMetaDataDTO, userMetaData);
			UserMetaData saved = userMetaDataReporsitory.save(userMetaData);
			return saved.getId();
		}catch (Exception e){
			return ResponseDto.builder()
					.status("failed")
					.data("User Meta Data Update Failed")
					.build();
		}


	}

	public ResponseDto deleteUserMeta(String id) {
		try {
			userMetaDataReporsitory.deleteById(id);
			return ResponseDto.builder()
					.status("success")
					.data("User Meta Data Deletion Completed")
					.build();
		}catch (Exception e){
			return ResponseDto.builder()
					.status("failed")
					.data("User Meta Data Deletion Failed")
					.build();
		}


	}

	@Transactional
	public Object getUserMetaData(String id) {
		try {
			UserMetaDataDTO userMetaDataDTO = new UserMetaDataDTO();
			UserMetaData userMetaData;
			userMetaData = userMetaDataReporsitory.findById(id).get();
			System.out.println("User Meta : "+userMetaData.toString());
			BeanUtils.copyProperties(userMetaData, userMetaDataDTO);
			return userMetaDataDTO;
		}catch (Exception e){
			return ResponseDto.builder()
					.status("failed")
					.data("Cannot find User Meta data")
					.build();
		}

	}

	@Transactional
	public List<UserMetaData> getAllUserMetaData() {
		try {
			List<UserMetaData> userMetaDataList = new ArrayList<UserMetaData>();
			//List<CourseDto> courseDtoList = new ArrayList<CourseDto>();
			userMetaDataReporsitory.findAll().forEach(userMetaDataList::add);
			return userMetaDataList;
		}catch (Exception e){
			return null;
		}
	}

}
