package com.perfectcherry.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.perfectcherry.constant.InterestConstants;
import com.perfectcherry.constant.RegistrationConstants;
import com.perfectcherry.dto.InterestBean;
import com.perfectcherry.dto.InterestDTO;
import com.perfectcherry.dto.InterestOutBean;
import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.entity.Interest;
import com.perfectcherry.entity.UserAccount;
import com.perfectcherry.pcenum.InterestEnum;
import com.perfectcherry.repository.InterestRepository;
import com.perfectcherry.repository.UserAccountRepository;
import com.perfectcherry.service.InterestService;
import com.perfectcherry.service.PCEmailService;
import com.perfectcherry.utility.RegistrationUtility;

@Service
@Transactional(readOnly = true)
public class InterestServiceImpl implements InterestService {

	private Logger logger = LogManager.getLogger(InterestServiceImpl.class);

	@Autowired
	private InterestRepository interestRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private PCEmailService pcEmailService;

	@Override
	@Modifying
	@Transactional
	public ResponseEntity<ResponseDTO> saveInterest(InterestDTO interestDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Save interest with request data : %s", interestDTO.toString()));
		}
		if (RegistrationUtility.validateInterestDTO(interestDTO) && validateUserByID(interestDTO)
				&& !isInterestAlreadySent(interestDTO)) {
			Interest interest = fillInterestDetails(interestDTO);
			interestRepository.save(interest);
			pcEmailService.sendInterestMail(interestDTO.getUserId(), interestDTO.getInterestedOn(),
					InterestConstants.NEW);
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Interest Sent : %s", interestDTO.toString()));
			}
			return RegistrationUtility.fillResponseEntity(RegistrationConstants.INTEREST_SENT, HttpStatus.OK);
		} else {
			String errorMessage = interestDTO.getErrorMessage();
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Interest cannot be sent for interest id-%s : %s",
						interestDTO.getInterestId(), errorMessage));
			}
			return RegistrationUtility.fillResponseEntity(errorMessage, HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	@Modifying
	@Transactional
	public ResponseEntity<ResponseDTO> acceptInterest(Long interestID) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Accept interest with interest ID : %s", interestID));
		}
		if (null != interestID && interestID > 0) {
			Optional<Interest> interestOptional = interestRepository.findById(interestID);
			if (interestOptional.isPresent()) {
				Interest interest = fillInterestEntity(interestOptional.get(), InterestEnum.A.toString());
				interestRepository.save(interest);
				pcEmailService.sendInterestMail(interest.getUserId(), interest.getInterestedOn(),
						InterestConstants.ACCEPT);
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("Interest accepted : %s", interestID));
				}
				return RegistrationUtility.fillResponseEntity(RegistrationConstants.INTEREST_ACCEPTED, HttpStatus.OK);
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("Interest not found : %s", interestID));
				}
				return RegistrationUtility.fillResponseEntity(RegistrationConstants.INTEREST_NOT_FOUND,
						HttpStatus.BAD_REQUEST);
			}
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Invalid interest id : %s", interestID));
			}
			return RegistrationUtility.fillResponseEntity(RegistrationConstants.INVALID_INTEREST_ID_MESSAGE,
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	@Modifying
	@Transactional
	public ResponseEntity<ResponseDTO> declineInterest(Long interestID) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Decline interest with interest ID : %s", interestID));
		}
		if (null != interestID && interestID > 0) {
			Optional<Interest> interestOptional = interestRepository.findById(interestID);
			if (interestOptional.isPresent()) {
				Interest interest = fillInterestEntity(interestOptional.get(), InterestEnum.D.toString());
				interestRepository.save(interest);
				pcEmailService.sendInterestMail(interest.getUserId(), interest.getInterestedOn(),
						InterestConstants.DECLINE);
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("Interest Declined : %s", interestID));
				}
				return RegistrationUtility.fillResponseEntity(RegistrationConstants.INTEREST_DECLINED, HttpStatus.OK);
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("Interest not found : %s", interestID));
				}
				return RegistrationUtility.fillResponseEntity(RegistrationConstants.INTEREST_NOT_FOUND,
						HttpStatus.BAD_REQUEST);
			}
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Invalid interest id : %s", interestID));
			}
			return RegistrationUtility.fillResponseEntity(RegistrationConstants.INVALID_INTEREST_ID_MESSAGE,
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	@Modifying
	@Transactional
	public ResponseEntity<ResponseDTO> cancelInterest(Long interestID) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Cancel interest with interest ID : %s", interestID));
		}
		if (null != interestID && interestID > 0) {
			Optional<Interest> interestOptional = interestRepository.findById(interestID);
			if (interestOptional.isPresent()) {
				interestRepository.deleteById(interestID);
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("Interest canceled : %s", interestID));
				}
				return RegistrationUtility.fillResponseEntity(RegistrationConstants.INTEREST_CANCLED, HttpStatus.OK);
			} else {
				if (logger.isDebugEnabled()) {
					logger.debug(String.format("Interest not found : %s", interestID));
				}
				return RegistrationUtility.fillResponseEntity(RegistrationConstants.INTEREST_NOT_FOUND,
						HttpStatus.BAD_REQUEST);
			}

		} else {
			if (logger.isDebugEnabled()) {
				logger.debug(String.format("Invalid interest id : %s", interestID));
			}
			return RegistrationUtility.fillResponseEntity(RegistrationConstants.INVALID_INTEREST_ID_MESSAGE,
					HttpStatus.BAD_REQUEST);
		}
	}

	@Override
	public InterestOutBean interestSent(Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Interests sent by userID : %s", userId));
		}
		InterestOutBean interestOutBean = new InterestOutBean();
		List<InterestBean> interests = new ArrayList<>();
		List<Interest> interestList = interestRepository.interestSent(userId);
		interestList.forEach(interest -> interests
				.add(new InterestBean(interest, userAccountRepository.findById(interest.getInterestedOn()).get())));
		interestOutBean.setInterests(interests);
		return interestOutBean;
	}

	@Override
	public InterestOutBean interestReceived(Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Interests received by userID : %s", userId));
		}
		InterestOutBean interestOutBean = new InterestOutBean();
		List<InterestBean> interests = new ArrayList<>();
		List<Interest> interestList = interestRepository.interestReceived(userId);
		interestList.forEach(interest -> interests
				.add(new InterestBean(interest, userAccountRepository.findById(interest.getUserId()).get())));
		interestOutBean.setInterests(interests);
		return interestOutBean;
	}

	@Override
	public List<UserAccount> interestAcceptedByMe(Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Get Interests accepted by me with userID : %s", userId));
		}
		List<Long> userIds = interestRepository.interestAcceptedByMe(userId);
		return userAccountRepository.findAllById(userIds);
	}

	@Override
	public List<UserAccount> interestAcceptedByThem(Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Get Interests accepted by them with userID : %s", userId));
		}
		List<Long> userIds = interestRepository.interestAcceptedByThem(userId);
		return userAccountRepository.findAllById(userIds);
	}

	@Override
	public List<UserAccount> interestDeclinedByMe(Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Get Interests declined by me with userID : %s", userId));
		}
		List<Long> userIds = interestRepository.interestDeclinedByMe(userId);
		return userAccountRepository.findAllById(userIds);
	}

	@Override
	public List<UserAccount> interestDeclinedByThem(Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Get Interests declined by them with userID : %s", userId));
		}
		List<Long> userIds = interestRepository.interestDeclinedByThem(userId);
		return userAccountRepository.findAllById(userIds);
	}

	private Interest fillInterestEntity(Interest interest, String status) {
		if (logger.isDebugEnabled()) {
			logger.debug("Fill interest entity");
		}
		Date date = new Date();
		interest.setStatus(status);
		interest.setUpdatedDate(date);
		return interest;
	}

	private Interest fillInterestDetails(InterestDTO interestDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Fill interest details");
		}
		Date date = new Date();
		Interest interest = new Interest();
		interest.setInterestId(RegistrationUtility.getUniqueID());
		interest.setUserId(interestDTO.getUserId());
		interest.setInterestedOn(interestDTO.getInterestedOn());
		interest.setStatus(InterestEnum.P.toString());
		interest.setCreatedDate(date);
		interest.setUpdatedDate(date);
		return interest;
	}

	public boolean validateUserByID(InterestDTO interestDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Validating user by ID : %s", interestDTO.getUserId()));
		}
		StringBuilder sb = new StringBuilder();
		boolean isValid = true;

		Long userID = interestDTO.getUserId();
		if (!isUserActive(userID)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.NO_ACTIVE_USER_ID_MESSAGE);
			isValid = false;
		}

		Long interestedOnID = interestDTO.getInterestedOn();
		if (!isUserActive(interestedOnID)) {
			if (sb.length() != 0) {
				sb.append(", ");
			}
			sb.append(RegistrationConstants.NO_ACTIVE_INTERESTED_ON_ID_MESSAGE);
			isValid = false;
		}
		if (!isValid) {
			if (sb.length() != 0) {
				sb.append(".");
			}
			interestDTO.setErrorMessage(sb.toString());
		}
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Is user valid : %s", isValid));
		}
		return isValid;
	}

	public boolean isUserActive(Long userID) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Is user active : %s", userID));
		}
		Optional<UserAccount> userAccountOptional = userAccountRepository.getActiveUser(userID);
		return userAccountOptional.isPresent();
	}

	private boolean isInterestAlreadySent(InterestDTO interestDTO) {
		if (logger.isDebugEnabled()) {
			logger.debug("Check if interest is already sent from user : %s to %s", interestDTO.getUserId(),
					interestDTO.getInterestedOn());
		}
		Optional<Interest> interest = interestRepository.isInterestAlreadySent(interestDTO.getUserId(),
				interestDTO.getInterestedOn());
		if (interest.isPresent()) {
			interestDTO.setErrorMessage(RegistrationConstants.INTEREST_ALREADY_SENT_MESSAGE);
			return true;
		} else {
			return false;
		}
	}

}
