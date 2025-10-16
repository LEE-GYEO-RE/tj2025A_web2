package example.실습.실습5;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/alter")
@RequiredArgsConstructor
public class BookXmlController {

    private final BookXmlService bookXmlService;

    // ==== 실습 5 ====

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

    // ==== 실습 6 ====

    // 1. view1 생성
    @PutMapping("/view1")
    public String view1(){
        bookXmlService.view1();
        return " view1 생성 ";
    } // func e

    // 2. view2 생성
    @PutMapping("/view2")
    public String view2(){
        bookXmlService.view2();
        return "view2 생성";
    } // func e

    // 3. view1 조회
    @GetMapping("/selectview1")
    public Map<String , Object>selectView1(){
        return bookXmlService.selectView1();
    } // func e

    // 4. view2 조회
    @GetMapping("/selectview2")
    public List<BookDto> selectView2(){
        return bookXmlService.selectView2();
    } // func e


} // class e
