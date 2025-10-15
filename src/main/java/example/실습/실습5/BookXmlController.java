package example.실습.실습5;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alter")
@RequiredArgsConstructor
public class BookXmlController {

    private final BookXmlService bookXmlService;

    // books 테이블에 가격 필드 추가
    @PostMapping("/price")
    public ResponseEntity<String> alter1(){
        bookXmlService.alter1();
        return ResponseEntity.ok("alter 성공");
    }

    // books 테이블에 title 책 필드 수정

    // 평균 재고보다 많은 재고 가진 도서 조회

    // 가장 많이 대출한 도서 조회


} // class e
