package example.day13;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/xml")
@RequiredArgsConstructor
public class XmlController {

    private final XmlMapper xmlMapper;

    // 1. 등록
    //  탈랜드 body : { "name" : "유재석" ,"kor" : "90" ,"math" : "70" }
    @PostMapping("") // http://localhost:8080/xml
    public ResponseEntity<?> save(@RequestBody StudentDto dto ){
        // <?> : 제네릭타입 ? 넣으면 모든 타입을 지칭함. 와일드카드
        System.out.println("dto = " + dto); // save 실행 전
        int result = xmlMapper.save(dto);
        System.out.println("dto = " + dto); // save 실행 후
        return ResponseEntity.ok(result); // .ok( 100 , true , dto 다 가능)
    } // func e

    // 2. 전체 조회
    @GetMapping("")
    public ResponseEntity<List<StudentDto>> print(){
        List<StudentDto> result = xmlMapper.print();
        return ResponseEntity.ok(result);
    }

    // 3. 개별 조회
    @GetMapping("/find")
    public ResponseEntity<StudentDto> find( @RequestParam int sno ){
        StudentDto result = xmlMapper.find(sno);
        return ResponseEntity.ok(result);
    }

    // 4. 개별 삭제
    @DeleteMapping("")
    public ResponseEntity<?> delete( @RequestParam int sno ){
        xmlMapper.delete(sno);
        return ResponseEntity.ok(true);
    }

    // 5. 개별 수정
    @PutMapping("")
    public ResponseEntity<?> update( @RequestBody StudentDto dto ){
        xmlMapper.update(dto);
        return ResponseEntity.ok(true);
    }

    // 6. if 동적 쿼리 1
    @GetMapping("/query2")
    public ResponseEntity<List<StudentDto>> query2( @RequestParam int kor ){
        List<StudentDto> result = xmlMapper.query2(kor);
        return ResponseEntity.ok(result);
    }

    // 7. if 동적 쿼리 2
    @GetMapping("/query3")
    public ResponseEntity<List<StudentDto>> query3( @RequestParam String name , int math ){
        List<StudentDto> result = xmlMapper.query3( name , math );
        return ResponseEntity.ok(result);
    }

    // 8. forEach 동적 쿼리
    @PostMapping("/all")
    public ResponseEntity<Integer> saveAll( @RequestBody List<StudentDto> dto ){
        int result = xmlMapper.saveAll(dto);
        return ResponseEntity.ok(result);
    }



} // class e
