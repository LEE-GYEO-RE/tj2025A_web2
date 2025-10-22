package web2.service;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // =================== 유저 로그인 토큰 ===================/

    // 비밀키 지정
    private String secretUser = "123456789123456789123456789123456789";
    private final Key secretUserKey = Keys.hmacShaKeyFor(secretUser.getBytes(StandardCharsets.UTF_8));


    // 각 토큰 관련 함수들

    // 토큰 생성 : 회원로그인 정보 전용 (아이디 , 권한)
    public String loginToken( String uid , String urole ){
        String token = Jwts.builder()
                .claim("uid" , uid ) // uid 라는 key의 로그인 성공한 회원아이디 저장
                .claim( "urole" , urole) // urole 이라는 key의 로그인 성공한 회원 권한명 저장
                .setIssuedAt(new Date()) // 발급 시간
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60)) // 유효시간
                .signWith(secretUserKey , SignatureAlgorithm.HS256) // 서명 알고리즘 적용 : 같은 데이터인데 다르게 나옴
                .compact();
        System.out.println("token = " + token);
        return token;
    } // func e

    // 토큰 검증
    public Boolean loginTokenVerify( String token ){
        try {
            Jwts.parser()
                    .setSigningKey(secretUserKey) // 서명 검증시 필요한 비밀키 대입
                    .build()
                    .parseClaimsJws(token); // 검증할 토큰 대입 하여 검증 실패
            return true; // 예외 발생하지 않으면 검증 성공

        }catch (JwtException e ){
            return false; // 예외 발생하면 검증 실패 : 유효기간이 지난 토큰이거나 존재하지 않는 토큰
        }
    } // func e

    // 토큰 값 추출 claims
    public Claims loginTokenClaim( String token ){
            return Jwts.parser()
                    .setSigningKey(secretUserKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody(); // 검증에 성공한 토큰의 클레임 반환
    } // func e

    // 클레임들의 특정 값 추출 uid ,urole
    public String getUid( String token ){
        return loginTokenClaim(token).get("uid", String.class);
    } // func e

    public String getUrole( String token){
        return loginTokenClaim(token).get("urole" , String.class);
    } // func e


    // =================== 테스트 ===================/

    // JWT : JSON(자바스크립트 객체) WEB Token(증표)
    // 웹에서 자바스크립트 기반(클라이언트)의 특정한 데이터를 대신하는 증표
    // 특정한 데이터를 직접적으로 보여주지 않고 증표를 대신 보여주는 구조
    // 웹에서 데이터 숨기기 (보안)

    private String secret = "123456789123456789123456789123456789";
    private final Key secretKey = Keys.hmacShaKeyFor( secret.getBytes() );


    // 토큰 생성
    public String createToken( String data ){
        // 토큰 생성 : 라이브러리 설치 후
        // .builder() ; 빌더패턴(생성자 대신 함수형 객체 생성) 이용한 토큰 생성
        String token = Jwts.builder()
                .claim( "key" , data ) // .claim( key , value ) : 토큰에 넣을 데이터 대입
                .setIssuedAt( new Date() ) // .setIssuedAt( new Date() ) : 토큰 발급날짜/시간
                .setExpiration( new Date(System.currentTimeMillis() + 1000 * 30 ) ) // .setExpiration( new Date() ) : 토큰 만료시간
                .signWith(secretKey , SignatureAlgorithm.HS256)
                .compact(); // 최종 JWT 문자열 형태로 생성
        System.out.println("token = " + token); // soutv
        return token;

    } // func e

    // 토큰 검증 : 토큰 유효 검사
    public Boolean tokenVerify( String token ){
        try {
            Jwts.parser() // .parser() : 구성 성분 분해
                    .setSigningKey( secretKey ) // 서명 검증에 필요한 비밀키 대입
                    .build() // 비밀키 확인
                    .parseClaimsJws(token); // 검증할 토큰 대입
            return true; // 예외 발생하지 않으면 토큰 검증

        }catch (Exception e ){
            return false;
        }
    } // func e

    // 토큰 payload(내용물) claim 값 추출
    public String tokenValue( String token ){
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println("claims = " + claims);
            Object value = claims.get("key");
            return String.valueOf(value);
        }catch (Exception e) {
            return null;
        }
    } // func e


} // class e
// new Date() : 자바에서 시스템(컴퓨터)의 시간을 반환하는 클래스
// 밀리초 : 1/1000초 , new Date( System.currentTimeMillis() ) : // 현재 날짜/시간을 밀리초로 반환