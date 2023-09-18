package com.shared.info.dto;

import lombok.Builder;

@Builder
public record JwtResponse(String jwtToken) {
}
