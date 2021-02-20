package com.perfectcherry.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.entity.Image;

public interface ImageService {
	
	public List<Image> getImagesByUserId(Long userId);

	public Image getProfilePhotoByUserId(Long userId);
	
	public ResponseEntity<ResponseDTO> uploadImage(MultipartFile image, Long userID, char isProfilePhoto);

	public List<ResponseEntity<ResponseDTO>> uploadImages(MultipartFile[] image, Long userID);

}
