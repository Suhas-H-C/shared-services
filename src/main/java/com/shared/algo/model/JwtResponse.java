package com.shared.algo.model;

import lombok.Builder;

@Builder
public record JwtResponse(String jwtToken) {
}
