package com.shared.info.vo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Slf4j
public final class TestUtils {

    public String buildUrl(String port, String contextPath) {
        String url = "http://localhost:" + port + contextPath;
        log.info("URL : {}", url);
        return url;
    }

    public HttpHeaders httpHeaders(MediaType mediaType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth("suhas", "suhas");
        httpHeaders.setContentType(mediaType);

        return httpHeaders;
    }
}
