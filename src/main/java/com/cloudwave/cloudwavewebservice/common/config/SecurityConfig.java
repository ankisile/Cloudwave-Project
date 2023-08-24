package com.cloudwave.cloudwavewebservice.common.config;

import com.cloudwave.cloudwavewebservice.common.jwt.JwtAccessDeniedHandler;
import com.cloudwave.cloudwavewebservice.common.jwt.JwtAuthenticationEntryPoint;
import com.cloudwave.cloudwavewebservice.common.jwt.JwtFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true) //@PreAuthorize 어노테이션을 메소드 단위로 추가하기 위해 사용
@Configuration
@EnableWebSecurity // 스프링 시큐리티가 스프링 체인 필터에 등록. 기본적인 web 보안 활성화
public class SecurityConfig {
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtFilter jwtFilter;

    // password encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return
                http
                        .cors().disable()
                        .formLogin().disable()
                        .headers().frameOptions().disable()
                        .and()
                        .csrf().disable()
                        .sessionManagement()
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                        .and()
                        .authorizeRequests() //HttpServeletRequest를 사용하는 요청들에대한 접근제한을 설정
                        .antMatchers("/api/*").permitAll()
                        .antMatchers("/api/cart/*").authenticated()
                        .and()
                        .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) //OAUTH2 사용한다면 변경 필요
                        // Exception을 핸들링할 때 직접 만든 클래스를 추가
                        .exceptionHandling()
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler)
                        .and().build();
    }
}
