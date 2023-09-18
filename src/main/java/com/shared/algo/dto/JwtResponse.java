package com.shared.algo.dto;

import lombok.Builder;

@Builder
public record JwtResponse(String jwtToken) {
}
