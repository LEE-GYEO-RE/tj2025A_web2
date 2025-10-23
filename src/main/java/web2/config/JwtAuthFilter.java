package web2.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import web2.service.JwtService;

import java.io.IOException;
import java.util.List;

@Component // 빈 등록
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    // 개발자가 만든 토큰(JwtService) 인증 방법을 (시큐리티방식) UsernamePasswordAuthenticationToken 통합

    // [1] 내가 만든 토큰 방식
    private final JwtService jwtService;

    // [2] 기존 시큐리티 방식의 필터 커스텀 , 상속받기( OncePerRequestFilter ) , 물려받기
    @Override
    protected void doFilterInternal(HttpServletRequest request , HttpServletResponse response , FilterChain filterChain) throws IOException , ServletException {


        String token = null;
        if( request.getCookies() != null ){ // 쿠키가 존재하면
            for(Cookie cookie : request.getCookies()){ // 모든 쿠키들을 반복문 돌려서
                if(cookie.getName().equals("loginUser")){ // 로그인제 쿠키가 존재하면
                    token = cookie.getValue(); // 쿠키 값(토큰) 꺼내기
                }
            }
        }

        // 2. UsernamePasswordAuthenticationToken 을 재정의하기
        if(token != null && jwtService.loginTokenVerify(token)){
            String uid = jwtService.getUid(token);
            String urole = jwtService.getUrole(token);

            // 시큐리티가 원하는 서명(UsernamePasswordAuthenticationToken) 만들어주기
                // 시큐리티 토큰 생성
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(
                            uid ,
                            null ,
                            List.of(new SimpleGrantedAuthority("ROLE_" + urole ) ) );

                // 시큐리티가 사용할 수 있게 토큰 저장 SecurityContext(시큐리티 메모리)
            SecurityContextHolder.getContext().setAuthentication(authToken);

        }
        // 다른 필터에서 해당하는 토큰필터를 호출 할 수 있도록 반환
        filterChain.doFilter(request , response);

    } // func e


} // class e
