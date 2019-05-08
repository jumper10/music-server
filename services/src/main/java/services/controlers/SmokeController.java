package services.controlers;


import data.access.mapper.SmokeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/smoke")
@RestController
public class SmokeController extends RestControllerBase {
    @Autowired
    SmokeMapper smokeMapper;

    @GetMapping
    public String Smoke(){
        return  smokeMapper.smoke();
    }
}
