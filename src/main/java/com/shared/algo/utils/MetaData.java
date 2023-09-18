package com.shared.algo.utils;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record MetaData(boolean success, String repsonseId, LocalDateTime time) {
}
