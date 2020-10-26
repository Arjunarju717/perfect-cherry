package com.perfectcherry.serviceimpl;

import java.util.Date;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perfectcherry.constant.RegistrationConstants;
import com.perfectcherry.dto.ResetPasswordDTO;
import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.dto.UserDTO;
import com.perfectcherry.entity.User;
import com.perfectcherry.entity.UserAccount;
import com.perfectcherry.pcenum.UserRole;
import com.perfectcherry.pcenum.UserStatus;
import com.perfectcherry.repository.UserRepository;
import com.perfectcherry.service.PCEmailService;
import com.perfectcherry.service.UserService;
import com.perfectcherry.utility.RegistrationUtility;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

	private Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PCEmailService pcEmailService;

	@Override
	@Modifying
	@Transactional
	public ResponseEntity<ResponseDTO> saveUser(UserDTO userDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Create new user with details : %s", userDTO.toString()));
		}
		User user = null;
		if (RegistrationUtility.validateUserDTO(userDTO) && !isUserRegistered(userDTO)) {
			user = RegistrationUtility.getDefaultUserProperties(UserRole.U.toString());
			String password = RegistrationUtility.encryptPassword(userDTO.getPassword());
			UserAccount userAccount = fillUserAccount(userDTO, user.getId());
			user.setUsername(userDTO.getPhone().toString());
			user.setPassword(password);
			userAccount.setUser(user);
			user.setUserAccount(userAccount);
			userRepository.save(user);
			if (logger.isDebugEnabled()) {
				logger.debug("User created successfully : " + user.toString());
			}
			return RegistrationUtility.fillResponseEntity(RegistrationConstants.USER_CREATED_SUCCESSFULLY,
					HttpStatus.CREATED);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Unable to create user : %s", userDTO.getErrorMessage()));
		}
		return RegistrationUtility.fillResponseEntity(userDTO.getErrorMessage(), HttpStatus.BAD_REQUEST);
	}

	@Override
	@Modifying
	@Transactional
	public ResponseEntity<ResponseDTO> deleteUser(Long userID) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Delete user with userID : %s", userID));
		}
		Optional<User> userOptional = userRepository.findById(userID);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			userRepository.delete(user);
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("User with userID-%s deleted successfully", userID));
			}
			return RegistrationUtility.fillResponseEntity(RegistrationConstants.USER_DELETED_SUCCESSFULLY,
					HttpStatus.OK);
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("No user exists with given userID : %s", userID));
			}
			return RegistrationUtility.fillResponseEntity(RegistrationConstants.NO_USER_ID_MESSAGE,
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	@Modifying
	@Transactional
	public ResponseEntity<ResponseDTO> resetUserPassword(ResetPasswordDTO resetPasswordDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Inside resetPassword method for userID : %s", resetPasswordDTO.getUserID()));
		}
		if (RegistrationUtility.validateResetPasswordDTO(resetPasswordDTO)) {
			Optional<User> userOptional = userRepository.findById(resetPasswordDTO.getUserID());
			if (userOptional.isPresent()) {
				User user = userOptional.get();
				if (RegistrationUtility.isPasswordMatching(resetPasswordDTO.getOldPassword(), user.getPassword())) {
					String newEncryptedPassword = RegistrationUtility
							.encryptPassword(resetPasswordDTO.getNewPassword());
					user.setPassword(newEncryptedPassword);
					user.getUserAccount().setUpdatedDate(new Date());
					userRepository.save(user);
					pcEmailService.resetPasswordMail(user.getUserAccount().getEmailAddress());
					if (logger.isDebugEnabled()) {
						logger.debug(String.format("Password reset was successful for user : %s",
								resetPasswordDTO.getUserID()));
					}
					return RegistrationUtility.fillResponseEntity(RegistrationConstants.PASSWORD_RESET_SUCCESS_MESSAGE,
							HttpStatus.OK);
				} else {
					if (logger.isDebugEnabled()) {
						logger.debug(String.format("Old password is incorrect : %s", resetPasswordDTO.getUserID()));
					}
					return RegistrationUtility.fillResponseEntity(
							RegistrationConstants.USER_OLD_PASSWORD_INCORRECT_MESSAGE, HttpStatus.BAD_REQUEST);
				}
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("No user found with userID : %s", resetPasswordDTO.getUserID()));
				}
				return RegistrationUtility.fillResponseEntity(RegistrationConstants.NO_USER_ID_MESSAGE,
						HttpStatus.BAD_REQUEST);
			}
		}
		String errorMessage = resetPasswordDTO.getErrorMessage();
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("PasswordResetDTO validation fails : %s", errorMessage));
		}
		return RegistrationUtility.fillResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@Override
	@Modifying
	@Transactional
	public ResponseEntity<ResponseDTO> forgotUserPassword(String userName) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Send password reset mail to user : %s", userName));
		}
		if (null != userName) {
			Optional<User> userOptional = userRepository.findUserByName(userName);
			if (userOptional.isPresent()) {
				User user = userOptional.get();
				if (null != user.getUserAccount().getEmailAddress()) {
					String password = RegistrationUtility.getUniquePassword();
					String encryptedPassword = RegistrationUtility.encryptPassword(password);
					user.setPassword(encryptedPassword);
					user.getUserAccount().setUpdatedDate(new Date());
					userRepository.save(user);
					ResponseEntity<ResponseDTO> emailResponse = pcEmailService
							.forgotPassword(user.getUserAccount().getEmailAddress(), password);
					if (emailResponse.getStatusCodeValue() != 200) {
						return emailResponse;
					}
					if (logger.isDebugEnabled()) {
						logger.debug(String.format("Password reset mail sent successfully to user : %s", userName));
					}
					return RegistrationUtility.fillResponseEntity(RegistrationConstants.EMAIL_SENT_SUCCESSFULLY,
							HttpStatus.OK);
				} else {
					if (logger.isDebugEnabled()) {
						logger.debug(String.format("Email address is not available for user : %s", userName));
					}
					return RegistrationUtility.fillResponseEntity(RegistrationConstants.EMAILADDRESS_NOT_AVAILABLE,
							HttpStatus.BAD_REQUEST);
				}
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("No user exists with userName : %s", userName));
				}
				return RegistrationUtility.fillResponseEntity(RegistrationConstants.NO_USER_ID_MESSAGE,
						HttpStatus.BAD_REQUEST);
			}
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("User name is required : %s", userName));
			}
			return RegistrationUtility.fillResponseEntity(RegistrationConstants.USERNAME_REQUIRED_MESSAGE,
					HttpStatus.BAD_REQUEST);
		}
	}

	public UserAccount fillUserAccount(UserDTO userDTO, Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Fill user account details for userID : %s", userId));
		}
		Date date = new Date();
		UserAccount userAccount = new UserAccount();
		userAccount.setUserAccountId(userId);
		userAccount.setPcId(RegistrationUtility.getUniquePCID());
		userAccount.setCreatedDate(date);
		userAccount.setEmailAddress(userDTO.getEmailAddress());
		userAccount.setStatus(UserStatus.A.asChar());
		userAccount.setUpdatedDate(date);
		userAccount.setUserName(userDTO.getUserName());
		userAccount.setPhone(userDTO.getPhone());
		userAccount.setProfileUpdated(false);
		return userAccount;
	}

	public boolean isUserRegistered(UserDTO userDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Check if user is registered");
		}
		Optional<User> userOptional = userRepository.findUserByName(userDTO.getPhone().toString());
		if (userOptional.isPresent()) {
			userDTO.setErrorMessage(RegistrationConstants.USER_ALREADY_REGISTERED);
			return true;
		}
		return false;
	}
}
