package com.example.demo.exception;

import java.io.IOException;
import java.net.InetAddress;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.dto.ErrorResponseDto;
import com.example.demo.entities.ErrorLoggerEntity;
import com.example.demo.entities.MethodEnum;
import com.example.demo.repository.ErrorLoggerRepository;

@ControllerAdvice
public class AllExceptionHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private ErrorLoggerRepository errorLoggerRepository;

//	@ResponseBody
//	@ExceptionHandler(HospitalNotFoundException.class)
//
//	@ResponseStatus(value = HttpStatus.NOT_FOUND)
//	void handleResourceNotFound(HospitalNotFoundException e) {
//
//		e.getMessage();
//	}

	@ExceptionHandler(DataNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	void dataResourceNotFound(DataNotFoundException f) {
		f.getMessage();
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	public @ResponseBody ErrorResponseDto handleException(final Exception exception,
			HttpServletRequest httpServletRequest) throws IOException {

		ErrorLoggerEntity errorRequest = new ErrorLoggerEntity();
		errorRequest.setBody(httpServletRequest instanceof StandardMultipartHttpServletRequest ? null
				: httpServletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator())));
		errorRequest.setHost(InetAddress.getLoopbackAddress().getHostAddress());
		errorRequest.setMessage(exception.getMessage());
		errorRequest.setMethod(Enum.valueOf(MethodEnum.class, httpServletRequest.getMethod()));
		errorRequest.setToken(httpServletRequest.getHeader(PAGE_NOT_FOUND_LOG_CATEGORY));
		errorRequest.setUrl(httpServletRequest.getRequestURI());
		errorLoggerRepository.save(errorRequest);
		ErrorResponseDto error = new ErrorResponseDto();
		error.setMessage("errrrooooooorrrrrrr");
		error.setMsgKey("ffffffooooooouuuuunnnnndddd");
		return error;

	}

}
