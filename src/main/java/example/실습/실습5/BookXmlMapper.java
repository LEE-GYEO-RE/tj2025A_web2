package example.실습.실습5;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookXmlMapper {


    // [1] 테이블 수정 (ALTER)
    void alter1();
    void alter2();

    // [2] 서브쿼리
    List<BookDto> query1(); // 평균 재고보다 많은 도서
    BookDto query2();       // 가장 많이 대출된 도서

} // inter e
