package services.controlers;

import library.CustomException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

    @ExceptionHandler(CustomException.class)
    public CustomException customExceptionHandler(CustomException customException){
        return  customException;
    }
}
