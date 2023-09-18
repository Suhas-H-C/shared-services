package com.shared.info.service.impl;

import com.shared.info.dto.ClientDetails;
import com.shared.info.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public final class UserService implements UserDetailsService {

    private final ClientService clientService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //Logic to get the user form the Database
        return new User("suhas", "$2a$10$wdrTYak5lHz9uf9UrrY3WO7KXf9b.NUThyY1Bv3aoawyj7fb59VXm", new ArrayList<>());
    }

    public String getSigmaCode(String referenceNumber) {
        ClientDetails clientDetails = clientService.getClientDetails(referenceNumber);
        List<Map.Entry<String, ClientDetails>> entries = processCombination(clientDetails);
        if (entries.isEmpty()) {
            return "INVALID";
        }
        return entries.get(0).getKey();
    }

    private List<Map.Entry<String, ClientDetails>> processCombination(ClientDetails clientDetails) {
        BiPredicate<String, String> bi = (segment, subSegment) ->
                segment.equalsIgnoreCase(clientDetails.segment()) && subSegment.equalsIgnoreCase(clientDetails.subSegment());

        return dataForProcessing().entrySet().stream()
                .filter(entries -> bi.test(entries.getValue().segment(), entries.getValue().subSegment())).collect(Collectors.toList());
    }

    private Map<String, ClientDetails> dataForProcessing() {
        return Map.of("AWT", new ClientDetails("A1", "A2"),
                "WWT", new ClientDetails("W1", "W2"),
                "RWT", new ClientDetails("R1", "R2"));
    }
}
