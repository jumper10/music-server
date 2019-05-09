package services.controlers;

import data.access.entities.UserEntity;
import data.access.models.User;
import data.access.repositories.UserManagerRepository;
import library.CustomException;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")

public class UserManagerController extends RestControllerBase {

    public static final Logger logger = LoggerFactory.getLogger(SmokeController.class);

    @Autowired
    UserManagerRepository userManagerRepository;

    @GetMapping("/{userName}")
    public User findByUserName(@PathVariable String userName) throws CustomException {
        logger.info("findByUserName: " + userName);
        var user = userManagerRepository.findByUserName(userName);
        logger.info(user.toString());
        return  user;
    }
}
