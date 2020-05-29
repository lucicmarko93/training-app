package com.training.config;

import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import lombok.Getter;

@Singleton
@Startup
@Getter
public class AppPropertiesLoader {
	
	@Inject 
	@ConfigProperty(name = "black.list.ids")
	private List<String> ids;
	
	@Inject 
	@ConfigProperty(name = "adult.age")
	private int adultAge;
}
