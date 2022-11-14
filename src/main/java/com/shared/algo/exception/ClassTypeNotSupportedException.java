package com.shared.algo.exception;

public final class ClassTypeNotSupportedException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;

	@Override
	public String getMessage() {
		return this.message;
	}

	public ClassTypeNotSupportedException(String message) {
		super();
		this.message = message;
	}

}
