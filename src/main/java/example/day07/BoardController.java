package example.day07;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/board") // + 공통 URL
@RequiredArgsConstructor
@CrossOrigin( value = "http://localhost:5173" ) // CORS( 서로 다른 서버간의 요청/응답 허용 ) 정책을 설정
public class BoardController {
    private final BoardService boardService;

    // 1. 게시물 등록
    @PostMapping("")
    public ResponseEntity<Boolean> boardWrite(@RequestBody BoardDto boardDto ){
        boolean result = boardService.boardWrite(boardDto);
        return ResponseEntity.status( 200 ).body(result);
    } // func e

    // 2. 게시물 전체조회
    @GetMapping("")
    public ResponseEntity<List<BoardDto>> boardPrint(){
        List<BoardDto> result = boardService.boardPrint();
        return ResponseEntity.status( 200 ).body(result);
    } // func e

    // 3. 게시물 개별조회
    @GetMapping("/find")
    public ResponseEntity<BoardDto> boardFind(@RequestParam int bno){
        BoardDto result = boardService.boardFind(bno);
        return ResponseEntity.status( 200 ).body(result);
    } // func e

    // 4. 게시물 삭제
    @DeleteMapping("")
    public ResponseEntity<Boolean> boardDelete(@RequestParam int bno){
        Boolean result = boardService.boardDelete(bno);
        return ResponseEntity.status( 200 ).body( result);
    } // func e

    // 5. 게시물 수정
    @PutMapping("")
    public ResponseEntity<Boolean> boardUpdate(@RequestBody BoardDto boardDto ){
        Boolean result = boardService.boardUpdate(boardDto);
        return ResponseEntity.status( 200 ).body(result);
    } // func e

} // class e

