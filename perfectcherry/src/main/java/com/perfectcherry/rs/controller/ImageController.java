package com.perfectcherry.rs.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.entity.Image;
import com.perfectcherry.service.ImageService;

@RestController
@RequestMapping("image/")
public class ImageController {

	private Logger logger = LogManager.getLogger(ImageController.class);

	@Autowired
	private ImageService imageService;

	@GetMapping("getAllUserImagesById/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<Image> getAllUserImagesById(@PathVariable Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside getAllUserImagesById method");
		}
		return imageService.getImagesByUserId(userId);
	}

	@GetMapping("getProfilePhotoByUserId/{userId}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public Image getProfilePhotoByUserId(@PathVariable Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("Inside getProfilePhotoByUserId method");
		}
		return imageService.getProfilePhotoByUserId(userId);
	}

	@PostMapping("/uploadProfilePhoto/{userID}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public ResponseEntity<ResponseDTO> uploadProfilePhoto(@RequestParam("image") MultipartFile image, @PathVariable Long userID) {
		return imageService.uploadProfilePhoto(image, userID);
	}

	@PostMapping("/uploadImages/{userID}")
	@PreAuthorize("hasRole('ROLE_USER')")
	public List<ResponseEntity<ResponseDTO>> uploadImages(@RequestParam("images") MultipartFile[] images, @PathVariable Long userID) {
		return imageService.uploadImages(images, userID);
	}

}
