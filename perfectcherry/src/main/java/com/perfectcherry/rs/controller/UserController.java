package com.perfectcherry.rs.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfectcherry.dto.ResetPasswordDTO;
import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.dto.UserDTO;
import com.perfectcherry.service.UserService;

@RestController
@RequestMapping("user/")
public class UserController {

	private Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@PostMapping("create")
	public ResponseEntity<ResponseDTO> createUser(@RequestBody UserDTO userDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside createUser method");
		}
		return userService.saveUser(userDTO);
	}

	@DeleteMapping("delete/{userID}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<ResponseDTO> delete(@PathVariable Long userID) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside delete method");
		}
		return userService.deleteUser(userID);
	}

	@PatchMapping("forgotPassword/{userName}")
	public ResponseEntity<ResponseDTO> forgotPassword(@PathVariable String userName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside forgotPassword method");
		}
		return userService.forgotUserPassword(userName);
	}

	@PatchMapping("resetPassword")
	public ResponseEntity<ResponseDTO> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Resetting password for user : %s", resetPasswordDTO.getUserName()));
		}
		return userService.resetUserPassword(resetPasswordDTO);
	}

}
