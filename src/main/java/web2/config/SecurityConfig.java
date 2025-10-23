package web2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    // 시큐리티(보안) 필터(검증/확인) 체인
    // 미리 만들어진 필터들이 다수... 그런 필터들을 커스텀(수정)/제외/끄기

    private final JwtAuthFilter jwtAuthFilter;
    private final OAuth2SuccessHandler oAuth2SuccessHandler;


    // !! : HTTP 관련 필터들을 커스텀 , HTTP 요청과 응답 처리하는 웹 아키텍처
    @Bean // 빈 등록
    public SecurityFilterChain securityFilterChain (HttpSecurity security ) throws Exception{


                // [1] HTTP 요청에 따른 권한 커스텀
                // .authorizeHttpRequests( auth -> auth.requestMatchers("경로").권한);
                // .permitAll() : 모든 권한 허용
                // .hasRole("권한명") , .hasAnyRole("권한명" , "권한명");
        security
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/user/info").hasAnyRole("USER" , "ADMIN") // 2개이상 가능 , 권한명은 대문자
                        .requestMatchers("/api/admin/dashboard").hasRole("ADMIN") // admin 관련 controller는 ADMIN 권할 일때 만 가능
                        .requestMatchers("/**").permitAll());


                // [2] HTTP 요청이 csrf( 요청간의 해킹 공격 ) POST , PUT 자동 차단 커스텀
        security
                // .csrf( csrf -> csrf.ignoringRequestMatchers("csrf 제외할 경로"));
                .csrf( csrf -> csrf.disable() ); // 개발단계 권장 , csrf 사용안함.

                // [3] 시큐리티 내부에서 사용되는 (세션)토큰 , UsernamePasswordAuthenticationToken
                // web2 실습에서는 쿠키 기반의 토큰 구현중 = 시큐리티가 제공하는 세션 사용안함
                // [3-1] 시큐리티 세션 끄기
        security
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

                // [3-2] UsernamePasswordAuthenticationToken 을 개발자가 만든 토큰 대체
                // security.addFilterBefore( 내가만든 토큰객체 필터 , UsernamePasswordAuthenticationToken.class);
        security
                .addFilterBefore( jwtAuthFilter , UsernamePasswordAuthenticationFilter.class);


                // [4] OAuth2 로그인 필터 사용 설정
        security
                .oauth2Login( oauth -> oauth
                        .loginPage("/oauth2/authorization") // 현재 서버의 로그인 페이지가 아닌 타사 로그인페이지 사용
                        .successHandler(oAuth2SuccessHandler) // 타사 로그인 페이지에서 로그인 성공시 반환되는 클래스 정의
                );

        // ===================== 완료 ===================== //

        return security.build(); // 커스텀 완료된 객체 반환

    } // func e


} // class e
