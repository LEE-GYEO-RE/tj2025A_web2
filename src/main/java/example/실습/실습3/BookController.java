package example.실습.실습3;

import lombok.RequiredArgsConstructor;
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
    public boolean loan(@RequestBody RentalsDto rentalsDto){
        return bookService.loan(rentalsDto);
    }

    // 반납 기록 저장 후 재고 업데이트
    @PostMapping("/checkout")
    public boolean checkout( @RequestBody RentalsDto rentalsDto ){
        return bookService.checkout(rentalsDto);
    }

} // class e
