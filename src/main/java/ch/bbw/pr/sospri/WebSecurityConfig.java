package ch.bbw.pr.sospri;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void globalSecurityConfiguration(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("{noop}1234").roles("user");
        auth.inMemoryAuthentication().withUser("admin").password("{noop}5678").roles("user", "admin");
    }

    protected void configure(HttpSecurity http) throws Exception{
        System.out.println("Using default config ");

        http.authorizeRequests()
                .antMatchers("/noSecurity").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .and().httpBasic();
    }
}
