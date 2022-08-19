package vn.com.cmcglobal.demoshopcart.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DemoShopCartExceptionHandlder {
    @ExceptionHandler({UserException.class})
    public ResponseEntity<Map<String, String>> handleUserException(UserException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("Error", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({CartLineException.class})
    public ResponseEntity<Map<String, String>> handleUserException(CartLineException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("Error", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BillException.class})
    public ResponseEntity<Map<String, String>> handleUserException(BillException ex) {
        Map<String, String> map = new HashMap<>();
        map.put("Error", ex.getMessage());
        return new ResponseEntity<>(map, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
