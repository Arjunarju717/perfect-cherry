package com.perfectcherry.serviceimpl;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.perfectcherry.service.AWSS3Service;
import com.perfectcherry.utility.PerfectCherryUtility;

@Service
public class AWSS3ServiceImpl implements AWSS3Service {

	private static final Logger logger = LogManager.getLogger(AWSS3ServiceImpl.class);

	@Autowired
	private AmazonS3 amazonS3;

	@Async
	@Override
	public void uploadImageToS3Bucket(final MultipartFile multipartImageFile, String imageName) {
		logger.info("Image upload in progress.");
		try {
			final File imagefile = PerfectCherryUtility.convertMultiPartFileToFile(multipartImageFile);
			final PutObjectRequest putObjectRequest = new PutObjectRequest(
					PerfectCherryUtility.getS3ImageBucketFolder(), imageName, imagefile)
							.withCannedAcl(CannedAccessControlList.PublicRead);
			amazonS3.putObject(putObjectRequest);
			logger.info("Image upload is completed.");
		} catch (final AmazonServiceException ex) {
			logger.info("Image upload is failed.");
			logger.error("Error= {} while uploading Image.", ex.getMessage());
			throw new AmazonServiceException("Exception Occured while uploading image to AWS S3");
		}
	}

	public void deleteImageFromS3Bucket(String imageUrl) {
		logger.info("Image deletion is in progress.");
		try {
			String imageName = imageUrl.substring(imageUrl.lastIndexOf('/') + 1);
			amazonS3.deleteObject(
					new DeleteObjectRequest(PerfectCherryUtility.getS3ImageBucketFolder() + "/", imageName));
		} catch (final AmazonServiceException ex) {
			logger.info("Failed to delete Image.");
			logger.error("Error= {} while deleting file.", ex.getMessage());
			throw new AmazonServiceException("Exception Occured while deleting image from AWS S3");
		}
	}
}
