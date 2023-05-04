package com.example.demo.dto;

public class SuccessResponseDto {

	private String message;
	private String messageKey;
	private Object data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public SuccessResponseDto(String message, String messageKey, Object data) {
		super();
		this.message = message;
		this.messageKey = messageKey;
		this.data = data;
	}

	public SuccessResponseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

}
