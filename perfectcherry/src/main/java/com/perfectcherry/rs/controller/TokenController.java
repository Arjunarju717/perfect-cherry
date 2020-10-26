package com.perfectcherry.rs.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.service.TokenService;

@RestController
public class TokenController {

	private Logger logger = LogManager.getLogger(EmailController.class);

	@Autowired
	private TokenService tokenService;

	@DeleteMapping("user/logout")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<ResponseDTO> logout(HttpServletRequest httpServletRequest) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside logout method");
		}
		return tokenService.revokeToken(httpServletRequest);
	}

}
