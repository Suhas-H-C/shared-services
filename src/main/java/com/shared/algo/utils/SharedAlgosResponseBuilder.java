package com.shared.algo.utils;

import java.time.LocalDateTime;
import java.util.Collection;

import static java.util.Collections.*;

import java.util.Objects;
import java.util.UUID;

public class SharedAlgosResponseBuilder {

    public static GenericResponse<?> wrapWithGenericResponse(Object data) {
        GenericResponse<?> response = new GenericResponse<>();
        response.setMetaData(metaData(data));
        response.setErrors(errors(data));
        response.setData(Objects.nonNull(data) ? singleton(data) : emptyList());

        return response;
    }

    public static GenericResponse<?> wrapWithErrorResponse(Object data) {
        GenericResponse<?> response = new GenericResponse<>();
        response.setMetaData(metaData(null));
        response.setErrors(errors(null));
        response.setData(singletonList(data.toString()));

        return response;
    }

    private static MetaData metaData(Object data) {
        MetaData metaData = new MetaData();

        if (data instanceof Collection<?>) {
            if (Objects.nonNull(data) && !((Collection<?>) data).isEmpty()) {
                metaData.setTime(LocalDateTime.now());
                metaData.setSuccess(true);
                metaData.setRepsonseId(UUID.randomUUID().toString());
            } else {
                metaData.setTime(LocalDateTime.now());
                metaData.setSuccess(false);
                metaData.setRepsonseId(UUID.randomUUID().toString());
            }

        } else {
            if (Objects.nonNull(data)) {
                metaData.setTime(LocalDateTime.now());
                metaData.setSuccess(true);
                metaData.setRepsonseId(UUID.randomUUID().toString());
            } else {
                metaData.setTime(LocalDateTime.now());
                metaData.setSuccess(false);
                metaData.setRepsonseId(UUID.randomUUID().toString());
            }
        }


        return metaData;
    }

    private static Errors errors(Object data) {
        Errors errors = new Errors();

        if (data instanceof Collection<?>) {
            if (Objects.nonNull(data) && !((Collection<?>) data).isEmpty()) {
                errors.setSuccess(true);
            } else {
                errors.setSuccess(false);
            }
        } else {
            if (Objects.nonNull(data)) {
                errors.setSuccess(true);
            } else {
                errors.setSuccess(false);
            }
        }
        return errors;
    }
}
