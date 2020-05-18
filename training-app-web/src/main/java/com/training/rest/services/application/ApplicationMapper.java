package com.training.rest.services.application;

import org.mapstruct.Mapper;

import com.training.infrastructure.application.Application;
import com.training.web.application.ApplicationRequest;

@Mapper
public interface ApplicationMapper {

	Application map(ApplicationRequest applicationRequest);
}
