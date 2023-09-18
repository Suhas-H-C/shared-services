package com.shared.algo.dto;

import lombok.Builder;

@Builder
public record ClientDetails(String segment, String subSegment) {
}
