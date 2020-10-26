package com.perfectcherry.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.perfectcherry.dto.ResponseDTO;
import com.perfectcherry.entity.Image;

public interface ImageService {
	
	public void init();

	public List<Image> getImagesByUserId(Long userId);

	public Image getProfilePhotoByUserId(Long userId);
	
	public ResponseEntity<ResponseDTO> uploadProfilePhoto(MultipartFile image, Long userID);

	public List<ResponseEntity<ResponseDTO>> uploadImages(MultipartFile[] image, Long userID);

}
