package example.실습.실습5;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Mapper
public interface BookXmlMapper {

    // ==== 실습 5 ====

    // [1] 테이블 수정 (ALTER)
    void alter1();
    void alter2();

    // [2] 서브쿼리
    List<BookDto> query1(); // 평균 재고보다 많은 도서
    BookDto query2();       // 가장 많이 대출된 도서

    // ==== 실습 6 ====

    // view 생성
    void view1();
    void view2();

    // view 조회
    Map< String , Object > selectView1();
    List<BookDto> selectView2();

} // inter e
