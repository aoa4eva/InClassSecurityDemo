package me.afua.securitydemo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class AppConfig extends WebSecurityConfigurerAdapter {

//    @Bean
//    PasswordEncoder passwordEncoder()
//    {
//        return new BCryptPasswordEncoder();
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/css/**","/img/**","/js/**").permitAll()
                .antMatchers("/admin").hasAuthority("ADMIN")
                .antMatchers("/user").hasAuthority("RANDOMUSER")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        auth.inMemoryAuthentication().withUser("adminuser").password(pwEncoder.encode("password")).authorities("ADMIN")
                .and()
                .withUser("ordinaryuser").password(pwEncoder.encode("ordinarypassword")).authorities("RANDOMUSER")
                .and()
                .passwordEncoder(pwEncoder);
    }
}
