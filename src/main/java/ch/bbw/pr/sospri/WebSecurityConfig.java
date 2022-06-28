package ch.bbw.pr.sospri;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    public void globalSecurityConfiguration(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("test").password("{noop}Asssssss0").roles("user");
        auth.inMemoryAuthentication().withUser("supervisor").password("{noop}1234").roles("user", "supervisor");
        auth.inMemoryAuthentication().withUser("admin").password("{noop}5678").roles("user", "admin");
    }

    protected void configure(HttpSecurity http) throws Exception{
        logger.info("Using default config ");

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
                .antMatchers("/get-channel").hasAnyRole("user","admin", "supervisor")
                .antMatchers("/get-members").hasRole("admin")
                .antMatchers("/edit-member").hasRole("admin")
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
