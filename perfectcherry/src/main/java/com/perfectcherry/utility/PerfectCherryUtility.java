package com.perfectcherry.utility;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.perfectcherry.configuration.properties.AWSS3ConfigProperties;

public final class PerfectCherryUtility {

	private static Logger logger = LogManager.getLogger(PerfectCherryUtility.class);

	private static AWSS3ConfigProperties awss3ConfigProperties;

	public static void setAwsS3Config(AWSS3ConfigProperties awss3ConfigProperties) {
		PerfectCherryUtility.awss3ConfigProperties = awss3ConfigProperties;
	}

	private PerfectCherryUtility() {
	}

	public static final File convertMultiPartFileToFile(final MultipartFile multipartFile) {
		if (logger.isDebugEnabled()) {
			logger.debug("Converting multipart-file to file");
		}
		final File file = new File(multipartFile.getOriginalFilename());
		try (final FileOutputStream outputStream = new FileOutputStream(file)) {
			outputStream.write(multipartFile.getBytes());
		} catch (final IOException ex) {
			logger.error("Error converting the multi-part file to file= ", ex.getMessage());
		}
		return file;
	}

	public static String getS3ImageBucketFolder() {
		return awss3ConfigProperties.getBucketName() + "/" + awss3ConfigProperties.getImagesFolder();
	}

	public static String getImageURL(String uniqueImageName) {
		return awss3ConfigProperties.getEndPointUrl() + "/" + awss3ConfigProperties.getImagesFolder() + "/"
				+ uniqueImageName;
	}

	public static final String getUniqueImageName(String imageName) {
		return new Date().getTime() + "-" + imageName;
	}

}
