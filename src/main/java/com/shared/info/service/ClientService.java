package com.shared.info.service;

import com.shared.info.dto.ClientDetails;

public interface ClientService {

    ClientDetails getClientDetails(String referenceNumber);
}
