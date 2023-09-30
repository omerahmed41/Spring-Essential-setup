package com.osuliman.spring.rest.essential.application.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
    /**
     * Configures the default security filter chain.
     *
     * Subclasses may override this method to customize the security filter chain. However,
     * subclasses should be careful not to remove any of the default security filters, as this
     * could leave the application vulnerable to attacks.
     *
     * @return the default security filter chain
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("omer")
                .password("{bcrypt}$2a$12$rAODaMRLfi8vm3wHApyYeeMF95HBA2UFbLGGgp3SzEcrHfm/BaI16")
                .roles("MANAGER", "ADMIN")
                .build();
        System.out.println("Bean userDetailsManager loaded");

        return new InMemoryUserDetailsManager(john, mary, susan);
    }
    /**
     * Configures the default security filter chain.
     *
     * Subclasses may override this method to customize the security filter chain. However,
     * subclasses should be careful not to remove any of the default security filters, as this
     * could leave the application vulnerable to attacks.
     *
     * @return the default security filter chain
     */
    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers(HttpMethod.GET,
                                        "/").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.GET,
                                        "/users").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.GET,
                                        "/swagger**").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.GET,
                                        "/api/employees/**").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST,
                                        "/api/employees").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PUT,
                                        "/api/employees").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.DELETE,
                                        "/api/employees/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

}


