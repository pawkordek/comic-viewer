package pawkordek.comicviewer.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user-profile").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll();
    }
}
