package com.shared.algo.utils;

import java.time.LocalDateTime;

public class MetaData {

	private boolean success;
	private String repsonseId;
	private LocalDateTime time;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getRepsonseId() {
		return repsonseId;
	}

	public void setRepsonseId(String repsonseId) {
		this.repsonseId = repsonseId;
	}

	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	public MetaData(boolean success, String repsonseId, LocalDateTime time) {
		super();
		this.success = success;
		this.repsonseId = repsonseId;
		this.time = time;
	}

	public MetaData() {
	}

	@Override
	public String toString() {
		return "MetaData [success=" + success + ", repsonseId=" + repsonseId + ", time=" + time + "]";
	}
}
