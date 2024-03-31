package com.system.backend.Config;


import com.system.backend.Service.CustomUserDetailsService;
import com.system.backend.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private LogoutHandler logoutHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().authorizeRequests()
                .antMatchers("/api/v1/login").permitAll()
                .antMatchers("/api/v1/register").permitAll()
                .antMatchers("/api/v1/user/admin/addadmin").permitAll()
                .antMatchers("/api/v1/user/products/getAll_desc").permitAll()
                .antMatchers("/api/v1/user/products/getAll_asc").permitAll()
                .antMatchers("/api/v1/user/products/getSearch_desc").permitAll()
                .antMatchers("/api/v1/user/products/getSearch_asc").permitAll()
                .antMatchers("/api/v1/user/products/one/{product_id}").permitAll()

                .antMatchers("/api/v1/user/posts/getALL_desc").permitAll()
                .antMatchers("/api/v1/user/posts/getALL_asc").permitAll()
                .antMatchers("/api/v1/user/posts/one/{post_id}").permitAll()
                .antMatchers("/api/v1/user/posts/getSearch_asc").permitAll()
                .antMatchers("/api/v1/user/posts/getSearch_desc").permitAll()

                .antMatchers("/api/v1/user/pets/getAll_desc").permitAll()
                .antMatchers("/api/v1/user/pets/getAll_asc").permitAll()
                .antMatchers("/api/v1/user/pets/type_desc/{type}").permitAll()
                .antMatchers("/api/v1/user/pets/type_asc/{type}").permitAll()
                .antMatchers("/api/v1/user/pets/getSearch").permitAll()
                .antMatchers("/api/v1/user/pets/one/{pet_id}").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutUrl("/api/v1/logout")
                .addLogoutHandler(logoutHandler)
                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                .and()
                .exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
        ;
    }
}
