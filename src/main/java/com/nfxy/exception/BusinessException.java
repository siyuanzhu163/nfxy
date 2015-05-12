package com.nfxy.exception;

/**
 * 抽象基类，所有业务异常的父类
 * @author SIYUAN
 */
public class BusinessException extends Exception {

	private static final long serialVersionUID = 9163392311699319641L;
	
	public BusinessException() {
		
	}
	
	public BusinessException(String message) {
		super(message);
	}
	
	public BusinessException(Throwable cause) {
		super(cause);
	}
	
	public BusinessException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
