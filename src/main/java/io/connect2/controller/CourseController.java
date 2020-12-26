package io.connect2.controller;

import java.util.List;

import io.connect2.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.connect2.model.UserMetaData;
import io.connect2.dto.UserMetaDataDTO;
import io.connect2.service.UserMetaDataService;

@CrossOrigin("*")
@RestController
public class CourseController {

    @Autowired
    private UserMetaDataService userMetaDataService;

    @PostMapping("/usermeta")
    public Object addCourse(@RequestBody UserMetaDataDTO userMetaDataDTO) {
        return userMetaDataService.addUserMetaData(userMetaDataDTO);
    }

    @GetMapping("/users")
    public List<UserMetaData> viewAllCourses() {
        return userMetaDataService.getAllUserMetaData();
    }

    @DeleteMapping("/user/{id}")
    public Object deleteCourse(@PathVariable String id) {
        return userMetaDataService.deleteUserMeta(id);
    }

    @GetMapping("user/{id}")
    public Object searchCourse(@PathVariable String id) {
        return userMetaDataService.getUserMetaData(id);

    }

    @PutMapping("user/{id}")
    public void updateCourse(@RequestBody UserMetaDataDTO userMetaDataDTO, @PathVariable String id) {
            userMetaDataService.updateUserMetaData(userMetaDataDTO, id);
    }

}
