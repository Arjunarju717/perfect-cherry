package com.perfectcherry.serviceimpl;

import java.util.Arrays;
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
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.perfectcherry.constant.ImageConstants;
import com.perfectcherry.constant.RegistrationConstants;
import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.entity.Image;
import com.perfectcherry.entity.UserAccount;
import com.perfectcherry.exception.NoActiveUserFoundException;
import com.perfectcherry.repository.ImagesRepository;
import com.perfectcherry.repository.UserAccountRepository;
import com.perfectcherry.service.AWSS3Service;
import com.perfectcherry.service.ImageService;
import com.perfectcherry.utility.PerfectCherryUtility;
import com.perfectcherry.utility.RegistrationUtility;

@Service
@Transactional(readOnly = true)
public class ImageServiceImpl implements ImageService {

	private static final Logger logger = LogManager.getLogger(ImageServiceImpl.class);

	@Autowired
	private ImagesRepository imagesRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private AWSS3Service awsS3Service;

	@Override
	public List<Image> getImagesByUserId(Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Fetch user images by ID : %s", userId));
		}
		Optional<UserAccount> userAccountOptional = userAccountRepository.getActiveUser(userId);
		if (userAccountOptional.isPresent()) {
			return imagesRepository.getAllImagesByUserId(userId);
		}
		throw new NoActiveUserFoundException(RegistrationConstants.NO_ACTIVE_USER_ID_MESSAGE);
	}

	@Override
	public Image getProfilePhotoByUserId(Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Fetch profile photo by ID : %s", userId));
		}
		Optional<UserAccount> userAccountOptional = userAccountRepository.getActiveUser(userId);
		if (userAccountOptional.isPresent()) {
			return imagesRepository.getProfilePhotoByUserId(userId);
		}
		throw new NoActiveUserFoundException(RegistrationConstants.NO_ACTIVE_USER_ID_MESSAGE);
		
	}

	@Override
	@Modifying
	@Transactional
	public ResponseEntity<ResponseDTO> uploadImage(MultipartFile image, Long userID, char isProfilePhoto) {
		Optional<UserAccount> userAccountOptional = userAccountRepository.getActiveUser(userID);
		if (userAccountOptional.isPresent()) {
			UserAccount userAccount = userAccountOptional.get();
			return upload(image, userAccount, isProfilePhoto);
		}
		throw new NoActiveUserFoundException(RegistrationConstants.NO_ACTIVE_USER_ID_MESSAGE);
	}

	@Override
	@Modifying
	@Transactional
	public List<ResponseEntity<ResponseDTO>> uploadImages(MultipartFile[] images, Long userID) {
		Optional<UserAccount> userAccountOptional = userAccountRepository.getActiveUser(userID);
		if (userAccountOptional.isPresent()) {
			UserAccount userAccount = userAccountOptional.get();
			return Arrays.asList(images).stream()
					.map(image -> upload(image, userAccount, ImageConstants.IS_NOT_PROFILE_PHOTO))
					.collect(Collectors.toList());
		}
		throw new NoActiveUserFoundException(RegistrationConstants.NO_ACTIVE_USER_ID_MESSAGE);
	}

	private ResponseEntity<ResponseDTO> upload(MultipartFile multipartImageFile, final UserAccount userAccount,
			char isProfilePhoto) {
		String imageName = StringUtils.cleanPath(multipartImageFile.getOriginalFilename());
		try {
			if (imageName.contains("..")) {
				return RegistrationUtility.fillResponseEntity(
						String.format(ImageConstants.INVALID_IMAGE_NAME, imageName), HttpStatus.BAD_REQUEST);
			}
			Image imageEntity = fillImageData(multipartImageFile, imageName, userAccount, isProfilePhoto);
			awsS3Service.uploadImageToS3Bucket(multipartImageFile, imageEntity.getImageName());
			imagesRepository.save(imageEntity);
			return RegistrationUtility.fillResponseEntity(String.format(ImageConstants.IMAGE_UPLOAD_SUCCESS, imageName),
					HttpStatus.OK);
		} catch (Exception ex) {
			return RegistrationUtility.fillResponseEntity(
					String.format(ImageConstants.IMAGE_UPLOAD_EXCEPTION, imageName), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private Image fillImageData(MultipartFile multipartImageFile, String imageName, UserAccount userAccount,
			char isProfilePhoto) {
		Date date = new Date();
		String uniqueImageName = PerfectCherryUtility.getUniqueImageName(imageName);
		Image imageEntity = new Image();
		imageEntity.setImageId(RegistrationUtility.getUniqueID());
		imageEntity.setImageURL(PerfectCherryUtility.getImageURL(uniqueImageName));
		imageEntity.setImageName(uniqueImageName);
		imageEntity.setImageType(multipartImageFile.getContentType());
		imageEntity.setUserAccount(userAccount);
		imageEntity.setIsProfilePhoto(isProfilePhoto);
		imageEntity.setCreatedDate(date);
		imageEntity.setUpdatedDate(date);
		return imageEntity;
	}

}
