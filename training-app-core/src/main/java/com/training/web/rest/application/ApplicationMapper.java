package com.training.web.rest.application;

import org.mapstruct.Mapper;

import com.training.infrastructure.database.application.Application;
import com.training.infrastructure.thirdparty.data.ApplicationReq;
import com.training.web.rest.application.data.ApplicationDto;

@Mapper(componentModel = "cdi")
public interface ApplicationMapper {

	Application map(ApplicationDto applicationDto);
	
	ApplicationReq map(Application application);
	
}
