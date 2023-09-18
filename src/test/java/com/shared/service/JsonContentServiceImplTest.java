package com.shared.service;

import com.shared.algo.dto.InternetProtocol;
import com.shared.algo.service.impl.JsonContentServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertFalse;

class JsonContentServiceImplTest {

    private final JsonContentServiceImpl service = new JsonContentServiceImpl();

    @Test
    void should_parse_json_file_and_return_the_date_when_file_name_is_passed() throws Exception {
        Collection<?> actualResponse = service.fetchJsonData(InternetProtocol.class, "InternetProtocol");
        assertFalse(actualResponse.isEmpty());
    }

}
