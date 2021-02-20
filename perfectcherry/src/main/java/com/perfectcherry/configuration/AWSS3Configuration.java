package com.perfectcherry.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.perfectcherry.configuration.properties.AWSS3ConfigProperties;

@Configuration
public class AWSS3Configuration {

	@Bean
	public AmazonS3 getAmazonS3Cient(AWSS3ConfigProperties awss3ConfigProperties) {
		final BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(awss3ConfigProperties.getAccessKeyId(),
				awss3ConfigProperties.getSecretAccessKey());
		return AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(awss3ConfigProperties.getRegionName()))
				.withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials)).build();
	}

}
