package application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ErrorMessage {

    private final String message;

    public ErrorMessage(String message) {
        this.message = message;
    }

}
