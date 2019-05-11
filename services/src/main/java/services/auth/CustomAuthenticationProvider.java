package services.auth;

import data.access.models.User;
import data.access.repositories.UserManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserManagerRepository userManagerRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName=authentication.getName();
        String password = authentication.getCredentials().toString();
        try {
            User user = userManagerRepository.findByUserName(userName);
            if(user == null){
                throw  new CustomAuthenticationException("用户不存在！");
            }
            if(!user.getPassword().equals(password)){
                throw  new CustomAuthenticationException("密码错误！");
            }
            UserDetails userDetails = org.springframework.security.core.userdetails.User
                    .withUsername(user.getUserName())
                    .password("{noop}".concat(user.getPassword()))
                    .roles(user.getRoles().toArray(new String[(int) user.getRoles().stream().count()]))
                    .build();


             return  new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());

        }catch (Exception ex){
            ex.printStackTrace();
            throw new CustomAuthenticationException(ex.getMessage());
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
