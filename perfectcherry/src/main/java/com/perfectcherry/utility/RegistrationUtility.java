package com.perfectcherry.utility;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

import com.perfectcherry.constant.RegistrationConstants;
import com.perfectcherry.dto.InterestDTO;
import com.perfectcherry.dto.ResetPasswordDTO;
import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.dto.UserAccountDTO;
import com.perfectcherry.dto.UserDTO;
import com.perfectcherry.entity.Role;
import com.perfectcherry.entity.User;

public final class RegistrationUtility {

	private static Logger logger = LogManager.getLogger(RegistrationUtility.class);

	private RegistrationUtility() {
	}

	private static final String EMAIL_REGEX = "^(.+)@(.+)$";

	private static List<String> genderList = Arrays.asList("Male", "Female", "Other");
	private static List<String> sexualOrientationList = Arrays.asList("Straight", "Lesbian", "Gay", "Bisexual",
			"Transgender", "Questioning");
	private static List<String> interestedInList = Arrays.asList("Male", "Female", "Transgender");

	public static boolean validateUserDTO(UserDTO userDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Validate user details : %s", userDTO.toString()));
		}
		StringBuilder sb = new StringBuilder();
		boolean isValid = true;

		String username = userDTO.getUserName();
		if (isEmpty(username)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USERNAME_REQUIRED_MESSAGE);
			isValid = false;
		}

		String phone = userDTO.getPhone() != null ? userDTO.getPhone().toString() : null;
		if (isEmpty(phone)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_PHONE_REQUIRED_MESSAGE);
			isValid = false;
		} else if (isNotValidLength(phone, RegistrationConstants.USER_PHONE_LENGTH)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_PHONE_LENGTH_VALIDATION_MESSAGE);
			isValid = false;
		}

		String emailaddress = userDTO.getEmailAddress();
		if (isEmpty(emailaddress)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_EMAILADDRESS_REQUIRED_MESSAGE);
			isValid = false;
		} else if (!isEmailValid(emailaddress)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_EMAILADDRESS_VALIDATION_MESSAGE);
			isValid = false;
		}

		String password = userDTO.getPassword();
		if (isEmpty(password)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_PASSWORD_REQUIRED_MESSAGE);
			isValid = false;
		}

		String confirmedPassword = userDTO.getConfirmedPassword();
		if (isEmpty(confirmedPassword)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_CONFIRM_PASSWORD_REQUIRED_MESSAGE);
			isValid = false;
		}
		if (!isEmpty(password) && !isEmpty(confirmedPassword) && !doesPasswordMatch(password, confirmedPassword)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_PASSWORD_VALIDATION_MESSAGE);
			isValid = false;
		}
		if (!isValid) {
			if (sb.length() != 0) {
				sb.append(".");
			}
			userDTO.setErrorMessage(sb.toString());
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Is user details valid : %s", isValid));
		}
		return isValid;
	}

	public static boolean validateUserAccountDTO(UserAccountDTO userAccountDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Validating user account details : %s", userAccountDTO.toString());
		}
		StringBuilder sb = new StringBuilder();
		boolean isValid = true;

		String userId = userAccountDTO.getUserId() != null ? userAccountDTO.getUserId().toString() : null;
		if (isEmpty(userId)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_ID_MESSAGE);
			isValid = false;
		}

		String username = userAccountDTO.getUserName();
		if (isEmpty(username)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USERNAME_REQUIRED_MESSAGE);
			isValid = false;
		}

		String phone = userAccountDTO.getPhone() != null ? userAccountDTO.getPhone().toString() : null;
		if (isEmpty(phone)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_PHONE_REQUIRED_MESSAGE);
			isValid = false;
		} else if (isNotValidLength(phone, RegistrationConstants.USER_PHONE_LENGTH)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_PHONE_LENGTH_VALIDATION_MESSAGE);
			isValid = false;
		}

		String emailaddress = userAccountDTO.getEmailAddress();
		if (isEmpty(emailaddress)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_EMAILADDRESS_REQUIRED_MESSAGE);
			isValid = false;
		} else if (!isEmailValid(emailaddress)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_EMAILADDRESS_VALIDATION_MESSAGE);
			isValid = false;
		}

		String livingIn = userAccountDTO.getLivingIn();
		if (isEmpty(livingIn)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_LIVING_IN_MESSAGE);
			isValid = false;
		}

		String latitude = userAccountDTO.getLatitude() != null ? userAccountDTO.getLatitude().toString() : null;
		if (isEmpty(latitude)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_LOCATION_LATITUDE_MESSAGE);
			isValid = false;
		}

		String langitude = userAccountDTO.getLongitude() != null ? userAccountDTO.getLongitude().toString() : null;
		if (isEmpty(langitude)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_LOCATION_LONGITUDE_MESSAGE);
			isValid = false;
		}

		String dob = userAccountDTO.getDob() != null ? userAccountDTO.getDob().toString() : null;
		if (isEmpty(dob)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_DOB_MESSAGE);
			isValid = false;
		}

		String gender = userAccountDTO.getGender();
		if (isEmpty(gender)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_GENDER_MESSAGE);
			isValid = false;
		} else if (!validateGender(gender)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_GENDER_VALIDATION_MESSAGE);
			isValid = false;
		}

		String sexualOrientation = userAccountDTO.getSexualOrientation();
		if (isEmpty(sexualOrientation)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_SEXUAL_ORIENTATION_MESSAGE);
			isValid = false;
		} else if (!validateSexualOrientation(sexualOrientation)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_SEXUAL_ORIENTATION_VALIDATION_MESSAGE);
			isValid = false;
		}

		String interestedIn = userAccountDTO.getInterestedIn();
		if (isEmpty(interestedIn)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_INTERESTED_IN_MESSAGE);
			isValid = false;
		} else if (!validateInterestedIn(interestedIn)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_INTERESTED_IN_VALIDATION_MESSAGE);
			isValid = false;
		}

		if (!isValid) {
			if (sb.length() != 0) {
				sb.append(".");
			}
			userAccountDTO.setErrorMessage(sb.toString());
		}
		if (logger.isDebugEnabled()) {
			logger.debug("Is user account details valid : %s", isValid);
		}
		return isValid;
	}

	public static boolean validateInterestDTO(InterestDTO interestDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Validate interest details : %s", interestDTO.toString()));
		}
		StringBuilder sb = new StringBuilder();
		boolean isValid = true;

		Long userID = interestDTO.getUserId();
		if (null == userID) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_ID_MESSAGE);
			isValid = false;
		} else if (userID <= 0) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.INVALID_USER_ID_MESSAGE);
			isValid = false;
		}

		Long interestedOnID = interestDTO.getInterestedOn();
		if (null == interestedOnID) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.INTERESTED_ON_ID_MESSAGE);
			isValid = false;
		} else if (interestedOnID <= 0) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.INVALID_INTERESTED_ON_ID_MESSAGE);
			isValid = false;
		}
		if (!isValid) {
			if (sb.length() != 0) {
				sb.append(".");
			}
			interestDTO.setErrorMessage(sb.toString());
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Is interest details valid : %s", isValid));
		}
		return isValid;
	}

	private static boolean validateInterestedIn(String interestedIn) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside validateInterestedIn method");
		}
		return interestedInList.stream().anyMatch(s -> s.equalsIgnoreCase(interestedIn));
	}

	private static boolean validateSexualOrientation(String sexualOrientation) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Is sexual orientation valid : %s", sexualOrientation));
		}
		return sexualOrientationList.stream().anyMatch(s -> s.equalsIgnoreCase(sexualOrientation));
	}

	private static boolean validateGender(String gender) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Is gender valid : %s", gender));
		}
		return genderList.stream().anyMatch(s -> s.equalsIgnoreCase(gender));
	}

	public static boolean isEmpty(String stringValue) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside isEmpty method");
		}
		return stringValue == null || stringValue.trim().isEmpty();
	}

	public static boolean isNotValidLength(String stringValue, int length) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside isNotValidLength method");
		}
		return (!isEmpty(stringValue) && stringValue.trim().length() != length);
	}

	public static boolean isEmailValid(String emailAddress) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Is emailAddress valid : %s", emailAddress));
		}
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(emailAddress);
		return matcher.matches();
	}

	public static boolean doesPasswordMatch(String password, String confirmedPassword) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside doesPasswordMatch method");
		}
		return password.equals(confirmedPassword);
	}

	public static User getDefaultUserProperties(String roleName) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside getDefaultUserProperties method");
		}
		User user = new User();
		List<Role> roles = new ArrayList<>();
		Role role = new Role();
		user.setId(getUniqueID());
		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setCredentialsNonExpired(true);
		user.setEnabled(true);
		role.setId(2L);
		role.setName(roleName);
		roles.add(role);
		user.setRoles(roles);
		return user;
	}

	public static String encryptPassword(String rawPassword) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside encryptPassword method");
		}
		return PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(rawPassword);
	}
	
	public static boolean isPasswordMatching(String rawPassword, String encodedPassword) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside isPasswordMatching method");
		}
		return PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(rawPassword, encodedPassword);
	}

	public static Long getUniqueID() {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside getUniqueID method");
		}
		DateFormat df = new SimpleDateFormat("yy");
		long year = Long.parseLong(df.format(Calendar.getInstance().getTime()) + "00000000");
		long randomNumber = (long) (Math.random() * 100000000L);
		return year + randomNumber;
	}

	public static String getUniquePCID() {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside getUniquePCID method");
		}
		long randomNumber = (long) (Math.random() * 100000000L);
		return RegistrationConstants.PC + randomNumber;
	}

	public static String getUniquePassword() {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside getUniquePassword method");
		}
		int leftLimit = 48; // numeral '0'
		int rightLimit = 122; // letter 'z'
		int targetStringLength = 8;
		return new Random().ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(targetStringLength)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString();
	}

	public static ResponseEntity<ResponseDTO> fillResponseEntity(String message, HttpStatus status) {
		ResponseDTO responseDTO = new ResponseDTO(message);
		return new ResponseEntity<>(responseDTO, status);
	}

	public static boolean validateResetPasswordDTO(ResetPasswordDTO resetPasswordDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Validating resetPassword DTO for userID : %s", resetPasswordDTO.getUserID()));
		}
		StringBuilder sb = new StringBuilder();
		boolean isValid = true;
		
		String oldPassword = resetPasswordDTO.getOldPassword();
		if (isEmpty(oldPassword)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_OLD_PASSWORD_REQUIRED_MESSAGE);
			isValid = false;
		}
		
		String newPassword = resetPasswordDTO.getNewPassword();
		if (isEmpty(newPassword)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_NEW_PASSWORD_REQUIRED_MESSAGE);
			isValid = false;
		}

		String confirmedPassword = resetPasswordDTO.getConfirmPassword();
		if (isEmpty(confirmedPassword)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_CONFIRM_PASSWORD_REQUIRED_MESSAGE);
			isValid = false;
		}
		if (!isEmpty(newPassword) && !isEmpty(confirmedPassword) && !doesPasswordMatch(newPassword, confirmedPassword)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.USER_PASSWORD_VALIDATION_MESSAGE);
			isValid = false;
		}	
		if (!isValid) {
			if (sb.length() != 0) {
				sb.append(".");
			}
			resetPasswordDTO.setErrorMessage(sb.toString());
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Is resetPasswordDTO details valid : %s", isValid));
		}
		return isValid;
	}

}
