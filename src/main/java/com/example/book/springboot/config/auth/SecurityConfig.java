package com.example.book.springboot.config.auth;

import com.example.book.springboot.domain.user.Role;
import com.example.book.springboot.config.auth.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // 스프링 시큐리티 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable().headers().frameOptions().disable()
                .and()
                .authorizeRequests() // URL별 권한 관리 설정
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**") // 권한 관리 대상 지정, URL, HTTP 메소드별로 관리가 가능
                .permitAll() // permitAll : 위 지정된 URL들은 전체 열람 허용
                .antMatchers("/api/v1/**")
                .hasRole(Role.USER.name()) // 위 지정된 URL은 USER 권한을 가진 사람만 허용
                .anyRequest().authenticated() // 나머지 URL들은 인증된(로그인한) 사용자만 허용
                .and()
                .logout()
                .logoutSuccessUrl("/") // 로그아웃 성공시 해당 경로로 이동
                .and()
                .oauth2Login() 
                .userInfoEndpoint() // OAUTH2 로그인 성공 후 사용자 정보를 가져올 때의 설정을 담당
                .userService(customOAuth2UserService); // 로그인 성공시 후속 조치 진행할 구현체를 등록, 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능을 명시할 수 있다.
    }
}
