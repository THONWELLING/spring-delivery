package com.thonwelling.fooddelivery.exceptionHandler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ApiError {
    private LocalDateTime dateTime;
    private String message;
}
