package com.perfectcherry.rs.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfectcherry.dto.InterestDTO;
import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.entity.UserAccount;
import com.perfectcherry.service.InterestService;

@RestController
@RequestMapping("interest/")
public class InterestController {

	private Logger logger = LogManager.getLogger(InterestController.class);

	@Autowired
	private InterestService interestService;

	@PostMapping("sendInterest")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<ResponseDTO> sendInterest(@RequestBody InterestDTO interestDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside sendInterest method");
		}
		return interestService.saveInterest(interestDTO);
	}

	@PatchMapping("acceptInterest/{interestID}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<ResponseDTO> acceptInterest(@PathVariable Long interestID) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside acceptInterest method");
		}
		return interestService.acceptInterest(interestID);
	}

	@PatchMapping("declineInterest/{interestID}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<ResponseDTO> declineInterest(@PathVariable Long interestID) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside declineInterest method");
		}
		return interestService.declineInterest(interestID);
	}

	@GetMapping("interestSent/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<UserAccount> interestSent(@PathVariable Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside interestSent method");
		}
		return interestService.interestSent(userId);
	}

	@GetMapping("interestReceived/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<UserAccount> interestReceived(@PathVariable Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside interestReceived method");
		}
		return interestService.interestReceived(userId);
	}

	@GetMapping("interestAcceptedByMe/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<UserAccount> interestAcceptedByMe(@PathVariable Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside interestAcceptedByMe method");
		}
		return interestService.interestAcceptedByMe(userId);
	}

	@GetMapping("interestAcceptedByThem/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<UserAccount> interestAcceptedByThem(@PathVariable Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside interestAcceptedByThem method");
		}
		return interestService.interestAcceptedByThem(userId);
	}

	@GetMapping("interestDeclinedByMe/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<UserAccount> interestDeclinedByMe(@PathVariable Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside interestDeclinedByMe method");
		}
		return interestService.interestDeclinedByMe(userId);
	}

	@GetMapping("interestDeclinedByThem/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<UserAccount> interestDeclinedByThem(@PathVariable Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside interestDeclinedByThem method");
		}
		return interestService.interestDeclinedByThem(userId);
	}

}
