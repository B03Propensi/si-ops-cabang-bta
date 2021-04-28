package bta.cabang.operasional.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.
                    authorizeRequests()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/static/**").permitAll()
                    .antMatchers("/cuti/add").hasAnyAuthority("Koordinator Bidang Studi", "Staf Cabang", "Pengajar")
                    .antMatchers("/cuti/delete").hasAnyAuthority("Koordinator Bidang Studi", "Staf Cabang", "Pengajar")
                    .antMatchers("/jadwal/tambah").hasAnyAuthority("Admin")
                    .antMatchers("/jadwal/ubah").hasAnyAuthority("Admin")
                    .antMatchers("/jadwal/hapus").hasAnyAuthority("Admin")
                    .antMatchers("/cabang/add").hasAnyAuthority("Admin")
                    .antMatchers("/cabang/update").hasAnyAuthority("Admin")
                    .antMatchers("/cabang/delete").hasAnyAuthority("Admin")
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/login").defaultSuccessUrl("/", true).permitAll()
                    .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login").permitAll()
                    .and()
                    .cors()
                    .and()
                    .csrf()
                    .disable();
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .passwordEncoder(encoder())
                .withUser("Test").password(encoder().encode("123456"))
                .roles("Admin");
    }

//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Autowired
//    public void configureAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
//    }
}