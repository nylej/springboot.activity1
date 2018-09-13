package com.springboot.config;


import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletProperties;
import org.springframework.stereotype.Component;

import com.springboot.people.PeopleResource;
import com.springboot.resource.CalculatorResource;

@Component
public class JerseyConfig extends ResourceConfig{

	public JerseyConfig() {
		register(CalculatorResource.class);
		register(PeopleResource.class);
		property(ServletProperties.FILTER_FORWARD_ON_404, true);
	}
}
