package com.shared.info.dto;

import lombok.Builder;

@Builder
public record JwtRequest(String username, String password) {
}
