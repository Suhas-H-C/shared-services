package com.shared.vo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public final class TestUtils {

    private static final Logger LOG = LoggerFactory.getLogger(TestUtils.class);

    public String buildUrl(String port, String contextPath) {
        String url = "http://localhost:" + port + contextPath;
        LOG.info("URL : {}", url);
        return url;
    }

    public HttpHeaders httpHeaders(MediaType mediaType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBasicAuth("suhas", "suhas");
        httpHeaders.setContentType(mediaType);

        return httpHeaders;
    }
}
