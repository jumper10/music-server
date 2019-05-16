package services.controlers;

import data.access.models.LoginModel;
import data.access.models.User;
import data.access.repositories.UserManagerRepository;
import library.CustomException;
import lombok.var;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Base64;

@RestController
@RequestMapping("/users")
public class LoginController extends RestControllerBase {

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    AuthenticationManager authenticationManager;


    @PostMapping("/login")
    public UserDetails login(@Valid @RequestBody LoginModel loginModel,HttpServletResponse response) throws CustomException {

        logger.info("findByUserName: " + loginModel.getUserName());

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .withUsername(loginModel.getUserName())
                .password(loginModel.getPassword())
                .roles("")
                .build();
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

        Authentication newAuthToken = authenticationManager.authenticate(authenticationToken);

        if (newAuthToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(newAuthToken);
            String plainCredentials =String.format("%s:%s",userDetails.getUsername(),userDetails.getPassword());
            String token = new String( Base64.getEncoder().encode(plainCredentials.getBytes()));
            response.addHeader("Authorization","Basic "+token);
        }
        logger.info(getCurrentUser().toString());

        UserDetails userDetails1 = getCurrentUser();

        return  userDetails;
    }

    public User autoLogin() {
        return null;
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextHolder.clearContext();
        var authorization = SecurityContextHolder.getContext().getAuthentication();
        if (authorization != null) {
            new SecurityContextLogoutHandler().logout(request, response, authorization);
            return "logout";
        }
        return "erro";
    }

}
