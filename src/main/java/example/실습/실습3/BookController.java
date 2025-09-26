package example.실습.실습3;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
@Log4j2 // ========== 로그(기록) 처리하는 어노테이션 제공 ================
public class BookController {

    private final BookService bookService;

    // 2. print 함수 대신 로그함수 사용해보기 = 부가기능 제공 , 제약조건 등
    @GetMapping("/log")
    public void log(){
        System.out.println(" 개발단계 에서는 print 많이 작성해야함 ");
        // log.XXXX : 출력함수 처럼 출력하는 메시지 함수이면서 부가기능(파일처리/제약조건 등등)
        log.debug("테스트 과정 사용된다."); // 테스트(개발) 과정에서 메시지
        log.info("서비스의 흐름, 데이터 상태 사용된다."); // 운용 과정 메시지
        log.warn("잠재적 문제 사용된다."); // 유지보수 과정 메시지
        log.error("예외/실패 상황 사용된다."); // 운용/유지보수 과정 메시지
    }

    // 대출 기록 저장 후 재고 업데이트
    @PostMapping("/loan")
    public ResponseEntity<Boolean> loan(@RequestBody RentalsDto rentalsDto){
        log.debug("대여 신청" + rentalsDto);
        boolean result = false;
        try {
            // 만약 커밋이면
            result = bookService.loan(rentalsDto);
            log.debug("대여 성공" + rentalsDto );
            return ResponseEntity.ok(result);
        }catch (RuntimeException e ){
            // 만약 롤백이면 상태 프론트에 뿌리기
            log.debug("대여실패" + rentalsDto + e.getMessage());
            return ResponseEntity.status(405).body(result);
        }
    }

    // 반납 기록 저장 후 재고 업데이트
    @PostMapping("/checkout")
    public ResponseEntity<Boolean> checkout( @RequestBody RentalsDto rentalsDto ){

        boolean result = false;
        try {
            // 만약 커밋이면
            result = bookService.checkout(rentalsDto);
            return ResponseEntity.ok(result);
        }catch (RuntimeException e ){
            // 만약 롤백이면 상태 프론트에 뿌리기
            return ResponseEntity.status(405).body(result);
        }
    }

} // class e
