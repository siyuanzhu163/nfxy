package com.nfxy.exception;

/**
 * 图片规格不合法
 */
public class IllegalImgException extends BusinessException {

	private static final long serialVersionUID = 4990684767065292720L;

	public IllegalImgException() {
		super();
	}

	public IllegalImgException(String message, Throwable cause) {
		super(message, cause);
	}

	public IllegalImgException(String message) {
		super(message);
	}

	public IllegalImgException(Throwable cause) {
		super(cause);
	}
	
}
