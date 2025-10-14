package example.실습.실습4;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final XmlMapper xmlMapper;

    // [1] 책 등록
    @PostMapping("")
    public ResponseEntity<Integer> addBook(@RequestBody BookDto dto ){
        xmlMapper.addBook(dto);
        int book_id = dto.getId();
        return ResponseEntity.ok(book_id);
    } // func e

    // [2] 대출 기록 검색 조회
    @PostMapping("/loanrecord")
    public ResponseEntity<List<RentalsDto>> loanRecord(@RequestBody RentalsDto dto){
        List<RentalsDto> result = xmlMapper.loanRecord( dto.getMember() , dto.getBook_id() );
        return ResponseEntity.ok(result);
    } // func e

    // [3] 책 일괄 등록
    @PostMapping("/all")
    public ResponseEntity<List<Integer>> addAll( @RequestBody List<BookDto> dtos){
        int result = xmlMapper.addAll(dtos);
        if(result > 0 ){
            List<Integer> list = new ArrayList<>();
            for( BookDto book : dtos ){
                list.add(book.getId());
            }
            return ResponseEntity.ok(list);
        }
        return ResponseEntity.status(404).body(null);
    } // func e

} // class e
