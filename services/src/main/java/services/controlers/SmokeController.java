package services.controlers;


import data.access.entities.SmokeEntity;
import data.access.mapper.SmokeMapper;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/smoke")
@RestController
public class SmokeController  {

    public static final Logger logger = LoggerFactory.getLogger(SmokeController.class);

    @Autowired
    SmokeMapper smokeMapper;

    @GetMapping
    public SmokeEntity Smoke(){
        logger.info("Smoke");
        var entity=  smokeMapper.smoke();
        logger.info(entity.toString());
        return  entity;
    }
}
