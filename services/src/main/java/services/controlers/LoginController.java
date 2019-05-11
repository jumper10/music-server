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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class LoginController extends RestControllerBase {

    public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UserManagerRepository userManagerRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public User login(@Valid LoginModel loginModel) throws CustomException {

        logger.info("findByUserName: " + loginModel.getUserName());

        var user = userManagerRepository.findByUserName(loginModel.getUserName());
        if (user == null) {
            throw new CustomException("用户不存在！");
        }
        if (user.getPassword().equals(loginModel.getPassword())) {
            UserDetails userDetails = org.springframework.security.core.userdetails.User
                    .withUsername(user.getUserName())
                    .password("{noop}".concat(user.getPassword()))
                    .roles(user.getRoles().toArray(new String[(int) user.getRoles().stream().count()]))
                    .build();
            UsernamePasswordAuthenticationToken  authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
            authenticationManager.authenticate(authenticationToken);

            if(authenticationToken.isAuthenticated()){
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } else {
            throw new CustomException("密码错误！");
        }

        logger.info(getCurrentUser().toString());

        SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        return user;
    }

    public User autoLogin(){
        return  null;
    }

    @GetMapping("/logout")
    public String Logout(HttpServletRequest request, HttpServletResponse response) {
        var authorization = SecurityContextHolder.getContext().getAuthentication();
        if (authorization != null) {
            new SecurityContextLogoutHandler().logout(request, response, authorization);
            return "logout";
        }
        return "erro";
    }

}
