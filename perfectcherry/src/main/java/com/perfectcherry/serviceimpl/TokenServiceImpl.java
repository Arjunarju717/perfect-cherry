package com.perfectcherry.serviceimpl;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import com.perfectcherry.constant.RegistrationConstants;
import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.service.TokenService;
import com.perfectcherry.utility.RegistrationUtility;

@Service
public class TokenServiceImpl implements TokenService {

	private Logger logger = LogManager.getLogger(TokenServiceImpl.class);

	private static final String AUTHORIZATION = "Authorization";

	private static final String BEARER = "bearer";

	@Autowired
	private TokenStore tokenStore;

	@Override
	public ResponseEntity<ResponseDTO> revokeToken(HttpServletRequest httpServletRequest) {
		if (logger.isDebugEnabled()) {
			logger.debug("Revoking Token");
		}
		if (httpServletRequest != null && httpServletRequest.getHeader(AUTHORIZATION) != null
				&& httpServletRequest.getHeader(AUTHORIZATION).contains(BEARER)) {
			String tokenID = httpServletRequest.getHeader(AUTHORIZATION).substring(BEARER.length() + 1);
			OAuth2AccessToken accessToken = tokenStore.readAccessToken(tokenID);
			OAuth2RefreshToken refreshToken = accessToken.getRefreshToken();
			tokenStore.removeAccessToken(accessToken);
			tokenStore.removeRefreshToken(refreshToken);
			if (logger.isDebugEnabled()) {
				logger.debug("Revoked token successfully");
			}
			return RegistrationUtility.fillResponseEntity(RegistrationConstants.LOGOUT_SUCCESS_MESSAGE, HttpStatus.OK);
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Revokation of token is unsuccessful");
		}
		return RegistrationUtility.fillResponseEntity(RegistrationConstants.LOGOUT_FAILURE_MESSAGE,
				HttpStatus.BAD_REQUEST);
	}

}
