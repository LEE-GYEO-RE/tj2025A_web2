package web2.model.mapper;

import org.apache.catalina.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import web2.model.dto.UserDto;

@Mapper
public interface UserMapper {

    // 회원가입
    @Insert("insert into users(uid , upwd , uname , uphone , urole ) values ( #{uid} , #{upwd} , #{uname} , #{uphone} , #{urole} ) ")
    @Options( useGeneratedKeys = true , keyProperty = "uno") // insert 성공시 매개변수에 생성된 pk값 주입한다.
    public int signup(UserDto userDto);

    // 암호화 전 로그인
    // @Select("select * from users where uid = #{uid} and upwd = #{upwd}")
    // public UserDto login(UserDto userDto);

    // 암호화 후 로그인
    @Select("select * from users where uid = #{uid}")
    UserDto login(String uid);

    // 로그아웃

    // 내정보

    // 중복검사

    // 비밀번호 수정

    // 회원탈퇴



} // inter e
