package services.controlers;

import library.CustomException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
public class AdviceController {

   @ExceptionHandler(BindException.class)
   public CustomException bindExceptionHandler(BindException bindException){
       List<ObjectError> errors= bindException.getAllErrors();
       ObjectError firstError =errors.get(0);
       return  new CustomException(firstError.getObjectName().concat(firstError.getCode()).
               concat(firstError.getDefaultMessage()));
   }

    @ExceptionHandler(CustomException.class)
    public CustomException customExceptionHandler(CustomException customException){
        return  customException;
    }
}
