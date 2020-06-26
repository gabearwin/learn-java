package xyz.gabear.learn.ssm.exception;

public class AuthenticateException extends RuntimeException {
    public AuthenticateException() {
        super();
    }

    public AuthenticateException(String message) {
        super(message);
    }

    public AuthenticateException(Throwable cause) {
        super(cause);
    }

    public AuthenticateException(String message, Throwable cause) {
        super(message, cause);
    }
}