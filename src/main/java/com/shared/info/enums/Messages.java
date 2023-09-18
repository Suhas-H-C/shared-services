package com.shared.info.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Messages {

    TYPE_NOT_FOUND("Class type not supported"), NULL_DATA("No data found"),
    INVALID_SHEET("Maintain sheet name as 'sheet1' strictly"),
    FILE_READING_FAILED("Error encountered while reading the file"),
    INVALID_FILE_NAME("Please check the file name");

    final String message;
}
