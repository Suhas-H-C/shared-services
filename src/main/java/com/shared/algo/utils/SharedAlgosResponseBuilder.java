package com.shared.algo.utils;

import java.time.LocalDateTime;
import java.util.Collection;
import static java.util.Collections.*;
import java.util.Objects;
import java.util.UUID;

public class SharedAlgosResponseBuilder {
	
	public static GenericResponse<?> wrapWithGenericResponse(Collection<?> data){
		GenericResponse<?> response = new GenericResponse<>();
		response.setMetaData(metaData(data));
		response.setErrors(errors(data));
		response.setData(Objects.nonNull(data) ? data : emptyList());
		
		return response;
	}
	
	public static GenericResponse<?> wrapWithGenericResponse(Object data){
		GenericResponse<?> response = new GenericResponse<>();
		response.setMetaData(metaData(data));
		response.setErrors(errors(data));
		response.setData(Objects.nonNull(data) ? singleton(data) : emptyList());
		
		return response;
	}
	
	public static GenericResponse<?> wrapWithErrorResponse(Object data){
		GenericResponse<?> response = new GenericResponse<>();
		response.setMetaData(metaData(null));
		response.setErrors(errors(null));
		response.setData(singletonList(data.toString()));
		
		return response;
	}
	
	private static MetaData metaData(Collection<?> data) {
		MetaData metaData = new MetaData();
		if(Objects.nonNull(data) && !data.isEmpty()) {
			metaData.setTime(LocalDateTime.now());
			metaData.setSuccess(true);
			metaData.setRepsonseId(UUID.randomUUID().toString());
		}else {
			metaData.setTime(LocalDateTime.now());
			metaData.setSuccess(false);
			metaData.setRepsonseId(UUID.randomUUID().toString());
		}
		
		return metaData;
	}
	
	private static MetaData metaData(Object data) {
		MetaData metaData = new MetaData();
		if(Objects.nonNull(data)) {
			metaData.setTime(LocalDateTime.now());
			metaData.setSuccess(true);
			metaData.setRepsonseId(UUID.randomUUID().toString());
		}else {
			metaData.setTime(LocalDateTime.now());
			metaData.setSuccess(false);
			metaData.setRepsonseId(UUID.randomUUID().toString());
		}
		
		return metaData;
	}
	
	private static Errors errors(Collection<?> data) {
		Errors errors = new Errors();
		if(Objects.nonNull(data) && !data.isEmpty()) {
			errors.setSuccess(true);
		}else {
			errors.setSuccess(false);
		}
		
		return errors;
	}
	
	private static Errors errors(Object data) {
		Errors errors = new Errors();
		if(Objects.nonNull(data)) {
			errors.setSuccess(true);
		}else {
			errors.setSuccess(true);
		}
		
		return errors;
	}
}
