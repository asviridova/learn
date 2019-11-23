package ru.otus.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/");
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests().antMatchers("/").permitAll()
                .and()
                .authorizeRequests().antMatchers("/edit", "/remove").hasAnyRole("ADMIN", "EDITOR")
                .and()
                .authorizeRequests().antMatchers("/add").hasAnyRole("USER", "ADMIN", "EDITOR")
                .and()
                .formLogin()
        ;
    }

    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails userAdmin = User
                .withUsername("admin")
                .password("admin1")
                .roles("ADMIN")
                .build();
        manager.createUser(userAdmin);
        UserDetails user = User
                .withUsername("user")
                .password("user1")
                .roles("USER")
                .build();
        manager.createUser(user);

        UserDetails editor = User
                .withUsername("editor")
                .password("editor1")
                .roles("EDITOR")
                .build();
        manager.createUser(editor);
        return manager;
    }


}
