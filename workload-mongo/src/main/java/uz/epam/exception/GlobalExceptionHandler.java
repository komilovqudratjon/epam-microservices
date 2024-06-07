package uz.epam.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

/**
 * @description: Global exception handler for the application.
 * @date: 12 November 2023 $
 * @time: 2:05 PM 21 $
 * @author: Qudratjon Komilov
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Handles generic Exceptions.
     *
     * @param e the caught Exception.
     * @return a ResponseEntity with error details and HTTP status code.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneralException(Exception e) {
        log.error("General Exception: ", e);
        ErrorResponse errorResponse = new ErrorResponse("Internal server error: " + e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

    /**
     * Handles exceptions related to Security.
     */
    @ExceptionHandler(SecurityException.class)
    public ResponseEntity<ErrorResponse> handleSecurityException(SecurityException e) {
        log.error("SecurityException: ", e);
        ErrorResponse errorResponse = new ErrorResponse("Security exception: " + e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

    /**
     * Handles MethodArgumentNotValidException.
     *
     * @param e the caught MethodArgumentNotValidException.
     * @return a ResponseEntity with error details and HTTP status code.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        List<ErrorsField> errors = e.getFieldErrors().stream().map(fieldError -> new ErrorsField(fieldError.getField(), fieldError.getDefaultMessage())).toList();
        ErrorResponseValid errorResponse = new ErrorResponseValid("Data not valid", System.currentTimeMillis(), errors);
        return ResponseEntity.badRequest().body(errorResponse);
    }


    public record ErrorsField(String field, String message) {
    }

    public record ErrorResponseValid(String message, long timestamp, List<ErrorsField> errorsField) {
    }

    public record ErrorResponse(String message, long timestamp) {
    }
}
