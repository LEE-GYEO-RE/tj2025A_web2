package example2.JPA실습.실습1;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    // 1. 등록
    @PostMapping
    public ResponseEntity<?> post(@RequestBody BookEntity book){
        BookEntity result = bookService.post(book);
        return ResponseEntity.ok().body(result);
    } // func e

    // 2. 전체 조회
    @GetMapping
    public ResponseEntity<?> get(){
        List<BookEntity> result = bookService.get();
        return ResponseEntity.ok().body(result);
    } // func e

    // 3. 수정
    @PutMapping
    public ResponseEntity<?> put(@RequestBody BookEntity book){
        BookEntity result = bookService.put(book);
        return ResponseEntity.ok().body(result);
    } // func e

    // 4. 삭제
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int id){
        Boolean result = bookService.delete(id);
        return ResponseEntity.ok().body(result);
    } // func e


} // class e
