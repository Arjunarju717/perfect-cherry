package com.perfectcherry.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import com.perfectcherry.dto.ResponseDTO;

public interface TokenService {

	public ResponseEntity<ResponseDTO> revokeToken(HttpServletRequest httpServletRequest);

}
