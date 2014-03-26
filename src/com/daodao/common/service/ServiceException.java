package com.daodao.common.service;

public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = -1035626300014469805L;
	private String messageCode;
	private String messageText;
	private String messageLevel;

	public ServiceException() {
		super();
	}

	public ServiceException(String messageCode, String messageText,
			String messageLevel) {
		super(messageText);
		this.messageCode = messageCode;
		this.messageText = messageText;
		this.messageLevel = messageLevel;
	}

	public ServiceException(String messageText) {
		super(messageText);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String messageText, Throwable cause) {
		super(messageText, cause);
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

	public String getMessageLevel() {
		return messageLevel;
	}

	public void setMessageLevel(String messageLevel) {
		this.messageLevel = messageLevel;
	}

}
