package blood_donor.blood_donor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice 
public class GlobalExceptionHandler {
    
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> NullValueError(NullPointerException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("This is prviding a null Value to the method or null value to the user");
    }
}
