package example2.JPA실습.실습2;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/movie")
public class MovieController {

    private final MovieService movieService;

    // 등록
    @PostMapping
    public ResponseEntity<?> post(@RequestBody MovieDto movieDto){
        return ResponseEntity.ok().body(movieService.post(movieDto));
    } // func e

    // 전체 조회
    @GetMapping
    public ResponseEntity<?> find(){
        return ResponseEntity.ok().body(movieService.find());
    } // func e

    // 개별 조회
    @GetMapping("/id")
    public ResponseEntity<?> findId(@RequestParam int movieId){
        return ResponseEntity.ok().body(movieService.findId(movieId));
    } // func e

    // 개별 수정
    @PutMapping
    public ResponseEntity<?> put(@RequestBody MovieDto movieDto){
        return ResponseEntity.ok().body(movieService.put(movieDto));
    } // func e

    // 개별 삭제
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int movieId){
        return ResponseEntity.ok().body(movieService.delete(movieId));
    } // func e


} // class e
