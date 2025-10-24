package web2.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web2.model.dto.UserDto;
import web2.service.JwtService;
import web2.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    // 세션 : 서버에 저장하는 임시 저장소 이므로 서버가 종료되면 사라진다.
    // 쿠키 : 클라이언트에 저장하는 임시 저장소 이므로 서버가 종료되도 유지된다.

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Integer> signup(@RequestBody UserDto userDto){
        int result = userService.signup(userDto);
        System.out.println("userDto = " + userDto.getUid());
        return ResponseEntity.ok().body(result);
    } // func e

    // 2-1 로그인(+세션 : 자바웹서버(톰캣)의 임시 저장소 )
//    @PostMapping("/login")
//    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto , HttpSession session){
//        UserDto result = userService.login(userDto);
//        if( result != null){
//            session.setAttribute("loginUno" , result.getUid());
//        }
//        return ResponseEntity.ok(result);
//    } // func e

    // 2-2 로그인(쿠키 : 클라이언트 브라우저의 임시 저장소 , 세션 : 서버 / 쿠키 : 클라이언트 )
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserDto userDto , HttpServletResponse response){
        UserDto result = userService.login(userDto);
        // 만약 로그인 성공하면 유저의 아이디를 쿠키에 저장
        if( result != null){
            // 로그인 정보를 세션에 저장하면 서버이므로 안전하다. 쿠키(클라이언트)에 저장하면 위험하다.
            // 그래서 안전장치가 필요하다
            // Cookie cookie = new Cookie("쿠키명" , 값 );
            // response.addCookie(생성한 쿠키);
            // Cookie cookie = new Cookie("loginUser" , result.getUid());

            // 쿠키에 저장하는 회원정보르르 토큰으로 저장하는 걸로 변경
            Cookie cookie = new Cookie("loginUser" , jwtService.loginToken(result.getUid(), result.getUrole() ));
            cookie.setHttpOnly(true); // .setHttpOnly(true) : 무조건 http 에서만 사용. JS로 접근 불가능
            cookie.setSecure(false); // .setSecure(true) : http로 탈취 하더라도 암호화 , 단 https 에서만 가능
            cookie.setPath("/"); // 쿠키에 접근할 수 있는 경로
            cookie.setMaxAge(60*60); // 쿠키 유효시간 , 1시간
            response.addCookie(cookie); // 생성한 쿠키를 클라이언트에게 반환한다.
        }
        return ResponseEntity.ok(result);
    } // func e

    // 로그아웃
    @DeleteMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response){
        Cookie cookie = new Cookie("loginUser" , null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(0); // 0 이면 즉시 삭제하라는 뜻

        response.addCookie(cookie); // 동일한 쿠키명으로 null 저장하면 기존 쿠키명 사라짐

        return ResponseEntity.ok(true);

    } // func e

    // 내정보
    @GetMapping("/info")
    public ResponseEntity<UserDto> myInfo(HttpServletRequest request){ // 쿠키 활용한 로그인상태 확인
        // 클라이언트에 저장된 모든 쿠키 가져오기
        Cookie[] cookies = request.getCookies();
        // 반복문 이용한 특정 쿠키명 찾기
        if( cookies != null ){
            for( Cookie cookie : cookies ){ // 하나씩 쿠키들을 반복하여
                if( cookie.getName().equals("loginUser")){ // "loginUser" 쿠키명과 같으면
                    // String uid = cookie.getValue(); // 쿠키의 저장된 값 반환 = 지금은 uid

                    // 쿠키에 저장된 토큰 반환 하기
                    String token = cookie.getValue(); // 쿠키의 저장된 토큰 반환
                    boolean verify = jwtService.loginTokenVerify(token); // 토큰 검증
                    if(verify){
                        String uid = jwtService.getUid(token); // 토큰에 저장된 클레임(회원아이디) 추출
                        UserDto result = userService.myInfo(uid);
                        return ResponseEntity.ok().body(result);
                    }
                    return ResponseEntity.ok().body(null);
                }
            } // for e
        } // if e
        return ResponseEntity.ok().body(null); // 비로그인 상태
    } // func e


    // 권한을 반환하는 컨트롤러
    @GetMapping("/check")
    public ResponseEntity<?> checkToken(@CookieValue(value = "loginUser" , required = false) String token){

        Map<String , Object> map = new HashMap<>();

        if(token != null && jwtService.loginTokenVerify(token) ){ // 토큰 있으면서 유효하면
            String urole = jwtService.getUrole(token);
            map.put("isAuth" , true);
            map.put("urole" , urole);
            return ResponseEntity.status(200).body(map); // 유저가 로그인 했으면
        }else {
            map.put("isAuth" , false);
            return ResponseEntity.status(403).body(map); // 유저가 로그인 안했으면
        }

    } // func e


} // class e
