package com.shared.algo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public final class SharedAlgoApplicationContext implements ApplicationContextAware {

	static ApplicationContext applicationContext;
	String applicationId;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SharedAlgoApplicationContext.applicationContext = applicationContext;
		applicationId = applicationContext.getId();
	}

	public String getApplicationId() {
		return applicationId;
	}
}
