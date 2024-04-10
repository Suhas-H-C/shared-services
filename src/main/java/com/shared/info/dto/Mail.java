package com.shared.info.dto;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
public record Mail(List<String> recipients, String message, String from,String subject, MultipartFile file) {
}
