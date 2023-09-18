package com.shared.info.dto;

import lombok.Builder;

@Builder
public record ClientDetails(String segment, String subSegment) {
}
