package com.shared.algo.enums;

public enum FileConstants {

	
	SHEET_NAME("sheet1");
	
	
	String value;

	private FileConstants(String value) {
		this.value = value;
	}
	public String getValue() {
		return value;
	}
}
