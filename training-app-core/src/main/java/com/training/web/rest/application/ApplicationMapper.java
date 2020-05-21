package com.training.web.rest.application;

import org.mapstruct.Mapper;

import com.training.infrastructure.database.application.Application;
import com.training.web.rest.application.data.ApplicationRequest;

@Mapper(componentModel = "cdi")
public interface ApplicationMapper {

	Application map(ApplicationRequest applicationRequest);
}
