package com.shared.algo.enums;

public enum ContentStatus {

    DISPOSITION("Content-Disposition", "attachment; filename=");

    String key;
    String content;

    private ContentStatus(String key, String content) {
        this.key = key;
        this.content = content;
    }

    public String getKey() {
        return key;
    }

    public String getContent() {
        return content;
    }
}
