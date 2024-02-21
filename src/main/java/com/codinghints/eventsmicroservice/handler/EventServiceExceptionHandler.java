package com.codinghints.eventsmicroservice.handler;

import com.codinghints.eventsmicroservice.dto.APIResponse;
import com.codinghints.eventsmicroservice.dto.ErrorDto;
import com.codinghints.eventsmicroservice.exception.EventServiceBusinessException;
import com.codinghints.eventsmicroservice.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class EventServiceExceptionHandler {
    private final String STATUS = "FAILED";
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse<?> handleMethodArgumentException(MethodArgumentNotValidException exception){
        APIResponse<?> response = new APIResponse<>();
        response.setStatus(STATUS);
        List<ErrorDto> errors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(error->{
            errors.add(new ErrorDto(error.getField(), error.getDefaultMessage()));
        });
        response.setErrors(errors);
        return response;
    }
    @ExceptionHandler(EventServiceBusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse<?> handleEventServiceException(EventServiceBusinessException exception){
        APIResponse<?> serviceResponse = new APIResponse<>();
        serviceResponse.setStatus(STATUS);
        serviceResponse.setErrors(Collections.singletonList(new ErrorDto("", exception.getMessage())));
        return serviceResponse;
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public APIResponse<?> handleResourceNotFoundException(NoSuchElementException exception){
        APIResponse<?> response = new APIResponse<>();
        response.setStatus(STATUS);
        response.setErrors(Collections.singletonList(new ErrorDto("", exception.getMessage())));
        return response;
    }

    @ExceptionHandler(GlobalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public APIResponse<?> handleGlobalException(GlobalException exception){

        return APIResponse.builder()
                .status(STATUS)
                .errors(List.of(new ErrorDto("", exception.getMessage())))
                .build();
    }
}
