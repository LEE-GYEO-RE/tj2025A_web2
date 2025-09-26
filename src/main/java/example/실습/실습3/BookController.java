package example.실습.실습3;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // 대출 기록 저장 후 재고 업데이트
    @PostMapping("/loan")
    public ResponseEntity<Boolean> loan(@RequestBody RentalsDto rentalsDto){
        boolean result = bookService.loan(rentalsDto);
        return ResponseEntity.status( 200 ).body(result);
    }

    // 반납 기록 저장 후 재고 업데이트
    @PostMapping("/checkout")
    public ResponseEntity<Boolean> checkout( @RequestBody RentalsDto rentalsDto ){
        boolean result = bookService.checkout(rentalsDto);
        return ResponseEntity.status( 200 ).body(result);
    }

} // class e
