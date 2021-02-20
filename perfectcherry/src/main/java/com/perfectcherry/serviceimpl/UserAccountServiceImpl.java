package com.perfectcherry.serviceimpl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perfectcherry.constant.RegistrationConstants;
import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.dto.UserAccountDTO;
import com.perfectcherry.entity.User;
import com.perfectcherry.entity.UserAccount;
import com.perfectcherry.pcenum.ProfilePhoto;
import com.perfectcherry.pcenum.UserStatus;
import com.perfectcherry.repository.UserAccountRepository;
import com.perfectcherry.repository.UserRepository;
import com.perfectcherry.service.UserAccountService;
import com.perfectcherry.service.UserService;
import com.perfectcherry.utility.RegistrationUtility;

@Service
@Transactional(readOnly = true)
public class UserAccountServiceImpl implements UserAccountService {

	private Logger logger = LogManager.getLogger(UserAccountServiceImpl.class);

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserService userService;

	@Override
	@Modifying
	@Transactional
	public ResponseEntity<ResponseDTO> saveUser(UserAccountDTO userAccountDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Updating user account with details : %s", userAccountDTO.toString()));
		}
		UserAccount userAccount = null;
		if (RegistrationUtility.validateUserAccountDTO(userAccountDTO)) {
			Optional<User> userOptional = userRepository.findById(userAccountDTO.getUserId());
			if (userOptional.isPresent() && userOptional.get().getUserAccount() != null
					&& userOptional.get().getUserAccount().getStatus() != ' '
					&& userOptional.get().getUserAccount().getStatus() != 'O') {
				userAccount = userOptional.get().getUserAccount();
				fillModifiedUserAccoutDetails(userAccount, userAccountDTO);
				userAccountRepository.save(userAccount);
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("User updated successfully : %s", userAccountDTO.toString()));
				}
				return RegistrationUtility.fillResponseEntity(RegistrationConstants.USER_UPDATED_SUCCESSFULLY,
						HttpStatus.OK);
			}
			if (logger.isDebugEnabled()) {
				logger.debug(
						String.format("No active user exists with given user ID : %s", userAccountDTO.getUserId()));
			}
			return RegistrationUtility.fillResponseEntity(RegistrationConstants.NO_ACTIVE_USER_ID_MESSAGE,
					HttpStatus.BAD_REQUEST);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Unable to update user details : %s", userAccountDTO.getErrorMessage()));
		}
		return RegistrationUtility.fillResponseEntity(userAccountDTO.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}

	@Override
	@Modifying
	@Transactional
	public ResponseEntity<ResponseDTO> deactivateUser(Long userID) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Deactiving user by userID : %s", userID));
		}
		Date date = new Date();
		userID = getUserID(userID);
		if (userID > 0) {
			Optional<UserAccount> userAccountOptional = userAccountRepository.getActiveUser(userID);
			if (userAccountOptional.isPresent()) {
				UserAccount userAccount = userAccountOptional.get();
				userAccount.setStatus(UserStatus.O.asChar());
				userAccount.setUpdatedDate(date);
				userAccountRepository.save(userAccount);
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("User with Id-%s is deactivated successfully", userID));
				}
				return RegistrationUtility.fillResponseEntity(RegistrationConstants.USER_DEACTIVATED_SUCCESSFULLY,
						HttpStatus.OK);
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("No active user exists with given userID : %s", userID));
				}
				return RegistrationUtility.fillResponseEntity(RegistrationConstants.NO_ACTIVE_USER_ID_MESSAGE,
						HttpStatus.BAD_REQUEST);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Invalid userID : %s", userID));
		}
		return RegistrationUtility.fillResponseEntity(RegistrationConstants.INVALID_USER_ID_MESSAGE,
				HttpStatus.BAD_REQUEST);
	}

	@Override
	@Modifying
	@Transactional
	public ResponseEntity<ResponseDTO> activateUser(Long userID) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Activating user by userID : %s", userID));
		}
		Date date = new Date();
		userID = getUserID(userID);
		if (userID > 0) {
			Optional<UserAccount> userAccountOptional = userAccountRepository.getObsoleteUser(userID);
			if (userAccountOptional.isPresent()) {
				UserAccount userAccount = userAccountOptional.get();
				userAccount.setStatus(UserStatus.A.asChar());
				userAccount.setUpdatedDate(date);
				userAccountRepository.save(userAccount);
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("User with userID-%s activated successfully", userID));
				}
				return RegistrationUtility.fillResponseEntity(RegistrationConstants.USER_ACTIVATED_SUCCESSFULLY,
						HttpStatus.OK);
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("No active user exists with userID : %s", userID));
				}
				return RegistrationUtility.fillResponseEntity(RegistrationConstants.NO_INACTIVE_USER_ID_MESSAGE,
						HttpStatus.BAD_REQUEST);
			}
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Invalid userID : %s", userID));
		}
		return RegistrationUtility.fillResponseEntity(RegistrationConstants.INVALID_USER_ID_MESSAGE,
				HttpStatus.BAD_REQUEST);
	}

	@Override
	public UserAccount getAllUserData(Long userID) {
		if (logger.isDebugEnabled()) {
			logger.debug("Get all user data");
		}
		userID = getUserID(userID);
		Optional<UserAccount> optionalUser = userAccountRepository.findById(userID);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		}
		return null;
	}

	@Override
	public UserAccount getUserData(Long userID) {
		if (logger.isDebugEnabled()) {
			logger.debug("Get user data by userID");
		}
		userID = getUserID(userID);
		Optional<UserAccount> optionalUser = userAccountRepository.findById(userID);
		if (optionalUser.isPresent()) {
			optionalUser.get().setImage(optionalUser.get().getImage().stream()
					.filter(s -> s.getIsProfilePhoto() == ProfilePhoto.Y.asChar()).collect(Collectors.toList()));

			return optionalUser.get();
		}
		return null;
	}

	@Override
	public List<UserAccount> findPeopleNearMe() {
		if (logger.isDebugEnabled()) {
			logger.debug("Find people near me");
		}
		List<UserAccount> userAccountList = null;
		User user = userService.getUserFromToken();
		if (user != null) {
			Optional<UserAccount> optionalUser = userAccountRepository.findById(user.getId());
			if (optionalUser.isPresent()) {
				UserAccount userAccount = optionalUser.get();
				userAccountList = userAccountRepository.findPeopleNearMe(userAccount.getUserAccountId(),
						userAccount.getLatitude(), userAccount.getLongitude(), userAccount.getPeopleWithinKm());
				return userAccountList.parallelStream()
						.filter(ua -> ua.getGender().equals(userAccount.getInterestedIn()))
						.collect(Collectors.toList());
			}
		}
		return Collections.<UserAccount>emptyList();
	}

	private Long getUserID(Long userID) {
		if (userID == null) {
			User user = userService.getUserFromToken();
			if (user != null) {
				userID = user.getId();
			}
		}
		return userID;
	}

	private UserAccount fillModifiedUserAccoutDetails(UserAccount userAccount, UserAccountDTO userAccountDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Find modified user accout details");
		}
		userAccount.setAboutMe(userAccountDTO.getAboutMe());
		userAccount.setCompany(userAccountDTO.getCompany());
		userAccount.setDob(userAccountDTO.getDob());
		userAccount.setEducation(userAccountDTO.getEducation());
		userAccount.setEmailAddress(userAccountDTO.getEmailAddress());
		userAccount.setGender(userAccountDTO.getGender());
		userAccount.setJobTitle(userAccountDTO.getJobTitle());
		userAccount.setLongitude(userAccountDTO.getLongitude());
		userAccount.setLatitude(userAccountDTO.getLatitude());
		userAccount.setLivingIn(userAccountDTO.getLivingIn());
		userAccount.setPhone(userAccountDTO.getPhone());
		userAccount.setSexualOrientation(userAccountDTO.getSexualOrientation());
		userAccount.setUpdatedDate(new Date());
		userAccount.setInterestedIn(userAccountDTO.getInterestedIn());
		userAccount.setPeopleWithinKm(userAccountDTO.getPeopleWithinKm());
		userAccount.setProfileUpdated(true);
		userAccount.setUserName(userAccountDTO.getUserName());
		return userAccount;
	}
}
