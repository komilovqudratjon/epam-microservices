package com.epam.upskill.authenticationservice.exception;

/**
 * @description: InvalidException class for invalid data.
 * @date: 12 November 2023 $
 * @time: 8:40 PM 27 $
 * @author: Qudratjon Komilov
 */
public class InvalidException extends RuntimeException {
    public InvalidException(String message) {
        super(message);
    }
}