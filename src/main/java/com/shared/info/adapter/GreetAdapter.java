package com.shared.info.adapter;

import com.shared.info.port.GreetPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.graphql.client.GraphQlClient;
import org.springframework.stereotype.Component;

@Component
public class GreetAdapter implements GreetPort {

    @Autowired
    @Qualifier("greetService")
    private GraphQlClient graphQlClient;

    @Override
    public String greet(String name) {
        return graphQlClient.documentName("greet-user")
                .variable("name",name)
                .retrieve("response")
                .toEntity(String.class).block();
    }
}
