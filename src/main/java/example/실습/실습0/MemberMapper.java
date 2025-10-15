package example.실습.실습0;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MemberMapper {

    // 회원 등록
    @Insert("insert into members( name , phone , age ) values ( #{name} , #{phone} , #{age})")
    public boolean addMember( MemberDto memberDto );

    // 회원 삭제
    @Delete("delete from members where mno = #{mno}")
    public boolean deleteMember( int mno );

    // 회원 조회
    @Select("select * from members")
    public List<MemberDto> memberList();

}
