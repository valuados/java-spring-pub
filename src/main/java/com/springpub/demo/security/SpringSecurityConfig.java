package com.springpub.demo.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.springpub.demo.security.UserRole.CLIENT;
import static com.springpub.demo.security.UserRole.MANAGER;

/**
 * @author valuados
 */
@EnableWebSecurity
@AllArgsConstructor
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final LoadUserDetailService userDetailsService;
    private final JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                //TODO check roles and uris
                .csrf().disable()
                .authorizeRequests()

                .antMatchers(HttpMethod.POST, "/menuItems").hasAnyRole(CLIENT.name())
                .antMatchers(HttpMethod.GET, "/menuItems").hasAnyRole(CLIENT.name(), MANAGER.name())
                .antMatchers(HttpMethod.PUT, "menuItems").hasAnyRole(MANAGER.name())
                .antMatchers(HttpMethod.DELETE, "menuItems").hasAnyRole(MANAGER.name())

                .antMatchers(HttpMethod.POST, "/sign-in", "/sign-up").permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .formLogin().disable();
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
