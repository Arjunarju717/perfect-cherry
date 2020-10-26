package com.perfectcherry.serviceimpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.perfectcherry.service.ImageService;
import com.perfectcherry.utility.RegistrationUtility;

@Service
@Transactional(readOnly = true)
public class ImageServiceImpl implements ImageService {

	private static final Logger logger = LogManager.getLogger(ImageServiceImpl.class);

	private final Path root = Paths.get("users");

	@Override
	public void init() {
		try {
			Files.createDirectory(root);
		} catch (IOException e) {
			throw new RuntimeException("Could not initialize folder for upload!");
		}
	}
	
	 @Value("${upload.path}")
	 private String path;

	@Autowired
	private ImagesRepository imagesRepository;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Override
	public List<Image> getImagesByUserId(Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Fetch user images by ID : %s", userId));
		}
		return imagesRepository.getAllImagesByUserId(userId);
	}

	@Override
	public Image getProfilePhotoByUserId(Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug(String.format("Fetch profile photo by ID : %s", userId));
		}
		return imagesRepository.getProfilePhotoByUserId(userId);
	}

	@Override
	@Modifying
	@Transactional
	public ResponseEntity<ResponseDTO> uploadProfilePhoto(MultipartFile image, Long userID) {
		Optional<UserAccount> userAccountOptional = userAccountRepository.getActiveUser(userID);
		if (userAccountOptional.isPresent()) {
			UserAccount userAccount = userAccountOptional.get();
			return uploadImage(image, userAccount, ImageConstants.IS_PROFILE_PHOTO);
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
					.map(image -> uploadImage(image, userAccount, ImageConstants.IS_NOT_PROFILE_PHOTO))
					.collect(Collectors.toList());
		}
		throw new NoActiveUserFoundException(RegistrationConstants.NO_ACTIVE_USER_ID_MESSAGE);
	}

	private ResponseEntity<ResponseDTO> uploadImage(MultipartFile image, final UserAccount userAccount,
			char isProfilePhoto) {
		String imageName = StringUtils.cleanPath(image.getOriginalFilename());
		try {
			if (imageName.contains("..")) {
				return RegistrationUtility.fillResponseEntity(
						String.format(ImageConstants.INVALID_IMAGE_NAME, imageName), HttpStatus.BAD_REQUEST);
			}
			Image imageEntity = fillImageData(image, imageName, userAccount, isProfilePhoto);
			Files.copy(image.getInputStream(), this.root.resolve(image.getOriginalFilename()));
			Files.copy(image.getInputStream(), Paths.get(path + image.getOriginalFilename()),
                    StandardCopyOption.REPLACE_EXISTING);
			imagesRepository.save(imageEntity);
			return RegistrationUtility.fillResponseEntity(String.format(ImageConstants.IMAGE_UPLOAD_SUCCESS, imageName),
					HttpStatus.OK);
		} catch (IOException ex) {
			return RegistrationUtility.fillResponseEntity(
					String.format(ImageConstants.IMAGE_UPLOAD_EXCEPTION, imageName), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private Image fillImageData(MultipartFile image, String imageName, UserAccount userAccount, char isProfilePhoto)
			throws IOException {
		Date date = new Date();
		Image imageEntity = new Image();
		imageEntity.setImageId(RegistrationUtility.getUniqueID());
		imageEntity.setImageData(image.getBytes());
		imageEntity.setImageName(imageName);
		imageEntity.setImageType(image.getContentType());
		imageEntity.setUserAccount(userAccount);
		imageEntity.setIsProfilePhoto(isProfilePhoto);
		imageEntity.setCreatedDate(date);
		imageEntity.setUpdatedDate(date);
		return imageEntity;
	}

}
