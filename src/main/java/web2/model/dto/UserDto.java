package web2.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class UserDto {

    private int uno;        // 회원번호 (PK)
    private  String uid;    // 아이디
    private  String upwd;   // 비밀번호
    private  String uname;  // 닉네임 (이름)
    private  String uphone; // 연락처
    private  String urole;  // 권한 (기본 USER)
    private  String udate;  // 가입일

} // claas e
