package services.controlers;


import org.slf4j.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

 public class RestControllerBase {

     @InitBinder
     public  void initBinder(WebDataBinder binder){
         binder.registerCustomEditor(Date.class,
                 new CustomDateEditor( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss"),true));
         binder.registerCustomEditor(LocalDateTime.class,
                 new CustomDateEditor( new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss"),true));

     }

   protected UserDetails  getCurrentUser(){
    return  (UserDetails) SecurityContextHolder.getContext()
            .getAuthentication().getPrincipal();
   }

}
