package com.shared.info.mapper;

import com.shared.info.pojo.ClientEntitlement;
import com.shared.info.pojo.EntityEntitlements;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ClientEntityMapper {

    @Mapping(target = "entityId", source = "id")
    EntityEntitlements clientEntitlementToEntityEntitlement(ClientEntitlement clientEntitlement);
}
