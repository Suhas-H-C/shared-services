package com.shared.algo.dto;

import lombok.Builder;

@Builder
public record JwtRequest(String username, String password) {
}
