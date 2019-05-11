package services.controlers;


import data.access.entities.SmokeEntity;
import data.access.mapper.SmokeMapper;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;


@RequestMapping({"/smoke","/"})
@RestController
public class SmokeController extends  RestControllerBase  {

    public static final Logger logger = LoggerFactory.getLogger(SmokeController.class);

    @Autowired
    SmokeMapper smokeMapper;

    @GetMapping("/date")
    public Date getDate(){
        return  new Date();
    }

    @GetMapping
    public SmokeEntity get(){
        logger.info("Smoke");
        var entity=  smokeMapper.smoke();
        logger.info(entity.toString());
        return  entity;
    }

    @PostMapping
    public SmokeEntity post( Date date){
        logger.info("Smoke");
        var entity=  smokeMapper.smoke();
        entity.setMessage(entity.getMessage().concat(LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toString()));
        logger.info(entity.toString());
        return  entity;
    }
}
