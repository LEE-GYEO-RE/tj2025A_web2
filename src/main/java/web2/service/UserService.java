package web2.service;

import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import web2.model.dto.UserDto;
import web2.model.mapper.UserMapper;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    // 회원가입
    public int signup(UserDto userDto){
        // 비밀번호 암호화
        userDto.setUpwd(encoder.encode(userDto.getUpwd()));
        System.out.println("userDto = " + userDto.getUpwd());

        userMapper.signup(userDto);
        if(userDto.getUno() > 0 ){
            return userDto.getUno();
        } // if e
        else { return 0; }
    } // func e

    // 로그인 : 암호문을 해독하는 방식이 아닌 비교할 비밀번호를 암호문으로 변경하여 암호문 비교
    public UserDto login(UserDto userDto){

        // 현재 로그인에서 입력받은 아이디의 계정이 있는 지 확인
        UserDto result = userMapper.login(userDto.getUid());
        if (result == null ) { return null;}

        System.out.println("userDto = " + userDto.getUpwd());
        System.out.println("userDto = " + result.getUpwd());

        // 만약 입력받은 아이디의 계정이 존재하면 , 입력받은 비밀번호와 암호화된 비밀번호 비교
        Boolean result2 = encoder.matches(userDto.getUpwd(), result.getUpwd());
        if( result2 == true){
            result.setUpwd(null); // 성공시 반환되는 계정에는 비밀번호 제외
            return result;
        }else {return null;}
    } // func e

    // 2-2 회원 비밀번호 암호화
    // * 암호화 : 사람이 이해할 수 없는 데이터 변경
    // * 복호화 : 암호화된 데이터를 다시 (평문)으로 변경
    // 나만의 계산식 : 암호화 알고리즘(순서도) ,
    // 비밀번호에서 사용되는 대표적인 Bcrypt : 복호화 불가능

    // 로그아웃

    // 내정보
    public UserDto myInfo(String uid ){
        UserDto result = userMapper.myInfo(uid);
        return result;
    } // func e


    // oauth2 회원가입 구성
    public UserDto oauth2UserSignup( String uid ,String name){
        UserDto userDto = userMapper.login(uid);

        if(userDto == null){
            UserDto oauthUser = new UserDto();
            oauthUser.setUid(uid);
            oauthUser.setUpwd("null"); // 타사 패스워드라 없음
            oauthUser.setUname(name);
            oauthUser.setUrole("USER"); // 추후에 일반유저와 OAuth 유저 권한 구분 가능
            userMapper.signup(oauthUser);
            return oauthUser;
        }
        return null;
    } // func e

    // 중복검사

    // 비밀번호 수정

    // 회원탈퇴



} // class e
