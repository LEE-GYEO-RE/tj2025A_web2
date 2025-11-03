package example2.day01;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exam")
public class ExamController {

    private final ExamService examService;

    // 1. C , 등록
    @PostMapping
    public ResponseEntity<?> post(@RequestBody ExamEntity examEntity){
        ExamEntity result = examService.post(examEntity);
        return ResponseEntity.ok().body(result);
    } // func e

    // 2. R , 전체 조회
    @GetMapping
    public ResponseEntity<?> get(){
        List<ExamEntity> result = examService.get();
        return ResponseEntity.ok().body(result);
    } // func e

    // 3-2. U , 특정한 엔티티 수정
    @PutMapping
    public ResponseEntity<?> put( @RequestBody ExamEntity examEntity ){
        ExamEntity result = examService.put2(examEntity);
        return ResponseEntity.ok().body(result);
    } // func e


    // 4. D , 특정한 엔티티 삭제
    @DeleteMapping
    public ResponseEntity<?> delete( @RequestParam int col1){
        boolean result = examService.delete(col1);
        return ResponseEntity.ok().body(result);
    } // func e

}// func e
