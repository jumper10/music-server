package services.controlers;


import data.access.models.User;
import data.access.repositories.UserManagerRepository;
import library.CustomException;
import lombok.var;
import org.springframework.security.core.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")

public class UserManagerController extends RestControllerBase {

    public static final Logger logger = LoggerFactory.getLogger(SmokeController.class);

    @Autowired
    UserManagerRepository userManagerRepository;

    @GetMapping("/logout")
    public String Logout(HttpServletRequest request, HttpServletResponse response){
        var authorization = SecurityContextHolder.getContext().getAuthentication();
        if(authorization != null){
            new SecurityContextLogoutHandler().logout(request, response,authorization);
            return "logout";
        }
        return "erro";
    }

    @GetMapping("/{userName}")
    @PreAuthorize("hasRole('R_ADMIN')")
    public User findByUserName(@PathVariable String userName) throws CustomException {
        logger.info("findByUserName: " + userName);
        var user = userManagerRepository.findByUserName(userName);
        logger.info(user.toString());
        logger.info(getCurrentUser().toString());
        return  user;
    }
}
