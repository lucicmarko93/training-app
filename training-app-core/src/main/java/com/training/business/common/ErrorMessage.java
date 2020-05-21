package com.training.business.common;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorMessage {
	
    private int status;
    private String reason;
    private String message;
    private LocalDateTime timestamp;

}
