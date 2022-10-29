package com.shared.algo.utils;

import java.util.Collection;

public class GenericResponse<T> {

	private MetaData metaData;
	private Collection<?> data;
	private Errors errors;

	public MetaData getMetaData() {
		return metaData;
	}

	public void setMetaData(MetaData metaData) {
		this.metaData = metaData;
	}

	public Collection<?> getData() {
		return data;
	}

	public void setData(Collection<?> data) {
		this.data = data;
	}

	public Errors getErrors() {
		return errors;
	}

	public void setErrors(Errors errors) {
		this.errors = errors;
	}

	public GenericResponse(MetaData metaData, Collection<?> data, Errors errors) {
		super();
		this.metaData = metaData;
		this.data = data;
		this.errors = errors;
	}

	public GenericResponse() {
	}

	@Override
	public String toString() {
		return "SharedAlgosResponseBuilder [metaData=" + metaData + ", data=" + data + ", errors=" + errors + "]";
	}
}
