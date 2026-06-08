package com.llanquihuetour.exception;

/**
 * Exception thrown when a RUT
 * does not meet the expected format.
 *
 * @author Sara
 * @version 1.0
 */
public class RutInvalidException extends Exception {

    /**
     * Creates a new exception.
     *
     * @param message error description
     */
    public RutInvalidException(String message) {
        super(message);
    }
}