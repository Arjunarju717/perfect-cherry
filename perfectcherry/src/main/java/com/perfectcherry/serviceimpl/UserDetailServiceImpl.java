package com.perfectcherry.serviceimpl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perfectcherry.constant.RegistrationConstants;
import com.perfectcherry.entity.AuthUserDetail;
import com.perfectcherry.entity.User;
import com.perfectcherry.repository.UserDetailRepository;

@Service("userDetailsService")
@Transactional(readOnly = true)
public class UserDetailServiceImpl implements UserDetailsService {

	private Logger logger = LogManager.getLogger(UserDetailServiceImpl.class);

	@Autowired
	private UserDetailRepository userDetailRepository;

	@Override
	public UserDetails loadUserByUsername(String name) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Load user by userName : %s", name));
		}
		User user = null;
		UserDetails userDetails = null;
		Optional<User> optionalUser = userDetailRepository.findByUsername(name);
		if (optionalUser.isPresent()) {
			user = optionalUser.get();
			userDetails = new AuthUserDetail(user);
			new AccountStatusUserDetailsChecker().check(userDetails);
		} else {
			throw new UsernameNotFoundException(RegistrationConstants.INVALID_CREDENTIALS_MESSAGE);
		}
		return userDetails;
	}
}