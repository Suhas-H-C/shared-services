package com.shared.algo.utils;

import java.time.LocalDateTime;

public class MetaData {

	private boolean success;
	private String responseId;
	private LocalDateTime time;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getResponseId() {
		return responseId;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public MetaData(boolean success, String responseId, LocalDateTime time) {
		super();
		this.success = success;
		this.responseId = responseId;
		this.time = time;
	}

	public MetaData() {
	}

	@Override
	public String toString() {
		return "MetaData [success=" + success + ", responseId=" + responseId + ", time=" + time + "]";
	}
}
