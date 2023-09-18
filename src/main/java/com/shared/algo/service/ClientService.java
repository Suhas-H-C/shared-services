package com.shared.algo.service;

import com.shared.algo.dto.ClientDetails;

public interface ClientService {

    ClientDetails getClientDetails(String referenceNumber);
}
