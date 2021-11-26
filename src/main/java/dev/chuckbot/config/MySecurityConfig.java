package dev.chuckbot.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

//object will describe the configuration for the security of your project
@EnableWebSecurity
class MySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //block specifies that access on each request has to be authenticated:
        http.authorizeRequests()

                .mvcMatchers("/", "/alljokes", "/api/jokes/random")
                .hasRole("USER")
                .mvcMatchers("/", "/alljokes","/addNewJokeForm", "/addNewJoke", "/delete", "/api/jokes/random")
                .hasRole("ADMIN")

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

    //Set own password (example: "password") and associate it with an id (example: "user")
    @Override
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
                  }
}