package example.day13;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

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

    // ============ 동적쿼리 , 일반 SQL 코드를 프로그래밍 SQL로 변경 : <if> <forEach> 등등 ============ //

    // 6. 국어점수가 특정 점수 이상인 학생 조회
    // 방법 두가지 , 어노테이션 or XML

    // 방법1 : @어노테이션( """ <script> XML 형식의 SQL </script> """ )
    //    @Select("""
    //            <script>
    //                select * from student where 1=1
    //                <if test="kor != null">
    //                    and kor >= #{kor}
    //                </if>
    //            </script>
    //            """)
    // """ """ : java15 버전 이상부터 """ 템플릿 지원 , +연산자처럼 문자열 연결
    // where 1=1 : 무조건 true 만들기 위한 강제 true
        // where 1=1 생략시 문제 발생 가능성있음
        // 대체 : <where> 마크업
    // <if test="조건식"> 참일 경우 SQL </if>
    List<StudentDto> query1( int kor );

    // 방법2 : XML , .xml 파일에서 연동
    List<StudentDto> query2( int kor );

    // 7. 포함된 이름 또는 수학점수(이상) 로 검색
    List<StudentDto> query3( String name , int math );

    // 8. 여러명 학생 등록
    int saveAll( List<StudentDto> dtos );

} // inter e
