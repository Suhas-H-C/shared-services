package com.shared.algo.enums;

public enum Messages {

	TYPE_NOT_FOUND("Class type not supported"),
	NULL_DATA("No data found"),
	INVLAID_SHEET("Maintain sheet name as 'sheet1' strictly");
	
	String message;

	private Messages(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
