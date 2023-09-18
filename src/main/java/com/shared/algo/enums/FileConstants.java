package com.shared.algo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FileConstants {
    
    SHEET_NAME("sheet1"),
    PDF_TITLE("Shared-Algos"),
    CONTENT_TYPE("ipdata");

    final String value;
}
