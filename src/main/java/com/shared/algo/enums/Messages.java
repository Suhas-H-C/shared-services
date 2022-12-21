package com.shared.algo.enums;

public enum Messages {

    TYPE_NOT_FOUND("Class type not supported"), NULL_DATA("No data found"),
    INVLAID_SHEET("Maintain sheet name as 'sheet1' strictly"),
    FILE_READING_FAILED("Error encountered while reading the file"), INVALID_FILE_NAME("Please check the file name");

    String message;

    private Messages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
