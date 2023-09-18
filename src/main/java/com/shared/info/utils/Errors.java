package com.shared.info.utils;

import lombok.Builder;

@Builder
public record Errors(boolean isSuccess) {
}
