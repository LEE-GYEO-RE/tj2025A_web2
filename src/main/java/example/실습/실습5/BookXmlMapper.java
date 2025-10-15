package example.실습.실습5;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookXmlMapper {

    // books 테이블에 가격 필드 추가
    void alter1();

    // books 테이블에 title 책 필드 수정
    Boolean alter2(BookDto dto);

    // 평균 재고보다 많은 재고 가진 도서 조회
    List<BookDto> select1(BookDto dto);

    // 가장 많이 대출한 도서 조회
    RentalsDto select2();

} // inter e
