package example.실습.실습5;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookXmlService {

    private final BookXmlMapper bookXmlMapper;


    // 1. ALTER - 컬럼 추가
    public void alter1() {
        bookXmlMapper.alter1();
    } // func e

    // 2. ALTER - 컬럼 수정
    public void alter2() {
        bookXmlMapper.alter2();
    } // func e

    // 3. 평균 재고보다 많은 도서
    public List<BookDto> query1() {
        return bookXmlMapper.query1();
    } // func e

    // 4. 가장 많이 대출된 도서
    public BookDto query2() {
        return bookXmlMapper.query2();
    } // func e


} // class e
