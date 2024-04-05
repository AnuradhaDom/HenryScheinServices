/* package com.henryshain;

import com.henryshain.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.henryshain.userservice.serviceImpl.SSUserDetailsService;

    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        @Qualifier("sessionRegistry")
        private SessionRegistry sessionRegistry;

        @Bean
        public PasswordEncoder encoder() {
            return new BCryptPasswordEncoder(11);
        }

        @Override
        public UserDetailsService userDetailsServiceBean() throws Exception {
            return new SSUserDetailsService(userRepository);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            // http.authorizeRequests().antMatchers("/assets/**", "/bootstrap3/**", "/",
            // "/register/**").permitAll()
            // .anyRequest().authenticated();

            //http.headers().frameOptions().sameOrigin();

            http.sessionManagement().maximumSessions(3).sessionRegistry(sessionRegistry()).expiredUrl("/");


            http.exceptionHandling().accessDeniedPage("/latestcharts");
            http.authorizeRequests().antMatchers("/assets/**", "/assets1/**", "/bootstrap3/**", "/", "/register/**")
                    .permitAll().antMatchers("/reset-password").permitAll().antMatchers("/reset-password-change")
                    .permitAll().antMatchers("/verify-emaillink").permitAll().antMatchers("/createAdminUser").permitAll()
                    .antMatchers("/portfolio-details").permitAll()
                    .antMatchers("/privacy").permitAll().anyRequest().authenticated();

            http.formLogin().failureUrl("/login?error").defaultSuccessUrl("/homescreen").loginPage("/login").permitAll().and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/")
                    .permitAll();

            //http.sessionManagement().invalidSessionUrl("/login?logout");

        }

        @Bean
        public SessionRegistry sessionRegistry() {
            return new SessionRegistryImpl();
        }

        @Bean
        public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisher() {
            return new ServletListenerRegistrationBean<HttpSessionEventPublisher>(new HttpSessionEventPublisher());
        }

        @Autowired
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(encoder());
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }


 */






