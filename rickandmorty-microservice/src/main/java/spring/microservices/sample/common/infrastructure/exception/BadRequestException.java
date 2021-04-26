package spring.microservices.sample.common.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Bad Request exception.
 *
 * @author Miguel A. Vila
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public final class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = 133853955330077367L;

    public BadRequestException() {
        super();
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(String message, Exception ex) {
        super(message, ex);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

}
