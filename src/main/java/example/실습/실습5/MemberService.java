package example.실습.실습5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    // 등록
    public boolean addMember( MemberDto memberDto){
        boolean result = memberMapper.addMember( memberDto );
        return result;
    } // func e

    // 삭제
    public boolean deleteMember( int mno ){
        boolean result = memberMapper.deleteMember( mno );
        return result;
    } // func e

    // 조회
    public List<MemberDto> memberList(){
        List<MemberDto> result = memberMapper.memberList();
        return result;
    } // func e


} // class e
