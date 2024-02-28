package my.test.storage.config;

import my.test.storage.service.StorageUserService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new StorageUserService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/styles/**");
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                //.logout(Customizer.withDefaults())
                .formLogin(formLogin -> formLogin
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginPage("/"))
                //        .loginProcessingUrl("/auth"))
                //        .successForwardUrl("/")
                //        .failureForwardUrl("/"))
                .logout(logout -> logout
                        .deleteCookies("remove")
                        .invalidateHttpSession(false)
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/"))
                .authorizeHttpRequests(managerRegistry -> managerRegistry
                        .requestMatchers("/admin").hasAuthority("ADMIN")
                        .requestMatchers("/users/**").hasAnyAuthority("ADMIN")

                        .requestMatchers("/files").authenticated()
                        .requestMatchers("/files/get/*").authenticated()
                        .requestMatchers("/files/add").hasAuthority("MANAGER")
                        .requestMatchers("/files/delete/*").hasAuthority("MANAGER")

                        .requestMatchers("/manager").hasAuthority("MANAGER")
                        .requestMatchers("/").permitAll()
              //          .requestMatchers("/auth").permitAll()
                        .anyRequest().authenticated())
                    //    .requestMatchers("/auth").permitAll())

           .httpBasic(Customizer.withDefaults());

        return http.build();
    }



}
