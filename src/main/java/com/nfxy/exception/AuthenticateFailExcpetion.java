package com.nfxy.exception;

/**
 * 权限验证失败
 * @author SIYUAN
 *
 */
public class AuthenticateFailExcpetion extends BusinessException {

	private static final long serialVersionUID = -4617106176682387297L;
	
	public AuthenticateFailExcpetion() {
		
	}
	
	public AuthenticateFailExcpetion(String message) {
		super(message);
	}
	
	public AuthenticateFailExcpetion(Throwable cause) {
		super(cause);
	}
	
	public AuthenticateFailExcpetion(String message, Throwable cause) {
		super(message, cause);
	}
	
}
