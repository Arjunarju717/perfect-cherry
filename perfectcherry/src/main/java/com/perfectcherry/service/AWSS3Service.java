package com.perfectcherry.service;

import org.springframework.web.multipart.MultipartFile;

public interface AWSS3Service {
	
	public void uploadImageToS3Bucket(final MultipartFile multipartImageFile, String imageName);

}
