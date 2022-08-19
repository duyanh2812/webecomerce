package vn.com.cmcglobal.demoshopcart.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private HttpStatus status;
    private String message;
}
