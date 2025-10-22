package web2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import web2.service.JwtService;

@RestController
@RequestMapping("/api/jwt")
@RequiredArgsConstructor
public class JwtController {

    private final JwtService jwtService;

    // 토큰 생성
    @GetMapping("/create")
    public ResponseEntity<String> createToken(@RequestParam String data ){
        String result = jwtService.createToken( data );
        return ResponseEntity.ok().body(result);
    } // func e

    // 토큰 검증
    @GetMapping("/verify")
    public ResponseEntity<?> tokenVerity( @RequestParam String token){
        Boolean result = jwtService.tokenVerify(token);
        return ResponseEntity.ok().body(result);
    } // func e

    // 토큰 값 추출
    @GetMapping("/value")
    public ResponseEntity<?> tokenValue( @RequestParam String token ){
        String result = jwtService.tokenValue(token);
        return ResponseEntity.ok().body(result);
    } // func e

} // class e
