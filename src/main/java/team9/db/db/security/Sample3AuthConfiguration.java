package team9.db.db.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class Sample3AuthConfiguration {

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserBuilder users = User.builder();

    UserDetails user1 = users
        .username("tohu")
        .password("$2y$10$Sp5kB/hKH/jTPtfDSxAWfeYyoNzeIvrRVsAkv4QClyb2zD8aFQBM.")
        .roles("PLAYER")
        .build();
    UserDetails admin = users
        .username("miku")
        .password("$2y$10$m616szfooSi8dZwLUkSrVerMr6e6j.SFp3jLX6lF5LvXApd9/ppCy")
        .roles("RELUR")
        .build();
    return new InMemoryUserDetailsManager(user1, admin);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin();

    http.authorizeHttpRequests()
        .mvcMatchers("/sample3/**").authenticated()
        .mvcMatchers("/sample4/**").authenticated();

    http.logout().logoutSuccessUrl("/");

    http.csrf().disable();
    http.headers().frameOptions().disable();
    return http.build();

  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
