package com.perfectcherry.iauthenticationimpl;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.perfectcherry.iauthentication.IAuthenticationFacade;

@Component
public class IAuthenticationFacadeImpl implements IAuthenticationFacade{

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();	
	}

}
