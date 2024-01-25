package com.example.assignment3_jaskeeratsingh.Security;

import com.example.assignment3_jaskeeratsingh.Database.DatabaseAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
@Autowired
@Lazy
DatabaseAccess da;

    @Bean
    public SecurityFilterChain securityfilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
        MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);
        return http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(mvc.pattern("/secure/**")).hasAnyRole("USER","ADMIN","GUEST")
                        .requestMatchers(mvc.pattern("/secure")).hasAnyRole("USER","ADMIN","GUEST")
                        .requestMatchers(mvc.pattern("/insertWatchAdmin")).hasAnyRole("USER","ADMIN","GUEST")
                        .requestMatchers(mvc.pattern("/addWatches")).hasRole("ADMIN")
                        .requestMatchers(mvc.pattern("/")).permitAll()
                        .requestMatchers(mvc.pattern("/js/**")).permitAll()
                        .requestMatchers(mvc.pattern("/insertWatch")).permitAll()
                        .requestMatchers(mvc.pattern("/cart")).permitAll()
                        .requestMatchers(mvc.pattern("/store")).permitAll()
                        .requestMatchers(mvc.pattern("/css/**")).permitAll()
                        .requestMatchers(mvc.pattern("/images/**")).permitAll()
                        .requestMatchers(mvc.pattern("/register")).permitAll()
                        .requestMatchers(mvc.pattern("/permission-denied")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .requestMatchers(mvc.pattern("/**")).denyAll()
                )
                .csrf(csrf -> csrf.ignoringRequestMatchers(AntPathRequestMatcher.antMatcher("/h2- console/**")).disable())
                         .headers(headers -> headers.frameOptions(FrameOptionsConfig::disable))
                         .formLogin(form -> form.loginPage("/login").permitAll())
                         .exceptionHandling(exception -> exception.accessDeniedPage("/permission-denied"))
                         .logout(logout -> logout.permitAll())
                         .build();
                         }

                         @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
