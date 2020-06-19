package com.desafio.codenation.config;


import com.desafio.codenation.services.SecurityEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

import static org.springframework.security.crypto.password.NoOpPasswordEncoder.getInstance;

@EnableWebSecurity
@EnableAuthorizationServer
@EnableResourceServer
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String[] PUBLIC_MATCHERS = {"/h2-console/**", "/oauth/token"};
    private static final String[] PUBLIC_MATCHERS_GET = {"/evento/**", "/log/**"};
    private static final String[] PUBLIC_MATCHERS_POST = {"/evento/**", "/log/**"};
    private final Environment env;
    private final UserDetailsService userDetailsService;


    @Autowired
    public SecurityConfig(SecurityEntityService securityEntityService, Environment env) {
        this.userDetailsService = securityEntityService;
        this.env = env;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html")
                .permitAll().anyRequest().authenticated();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors().and().csrf().disable();
//
//        if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
//            http.headers().frameOptions().disable();
//
//        }
//
//        http.authorizeRequests()
//                .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
//                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
//                .antMatchers(PUBLIC_MATCHERS).permitAll()
//                .anyRequest().authenticated();
//
//        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
////        http.addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService()));
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//    }


    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/")
        .antMatchers("/h2-console/**");
//                .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST)
//                .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET)
//                .antMatchers(PUBLIC_MATCHERS)
        ;

    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Override
    public void setAuthenticationConfiguration(AuthenticationConfiguration authenticationConfiguration) {
        super.setAuthenticationConfiguration(authenticationConfiguration);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return getInstance();
    }

}