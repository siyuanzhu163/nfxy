package com.nfxy.exception;

/**
 * 用户名重复
 */
public class ReduplicatedNameException extends BusinessException {

	private static final long serialVersionUID = -4208987795651091080L;

	public ReduplicatedNameException() {
		super();
	}

	public ReduplicatedNameException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReduplicatedNameException(String message) {
		super(message);
	}

	public ReduplicatedNameException(Throwable cause) {
		super(cause);
	}
	
}
