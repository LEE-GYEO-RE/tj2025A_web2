package example.day13;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface XmlMapper {

    // 1. 등록
    // MyBatis 에서 SQL 매핑하는 방법
    // 방법1 : 추상메소드 위에 @INSERT("SQL") 작성 , 간단한 sql crud 권장
    // 방법2 : 추상메소드를 매핑하는 XML 파일에서 SQL 작성 , 복잡한 sql 권장

    // [ 생성된 PK값 반환하는방법 ]
    // @Insert("insert into student(name , kor , math) values(#{name} , #{kor} , #{math};)")
    // @Options(useGeneratedKeys = true , keyProperty = "sno" ) // 생성된 PK값 sno 필드에 반환
    // 위에 어노테이션으로 하거나 xml에서 마크업에 속성 추가해서 반환하거나
    int save( StudentDto dto );

    // 2. 전체조회
    List<StudentDto> print();

    // 3. 개별 학생 조회
    StudentDto find(int sno );

    // 4. 개별 학생 삭제
    int delete( int sno );

    // 5. 개별 학생 수정
    int update( StudentDto studentDto );


} // inter e
