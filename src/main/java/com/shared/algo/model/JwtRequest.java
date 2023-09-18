package com.shared.algo.model;

import lombok.Builder;

@Builder
public record JwtRequest(String username, String password) {
}
