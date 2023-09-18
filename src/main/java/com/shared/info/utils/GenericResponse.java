package com.shared.info.utils;

import lombok.Builder;

import java.util.Collection;

@Builder
public record GenericResponse<T>(MetaData metaData, Collection<?> data, Errors errors) {
}
