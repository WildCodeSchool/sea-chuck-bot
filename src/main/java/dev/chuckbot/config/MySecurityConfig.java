package dev.chuckbot.config;

import dev.chuckbot.service.AuthenticatedUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = AuthenticatedUserService.class)
class MySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //block specifies that access on each request has to be authenticated:
        http.authorizeRequests()
                .mvcMatchers("/","/alljokes","/addNewJokeForm","/addNewJoke", "/delete", "/api/jokes/random")
                    .hasRole("ADMIN")
                .mvcMatchers("/","/alljokes","/api/jokes/random")
                    .hasRole("USER")
                .anyRequest().authenticated()
                .and()
                //specify that authentication should done through a login form
                .formLogin()
                .and()
                //basic HTTP authentication
                .httpBasic()
                .and()
                //If user has no access to a specific page, he will be redirected to the accessDenied.html template
                .exceptionHandling().accessDeniedPage("/accessDenied.html");
    }

    @Autowired
    public void globalSecurityConfiguration(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }


    //Set own password (example: "password") and associate it with an id (example: "user")
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        //declaration allows users to be stored "in memory" -- i.e. without a database
        auth.inMemoryAuthentication()
                .withUser("Christiane")
                    .password(encoder.encode("user1"))
                    .roles("USER")
                    .and()
                .withUser("Marco")
                .password(encoder.encode("user2"))
                .roles("USER")
                .and()
                .withUser("Eldor")
                    .password(encoder.encode("admin"))
                    .roles("ADMIN");
    }*/
}