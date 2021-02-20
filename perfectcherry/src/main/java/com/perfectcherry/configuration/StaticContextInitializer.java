package com.perfectcherry.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.perfectcherry.configuration.properties.AWSS3ConfigProperties;
import com.perfectcherry.utility.PerfectCherryUtility;

@Component
public class StaticContextInitializer {

	@Autowired
	private AWSS3ConfigProperties awss3ConfigProperties;

	@PostConstruct
	public void init() {
		PerfectCherryUtility.setAwsS3Config(awss3ConfigProperties);
	}

}
