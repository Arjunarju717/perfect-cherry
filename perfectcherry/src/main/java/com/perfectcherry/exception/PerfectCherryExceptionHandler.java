package com.perfectcherry.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.perfectcherry.dto.ResponseDTO;

@ControllerAdvice
public class PerfectCherryExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(NoActiveUserFoundException.class)
	public final ResponseEntity<ResponseDTO> noActiveUserFoundException(NoActiveUserFoundException ex) {
		return new ResponseEntity<>(new ResponseDTO(ex.getMessage()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public final ResponseEntity<ResponseDTO> maxUploadSizeExceededException(MaxUploadSizeExceededException ex) {
		return new ResponseEntity<>(new ResponseDTO(ex.toString()), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ResponseDTO> exception(Exception ex) {
		return new ResponseEntity<>(new ResponseDTO(ex.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
