package org.camunda.training.rest.accountservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;


// for non Spring Boot extend AbstractSecurityWebApplicationInitializer - super(SpringSecurityConfig.class)
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    //extends WebSecurityConfigurerAdapter
/*    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("demo")
                .username("demo")
                .password(passwordEncoder().encode("demo"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }*/

    @Autowired
    private RestAuthEntryPoint restAuthEntryPoint;
    private AutheticationSuccessHandler successHandler = new AutheticationSuccessHandler();
    private AuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("demo").password(passwordEncoder().encode("demo"))
                .authorities("USER").and()
                .withUser("admin").password(passwordEncoder().encode("admin"))
                .authorities("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling()
                .authenticationEntryPoint(restAuthEntryPoint).and()
                .authorizeRequests()
                .antMatchers("/service/**").authenticated()
                .antMatchers("/admin").hasRole("ADMIN").and()
//                .antMatchers(HttpMethod.GET, "/services/**").hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.POST, "/services/**").hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/services/**").hasAnyRole("USER", "ADMIN").and()
//                .and().cors()
//                .and().requestCache(new NullRequestCache())
                .httpBasic().and()
                .formLogin()
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()
                .logout();

        //*http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);*//*
    }
}