package example.실습.실습5;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alter")
@RequiredArgsConstructor
public class BookXmlController {

    private final BookXmlService bookXmlService;

    // 1. price 필드 추가
    @PutMapping("/price")
    public String alter1() {
        bookXmlService.alter1();
        return " price 컬럼 추가 ";
    } // func e

    // 2. title 필드 타입 수정
    @PutMapping("/title")
    public String alter2() {
        bookXmlService.alter2();
        return " title 컬럼 타입 LONGTEXT로 변경 ";
    } // func e

    // 3. 평균 재고보다 많은 도서 조회
    @GetMapping("/stock")
    public List<BookDto> query1() {
        return bookXmlService.query1();
    } // func e

    // 4. 가장 많이 대출된 도서 조회
    @GetMapping("/most")
    public BookDto query2() {
        return bookXmlService.query2();
    } // func e


} // class e
