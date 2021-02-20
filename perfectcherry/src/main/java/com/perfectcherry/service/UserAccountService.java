package com.perfectcherry.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.dto.UserAccountDTO;
import com.perfectcherry.entity.UserAccount;

public interface UserAccountService {

	public ResponseEntity<ResponseDTO> saveUser(UserAccountDTO userAccountDTO);

	public UserAccount getAllUserData(Long userID);

	public UserAccount getUserData(Long userID);

	public ResponseEntity<ResponseDTO> deactivateUser(Long userID);

	public ResponseEntity<ResponseDTO> activateUser(Long userID);

	public List<UserAccount> findPeopleNearMe();

}
