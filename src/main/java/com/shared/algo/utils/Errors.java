package com.shared.algo.utils;

public class Errors {

	private boolean isSuccess;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Errors(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public Errors() {
	}

	@Override
	public String toString() {
		return "Errors [isSuccess=" + isSuccess + "]";
	}
}
