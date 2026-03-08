package com.create.task_tracker.Handlers;

import com.create.task_tracker.Domain.DTO.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionalHandler {
   @ExceptionHandler({IllegalArgumentException.class})
   public ResponseEntity<ErrorResponse> handleException(RuntimeException ex, WebRequest request) {
        /*Return Response 400 Status Code*/
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }
}
