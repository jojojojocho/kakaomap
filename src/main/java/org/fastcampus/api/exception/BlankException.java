package org.fastcampus.api.exception;

/**
 * input data가 공백일 경우 필요한 Custom Exception
 *
 * @since 2023-08-22
 * @author byungsang jo
 */
public class BlankException extends Exception {
    public BlankException(String message) {
        super(message);
    }
}
