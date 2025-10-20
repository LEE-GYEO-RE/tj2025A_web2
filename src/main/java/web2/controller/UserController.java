package web2.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web2.model.dto.UserDto;
import web2.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<Integer> signup(@RequestBody UserDto userDto){
        int result = userService.signup(userDto);
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
            Cookie cookie = new Cookie("loginUser" , result.getUid());
            response.addCookie(cookie); // 생성한 쿠키를 클라이언트에게 반환한다.
        }
        return ResponseEntity.ok(result);
    } // func e

    // 로그아웃

    // 내정보

    // 중복검사

    // 비밀번호 수정

    // 회원탈퇴




} // class e
