package web2.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import web2.service.JwtService;
import web2.service.UserService;

import java.io.IOException;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler implements AuthenticationSuccessHandler {

    // [1] AuthenticationSuccessHandler 구현체 만들기
    // [2] onAuthenticationSuccess 메소드 구현

    private final JwtService jwtService;
    private final UserService userService;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 타사 로그인 성공 이후 로직 커스텀 (실패는 없음 : 타사에서 알아서 함)

        // [3-1] 로그인 성공한 회원의 타사가 발급한 토큰 확인 Oauth2authenticationToken , 타사의 회사명
        System.out.println(authentication);
        OAuth2AuthenticationToken authToken = (OAuth2AuthenticationToken) authentication;
        System.out.println("authToken = " + authToken);

        // [3-2] 로그인 성공한 회원의 동의항목(정보) , Oauth2User
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal(); // Principal() 주체
        System.out.println("oauth2User = " + oauth2User);

        // [4] google / kakao 식별 : 회사별로 동의항목이 다름
        String provider = authToken.getAuthorizedClientRegistrationId(); // 등록된 공급자 ID : google , kakao
        System.out.println("provider = " + provider);

        String uid = null; String name = null; // 동의항목 정보
        if(provider.equals("google")){                    // 제공자 구글이면
            uid = oauth2User.getAttribute("email"); // 동의항목 정보에서 아이디 빼오기
            name = oauth2User.getAttribute("name"); // 동의항목 정보에서 이름 빼오기
        } else if (provider.equals("kakao")) {            // 제공자 카카오면
            Map<String , Object> kakaoAccount = oauth2User.getAttribute("kakao_account"); // 동의항목 정보에서 닉네임 꺼오기
            Map<String , Object> profile = (Map<String, Object>) kakaoAccount.get("profile");    // 테스트 단계에서는 profile만
            uid = (String) profile.get("nickname"); // 계정 id는 동의항목에 없으므로 임의
            name = (String) profile.get("nickname");
        }

        // [7] oauth2 정보를 데이터베이스에 저장
        userService.oauth2UserSignup(uid , name);

        // [5] 자사의 로그인 방식 통합
        Cookie cookie = new Cookie("loginUser" , jwtService.loginToken( uid , "USER") );
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60);
        response.addCookie(cookie);

        // [6] 로그인 성공시 어디로 이동할지 (프론트앤드 루트)
        // response.sendRedirect("http://localhost:5173/"); 리액트 서버
        response.sendRedirect("http://localhost:5173/"); // 자바서버 메인 경로 localhost:8080





        // 1. 어느 타사의 로그인 성공인지 확인

        // 2. 로그인 성공한 동의항목(정보) 가져오기 : 개인정보

        // 3. 자사 서버와 타사 서버 통합 로그인 구현 (web2 기준 : 토큰/쿠키 발급)

        // 4. 자사 서버와 타사 서버 통합 DB( 최초 로그인이면 DB 저장 , 아니면 DB처리 없음)

    } // func e

} // class e
