package com.example.datatocloud.exception;

import com.example.datatocloud.dto.ErrorDTO;
import com.example.datatocloud.dto.ServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ApplicationGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ServiceResponse<?> methodArgumentNotValidException(MethodArgumentNotValidException exception){
        ServiceResponse<?> serviceResponse = new ServiceResponse<>();
        List<ErrorDTO> errorDTOList=new ArrayList<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error->errorDTOList.add(new ErrorDTO((error.getDefaultMessage()))));
        serviceResponse.setStatus(HttpStatus.BAD_REQUEST);
        serviceResponse.setError(errorDTOList);
        return serviceResponse;
    }

    @ExceptionHandler(CustomerServiceException.class)
    public ServiceResponse<?> handleCustomerServiceException(CustomerServiceException exception){
        ServiceResponse<?> serviceResponse = new ServiceResponse<>();
        List<ErrorDTO> errorDTOList=new ArrayList<>();
        errorDTOList.add(new ErrorDTO(exception.getMessage()));
        serviceResponse.setStatus(HttpStatus.BAD_REQUEST);
        serviceResponse.setError(errorDTOList);
        return serviceResponse;
    }

}
