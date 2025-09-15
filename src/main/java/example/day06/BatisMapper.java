package example.day06;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper // 해당 인터페이스를 스프링 컨테이너에 등록 , DAO 역할 대신 함.
public interface BatisMapper {

    // 1. 학생 등록
    // dao : insert into student( name , kor , math ) values ( ? , ? , ? )
    // mybatis : insert into student( name , kor , math ) values ( #{ 매개변수 } , #{ 매개변숫 } , #{ 매개변수 } )

    @Insert("insert into student(name,kor,math)values(#{name},#{kor},#{math})")
    // #{name} : Dto 의 name , student(name) : sql 의 name
    int save( StudentDto studentDto ); // 추상메소드
        // int : insert 된 레코드 수 반환 1 : 성공 , 0 : 실패

    // 2. 전체 학생 조회
    @Select("select * from student")
    List<StudentDto> findAll();

    // 3. 개별 학생 조회
    @Select("select * from student where sno = #{ sno }")
    Map< String , Object > find( int sno );

    // 4. 개별 학생 삭제
    @Delete("delete from student where sno = #{ sno }")
    int delete( int sno );


    // 5. 개별 학생 수정
    @Update("update student set kor =" +
            " #{ kor } , math = #{ math } where sno = #{ sno } ")
    int update( StudentDto studentDto );

} // class e
