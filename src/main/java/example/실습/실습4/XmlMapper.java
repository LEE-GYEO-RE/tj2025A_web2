package example.실습.실습4;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface XmlMapper {

    // 책 등록 (생성된 도서번호 반환)
    int addBook(BookDto bookDto);

    // 대출 기록 검색 조회 (조건 없을 경우 전체 조회)
    List<RentalsDto> loanRecord(String member , int book_id );

    // 책 일괄등록
    int addAll(List<BookDto> dtos);



} // inter e
