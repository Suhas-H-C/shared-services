package com.shared.info.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public final class SharedServicesApplicationContext implements ApplicationContextAware {

    static ApplicationContext applicationContext;
    String applicationId;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SharedServicesApplicationContext.applicationContext = applicationContext;
        applicationId = applicationContext.getId();
    }

    public String getApplicationId() {
        return applicationId;
    }
}
