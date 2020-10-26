package com.perfectcherry.serviceimpl;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perfectcherry.constant.EmailConstants;
import com.perfectcherry.constant.InterestConstants;
import com.perfectcherry.constant.PerfectCherryConstants;
import com.perfectcherry.constant.RegistrationConstants;
import com.perfectcherry.dto.MailDTO;
import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.entity.UserAccount;
import com.perfectcherry.exception.PerfectCherryException;
import com.perfectcherry.repository.UserAccountRepository;
import com.perfectcherry.service.MailService;
import com.perfectcherry.service.PCEmailService;
import com.perfectcherry.utility.RegistrationUtility;

@Service
@Transactional(readOnly = true)
public class PCEmailServiceImpl implements PCEmailService {

	private Logger logger = LogManager.getLogger(PCEmailServiceImpl.class);

	@Autowired
	private MailService mailService;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Override
	public ResponseEntity<ResponseDTO> forgotPassword(String emailAddress, String password) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Send forgot password mail to emailAddress : %s", emailAddress));
		}
		MailDTO mailDTO = getForgetMailDTO(emailAddress, password);
		try {
			mailService.sendMail(mailDTO);
		} catch (PerfectCherryException e) {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Exception occurred while sending forgot password mail : %s", e.toString()));
			}
			return RegistrationUtility.fillResponseEntity(RegistrationConstants.EMAIL_EXCEPTION_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Password reset mail was sent successfully to emailAddress : %s", emailAddress));
		}
		return RegistrationUtility.fillResponseEntity(RegistrationConstants.EMAIL_SENT_SUCCESSFULLY, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseDTO> resetPasswordMail(String emailAddress) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Send reset password mail to emailAddress : %s", emailAddress));
		}
		MailDTO mailDTO = getResetMailDTO(emailAddress);
		try {
			mailService.sendMail(mailDTO);
		} catch (PerfectCherryException e) {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Exception occurred while sending reset password mail : %s", e.toString()));
			}
			return RegistrationUtility.fillResponseEntity(RegistrationConstants.EMAIL_EXCEPTION_MESSAGE,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(
					String.format("Forgot password mail was sent successfully to emailAddress : %s", emailAddress));
		}
		return RegistrationUtility.fillResponseEntity(RegistrationConstants.EMAIL_SENT_SUCCESSFULLY, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseDTO> sendInterestMail(Long userId, Long interestedOn, String action) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Send Interest mail to interestedOnID : %s", interestedOn));
		}
		Optional<UserAccount> userAccountOptional = userAccountRepository.getActiveUser(userId);
		Optional<UserAccount> interestedOnUserAccountOptional = userAccountRepository.getActiveUser(interestedOn);
		if (userAccountOptional.isPresent() && interestedOnUserAccountOptional.isPresent()) {
			UserAccount userAccount = userAccountOptional.get();
			UserAccount interesteOnUserAccount = interestedOnUserAccountOptional.get();
			MailDTO mailDTO = getMailDTOFactory(userAccount, interesteOnUserAccount, action);
			try {
				mailService.sendMail(mailDTO);
			} catch (PerfectCherryException e) {
				if (logger.isDebugEnabled()) {
					logger.debug(
							String.format("Exception occurred while sending Send-Interest mail : %s", e.toString()));
				}
				return RegistrationUtility.fillResponseEntity(RegistrationConstants.EMAIL_EXCEPTION_MESSAGE,
						HttpStatus.INTERNAL_SERVER_ERROR);
			}
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Send-Interest mail was sent successfully to userID : %s", interestedOn));
			}
			return RegistrationUtility.fillResponseEntity(RegistrationConstants.EMAIL_SENT_SUCCESSFULLY, HttpStatus.OK);
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("No active user is present with userID : %s or interestedOnID: %s", userId,
					interestedOn));
		}
		return RegistrationUtility.fillResponseEntity(RegistrationConstants.NO_ACTIVE_USERS_ID_MESSAGE,
				HttpStatus.BAD_REQUEST);
	}

	private MailDTO getMailDTOFactory(UserAccount userAccount, UserAccount interesteOnUserAccount, String action) {
		MailDTO mailDTO = null;
		switch (action) {
		case InterestConstants.NEW:
			mailDTO = getInterestSentMailDTO(userAccount, interesteOnUserAccount);
			break;
		case InterestConstants.ACCEPT:
			mailDTO = getInterestAcceptMailDTO(userAccount, interesteOnUserAccount);
			break;
		case InterestConstants.DECLINE:
			mailDTO = getInterestDeclineMailDTO(userAccount, interesteOnUserAccount);
			break;
		}
		return mailDTO;
	}

	private MailDTO getForgetMailDTO(String emailAddress, String password) {
		if (logger.isDebugEnabled()) {
			logger.debug("Get forgetMailDTO");
		}
		MailDTO mailDTO = getDefaultMailDTO();
		mailDTO.setMailTo(emailAddress);
		mailDTO.setMailSubject(EmailConstants.FORGOT_PASSWORD_SUBJECT);
		mailDTO.setMailContent("Your password has been changed..!!!\n\nLogin with new password: " + password
				+ "\n\nThanks\nwww.perfectcherry.com");
		return mailDTO;
	}

	private MailDTO getResetMailDTO(String emailAddress) {
		if (logger.isDebugEnabled()) {
			logger.debug("Get resetMailDTO");
		}
		MailDTO mailDTO = getDefaultMailDTO();
		mailDTO.setMailTo(emailAddress);
		mailDTO.setMailSubject(EmailConstants.RESET_PASSWORD_SUBJECT);
		mailDTO.setMailContent(
				"Your password reset was successful..!!!\n\nLogin with new password\n\nThanks\nwww.perfectcherry.com");
		return mailDTO;
	}

	private MailDTO getInterestSentMailDTO(UserAccount userAccount, UserAccount interesteOnUserAccount) {
		if (logger.isDebugEnabled()) {
			logger.debug("Get ineterestSentMailDTO");
		}
		MailDTO mailDTO = getDefaultMailDTO();
		mailDTO.setMailTo(interesteOnUserAccount.getEmailAddress());
		mailDTO.setMailSubject(String.format(EmailConstants.INTEREST_SENT_SUBJECT, userAccount.getUserName()));
		mailDTO.setMailContent(String.format("Yay..!!!%n%nYou got a new interest, login to %s to find out more.%s",
				PerfectCherryConstants.PC_DOMAIN, EmailConstants.EMAIL_SIGNATURE));
		return mailDTO;
	}

	private MailDTO getInterestAcceptMailDTO(UserAccount userAccount, UserAccount interesteOnUserAccount) {
		if (logger.isDebugEnabled()) {
			logger.debug("Get acceptMailDTO");
		}
		MailDTO mailDTO = getDefaultMailDTO();
		mailDTO.setMailTo(interesteOnUserAccount.getEmailAddress());
		mailDTO.setMailSubject(String.format(EmailConstants.INTEREST_SENT_SUBJECT, userAccount.getUserName()));
		mailDTO.setMailContent(String.format("Yay..!!!%n%nYou got a new interest, login to %s to find out more.%s",
				PerfectCherryConstants.PC_DOMAIN, EmailConstants.EMAIL_SIGNATURE));
		return mailDTO;
	}

	private MailDTO getInterestDeclineMailDTO(UserAccount userAccount, UserAccount interesteOnUserAccount) {
		if (logger.isDebugEnabled()) {
			logger.debug("Get declineMailDTO");
		}
		MailDTO mailDTO = getDefaultMailDTO();
		mailDTO.setMailTo(interesteOnUserAccount.getEmailAddress());
		mailDTO.setMailSubject(String.format(EmailConstants.INTEREST_SENT_SUBJECT, userAccount.getUserName()));
		mailDTO.setMailContent(String.format("Yay..!!!%n%nYou got a new interest, login to %s to find out more.%s",
				PerfectCherryConstants.PC_DOMAIN, EmailConstants.EMAIL_SIGNATURE));
		return mailDTO;
	}

	private MailDTO getDefaultMailDTO() {
		MailDTO mailDTO = new MailDTO();
		mailDTO.setMailFrom(EmailConstants.PC_EMAIL_ID);
		return mailDTO;
	}
}
