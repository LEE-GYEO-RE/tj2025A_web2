package 평가5;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
@CrossOrigin( value = "http://localhost:5173" )
public class MovieController {

    private final MovieService movieService;

    // 영화 등록
    @PostMapping("")
    public ResponseEntity<Boolean> movieWrite( @RequestBody MovieDto movieDto ){
        boolean result = movieService.movieWrite(movieDto);
        return ResponseEntity.status( 200 ).body(result);
    } // func e

    // 영화 삭제
    @DeleteMapping("")
    public ResponseEntity<Boolean> deleteMovie( @RequestParam int mno , @RequestParam String pwd){
        boolean result = movieService.deleteMovie( mno, pwd);
        return ResponseEntity.status( 200 ).body(result);
    } // func e

    // 영화 목록 조회
    @GetMapping("")
    public ResponseEntity<List<MovieDto>> movieList(){
        List<MovieDto> result = movieService.movieList();
        return ResponseEntity.status( 200 ).body(result);
    } // func e

    // 토론글 작성
    @PostMapping("/review")
    public ResponseEntity<Boolean> reviewWrite( @RequestBody ReviewDto reviewDto){
        boolean result = movieService.reviewWrite( reviewDto );
        return ResponseEntity.status( 200 ).body(result);
    } // func e

    // 토론글 삭제
    @DeleteMapping("/review")
    public ResponseEntity<Boolean> deleteReview( @RequestParam int rno , @RequestParam String r_pwd){
        Boolean result = movieService.deleteReview( rno, r_pwd);
        return ResponseEntity.status( 200 ).body(result);
    } // func e

    // 토론글 전체 조회
    @GetMapping("/allreview")
    public ResponseEntity<List<ReviewDto>> reviewList(){
        List<ReviewDto> result = movieService.reviewList();
        return ResponseEntity.status( 200 ).body(result);
    } // func e

    // 토론글 개별 조회
    @GetMapping("/review")
    public ResponseEntity<List<ReviewDto>> reviewPrint( @RequestParam int mno ){
        List<ReviewDto> result = movieService.reviewPrint(mno);
        return ResponseEntity.status( 200 ).body(result);
    } // func e

} // class e
