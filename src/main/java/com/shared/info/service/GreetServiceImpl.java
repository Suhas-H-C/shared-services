package com.shared.info.service;

import com.shared.info.port.GreetPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GreetServiceImpl implements GreetService{

    private final GreetPort greetPort;

    @Override
    public String greet(String name) {
        return greetPort.greet(name);
    }
}
