package com.learning.datawarehouse.exceptionHandler;

import com.learning.datawarehouse.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CommonExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public final ResponseEntity<ErrorInfo> handleProductNotFoundException(ProductNotFoundException ex, HttpServletResponse resp) {
        ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InventoryNotFoundException.class)
    public final ResponseEntity<ErrorInfo> handleInventoryNotFoundException(InventoryNotFoundException ex, HttpServletResponse resp) {
        ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FileIncorrectFormatException.class)
    public final ResponseEntity<ErrorInfo> handleIncorrectFormatException(FileIncorrectFormatException ex, HttpServletResponse resp) {
        ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileException.class)
    public final ResponseEntity<ErrorInfo> handleFileException(FileException ex, HttpServletResponse resp) {
        ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(OutOfStockException.class)
    public final ResponseEntity<ErrorInfo> handleOutOfStockException(OutOfStockException ex, HttpServletResponse resp) {
        ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IntegrityViolationException.class)
    public final ResponseEntity<ErrorInfo> handleIntegrityViolationException(OutOfStockException ex, HttpServletResponse resp) {
        ErrorInfo errorInfo = new ErrorInfo(ex.getMessage(), ex.getErrorCode());
        return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
