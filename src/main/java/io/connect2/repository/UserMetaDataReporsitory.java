package io.connect2.repository;

import org.springframework.data.repository.CrudRepository;

import io.connect2.model.UserMetaData;

public interface UserMetaDataReporsitory extends CrudRepository<UserMetaData, String>{
}
