package com.perfectcherry.rs.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.dto.UserAccountDTO;
import com.perfectcherry.entity.UserAccount;
import com.perfectcherry.service.UserAccountService;

@RestController
@RequestMapping("userAccount/")
public class UserAccountController {

	private Logger logger = LogManager.getLogger(UserAccountController.class);

	@Autowired
	private UserAccountService userAccountService;

	@PatchMapping("update")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<ResponseDTO> userAccount(@RequestBody UserAccountDTO userAccountDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside userAccount method");
		}
		return userAccountService.saveUser(userAccountDTO);
	}

	@GetMapping("getAllUserData")
	@PreAuthorize("hasRole('ROLE_USER')")
	public UserAccount getAllUserData(Authentication authentication) {
		String arj = authentication.getName();
		Object obj= authentication.getPrincipal();
		if (logger.isDebugEnabled()) {
			logger.debug("Inside getAllUserData method");
		}
		return userAccountService.getAllUserData(null);
	}

	@GetMapping("getUserData")
	@PreAuthorize("hasRole('ROLE_USER')")
	public UserAccount getUserData() {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside getUserData method");
		}
		return userAccountService.getUserData(null);
	}
	
	@GetMapping("getAllUserDataById/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public UserAccount getAllUserDataById(@PathVariable Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside getAllUserDataById method");
		}
		return userAccountService.getAllUserData(userId);
	}

	@GetMapping("getUserDataById/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public UserAccount getUserDataById(@PathVariable Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside getUserDataById method");
		}
		return userAccountService.getUserData(userId);
	}

	@GetMapping("findPeopleNearMe")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<UserAccount> findPeopleNearMe() {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside findPeopleNearMe method");
		}
		return userAccountService.findPeopleNearMe();
	}
	
	@PatchMapping("deactivate")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<ResponseDTO> deactivate() {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside deactivate method");
		}
		return userAccountService.deactivateUser(null);
	}

	@PatchMapping("activate")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<ResponseDTO> activate() {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside sendInterest method");
		}
		return userAccountService.activateUser(null);
	}

	@PatchMapping("deactivate/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<ResponseDTO> deactivate(@PathVariable Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside deactivate method");
		}
		return userAccountService.deactivateUser(userId);
	}

	@PatchMapping("activate/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<ResponseDTO> activate(@PathVariable Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside sendInterest method");
		}
		return userAccountService.activateUser(userId);
	}
}
