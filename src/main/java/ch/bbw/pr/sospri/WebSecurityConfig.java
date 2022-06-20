package ch.bbw.pr.sospri;

import ch.bbw.pr.sospri.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;


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
                //Permition to all users
                .antMatchers("/css/*").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/fragments/**").permitAll()
                .antMatchers("/img/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/login.html").permitAll()
                .antMatchers("/contact.html").permitAll()
                .antMatchers("/member.html").permitAll()
                .antMatchers("/get-register").permitAll()

                //restricted access
                .antMatchers("/get-channel").hasRole("user")
                .antMatchers("/get-members").hasRole("admin")
                .antMatchers("/get-members").hasAuthority("admin")
                .anyRequest().authenticated()

                //Login and Logout
                .and().formLogin()
                .and().logout().permitAll()
                .and().exceptionHandling().accessDeniedPage("/403.html");

        http.csrf().ignoringAntMatchers("/h2-console/**").and().headers().frameOptions().sameOrigin();

    }

    /*
    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService((UserDetailsService) memberService);
        return provider;
    }

     */
}
