package services.config;

import data.access.models.User;
import data.access.repositories.UserManagerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import services.controlers.SmokeController;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig  extends WebSecurityConfigurerAdapter {
    static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Autowired
    UserManagerRepository userManagerRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().
                and()
                .authorizeRequests()
                .antMatchers("/api/smoke")
                .permitAll()
                .antMatchers("/api/swagger-ui.html")
                .permitAll()
                .antMatchers("/api/**").authenticated()
               ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                try {
                    User user = userManagerRepository.findByUserName(s);
                    if(user != null){
                        return org.springframework.security.core.userdetails.User
                                .withUsername(user.getUserName())
                                .password("{noop}".concat(user.getPassword()))
                                .roles(user.getRoles().toArray(new String[(int)user.getRoles().stream().count()]))
                                .build();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
return  null;
            }
        });
    }
}
