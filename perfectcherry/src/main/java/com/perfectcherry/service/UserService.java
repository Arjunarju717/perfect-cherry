package com.perfectcherry.service;

import org.springframework.http.ResponseEntity;

import com.perfectcherry.dto.ResetPasswordDTO;
import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.dto.UserDTO;
import com.perfectcherry.entity.User;

public interface UserService {
	
	public ResponseEntity<ResponseDTO> saveUser(UserDTO userDTO);
	
	public ResponseEntity<ResponseDTO> deleteUser(Long userID);
	
	public ResponseEntity<ResponseDTO> resetUserPassword(ResetPasswordDTO resetPasswordDTO);

	public ResponseEntity<ResponseDTO> forgotUserPassword(String userName);
	
	public ResponseEntity<ResponseDTO> isPhoneRegistered(String region, String phoneNumber);
	
	public User getUserFromToken();
	
}
