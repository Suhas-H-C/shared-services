package com.shared.algo.service;

import com.shared.algo.model.ClientDetails;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private ClientService clientService;

    @InjectMocks
    private UserService userService;

    public static Stream<Arguments> inputArgs() {
        return Stream.of(
                Arguments.of(new ClientDetails("A1", "A2"), UserService.AWT),
                Arguments.of(new ClientDetails("W1", "W2"), UserService.WWT),
                Arguments.of(new ClientDetails("R1", "R2"), UserService.RWT)
        );
    }

    @ParameterizedTest(name = "{index} - testing for {0} expecting {1}")
    @MethodSource("inputArgs")
    void getSigmaCode(ClientDetails clientDetails, String expectedResultString) {
        when(clientService.getClientDetails("123")).thenReturn(clientDetails);

        String sigmaCode = userService.getSigmaCode("123");
        assertEquals(expectedResultString, sigmaCode);
    }
}