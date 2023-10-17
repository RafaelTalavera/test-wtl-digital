package com.rafaeltalavera.springboot.testwtldigital;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive;
import com.rafaeltalavera.springboot.testwtldigital.auth.handles.LoginSuccessHandler;
import com.rafaeltalavera.springboot.testwtldigital.models.service.JpaUserDetailsService;



@Configuration
@EnableMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SpringSecurityConfig {
 
    @Autowired
    private LoginSuccessHandler successHandler;
    
    @Autowired
    private JpaUserDetailsService userDetailsService;
    
    @Autowired
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
        
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> {
            try {
                authz.requestMatchers("/css/**", "/js/**", "/images/**", "/adminlte/**", "/fontawesome/**", "/login/**", "/lis-customer/**").permitAll()
                .requestMatchers("/list-customer/**", "/").hasRole("USER")
                .requestMatchers("/delete/**").hasRole("ADMIN")
                .requestMatchers("/form/**").hasRole("ADMIN")
                .requestMatchers("/form-customer/**").hasRole("ADMIN")
                .requestMatchers("/show-customer/**").hasRole("ADMIN")
                .requestMatchers("/show/**").hasRole("ADMIN")
                .requestMatchers("/prueba/**").hasRole("ADMIN")
                
                .anyRequest().authenticated();
                
            } catch (Exception e) {
                System.out.println("ENTRÓ EN EXCEPTION DE FILTERCHAIN");
                e.printStackTrace();
            }
        }).formLogin(login -> login.loginPage("/login").successHandler(successHandler).permitAll())
        .logout(logout -> logout.logoutSuccessUrl("/login?logout").permitAll().addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(Directive.COOKIES)))
        )
        .exceptionHandling(((exceptionHandling) -> exceptionHandling.accessDeniedPage("/error_403")));
        
        return http.build();
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder());
    }
}
