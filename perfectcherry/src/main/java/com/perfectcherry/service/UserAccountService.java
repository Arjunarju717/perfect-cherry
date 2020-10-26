package com.perfectcherry.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.dto.UserAccountDTO;
import com.perfectcherry.entity.UserAccount;

public interface UserAccountService {
	
	public ResponseEntity<ResponseDTO> saveUser(UserAccountDTO userAccountDTO);
	
	public UserAccount getAllUserDataById(Long userid);
	
	public UserAccount getUserDataById(Long userid);
	
	public ResponseEntity<ResponseDTO> deactivateUser(Long userID);

	public ResponseEntity<ResponseDTO> activateUser(Long userID);
	
	public List<UserAccount> findPeopleNearMe(Long userid);
	
	
}
