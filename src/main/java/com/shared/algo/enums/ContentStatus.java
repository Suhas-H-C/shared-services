package com.shared.algo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ContentStatus {

    DISPOSITION("Content-Disposition", "attachment; filename=");

    final String key;
    final String content;
}
