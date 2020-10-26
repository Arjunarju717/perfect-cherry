package com.perfectcherry.service;

import org.springframework.http.ResponseEntity;

import com.perfectcherry.dto.ResponseDTO;

public interface PCEmailService {
	
	public ResponseEntity<ResponseDTO> forgotPassword(String emailAddress, String password);
	
	public ResponseEntity<ResponseDTO> resetPasswordMail(String emailAddress);

	public ResponseEntity<ResponseDTO> sendInterestMail(Long userId, Long interestedOn, String action);

}
