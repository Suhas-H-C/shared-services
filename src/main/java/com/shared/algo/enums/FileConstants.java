package com.shared.algo.enums;

public enum FileConstants {


    SHEET_NAME("sheet1"), PDF_TITLE("Shared-Algos"), CONTENT_TYPE("ipdata");

    String value;

    private FileConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
