package com.perfectcherry.service;

import com.perfectcherry.dto.MailDTO;
import com.perfectcherry.exception.PerfectCherryException;

public interface MailService {
	
	public void sendMail(MailDTO mailDTO) throws PerfectCherryException;

}
