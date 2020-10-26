package com.perfectcherry.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.perfectcherry.dto.InterestDTO;
import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.entity.UserAccount;

public interface InterestService {
	
	public ResponseEntity<ResponseDTO> saveInterest(InterestDTO interestDTO);

	public ResponseEntity<ResponseDTO> acceptInterest(Long interestID);

	public ResponseEntity<ResponseDTO> declineInterest(Long interestID);
	
	public List<UserAccount> interestSent(Long userId);

	public List<UserAccount> interestReceived(Long userId);
	
	public List<UserAccount> interestAcceptedByMe(Long userId);
	
	public List<UserAccount> interestAcceptedByThem(Long userId);

	public List<UserAccount> interestDeclinedByMe(Long userId);
	
	public List<UserAccount> interestDeclinedByThem(Long userId);
	
}
