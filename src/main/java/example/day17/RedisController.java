package example.day17;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {

    // [*] 간단한 텍스트를 레디스에 접근하는 객체
    private final RedisTemplate<String , Object> redisTemplate;

    // [1] 간단한 텍스트를 레디스 서버에 저장하고 호출하기 (원래는 Service에서)
    @GetMapping("/test1")
    public ResponseEntity<?> test1(){
        System.out.println("RedisController.test1");

        // [저장] 레디스 템플릿객체명.opsForValue().set( key , value ); , key값은 중복이 안되므로 중복이면 덮어쓰기 적용
        // { "유재석" : "90" } , { "강호동" : "80" }
        redisTemplate.opsForValue().set( "유재석" , "90" ); // 임의 데이터 저장1
        redisTemplate.opsForValue().set( "강호동" , "80" ); // 임의 데이터 저장2
        redisTemplate.opsForValue().set( "유재석" , "100" ); // key는 중복 허용하지 않고 , value는 중복 허용

        // [모든 키 호출]
        Set<String> keys = redisTemplate.keys("*");
            // List vs Map vs Set 컬렉션 프레임워크
        List<Object> result = new ArrayList<>(); // 임의의 리스트
        for( String key : keys ){
            result.add(redisTemplate.opsForValue().get(key));
        }
        return ResponseEntity.ok(result);

    } // func e

    // day13/day06 crud를 데이터베이스 없이 레디스로 실습 변환

    private final RedisTemplate<String , Object> studentTemplate;

    // 1. 등록
    @PostMapping("")
    private ResponseEntity<?> save(@RequestBody StudentDto studentDto){
        System.out.println("studentDto = " + studentDto);
        // 0. 중복없는 key 구성
        String key = "student:" + studentDto.getSno();  // sno를 key로 조합하여 , 예] student: 1 , student: 2
        // 1. 레디스에 전달받은 값 저장
        // 예상 : { "student:1" : {"sno" : 1 , "name" : "강호동" , "math" : 90 , kor : 100 } }
        studentTemplate.opsForValue().set(key , studentDto );
        return ResponseEntity.ok().body("저장 성공");
    } // func e

    // 2. 전체 조회
    @GetMapping("")
    private ResponseEntity<?> findAll(){
        // 0. 조회할 key를 모두 가져온다 * : 레디스내 모든 키 , xxx:* : xxx까지 동일하고 * 자리는 임의의 문자 대응
        // studentTemplate.keys("문자열:*"); // 문자열까지는 동일하면 * 위치는 서로다른 문자열 패턴
        Set<String> keys = studentTemplate.keys("student:*");
        // 1. 반복문 이용한 value 꺼내서 리스트에 담기

        List<Object> list = new ArrayList<>();
        for( String key : keys){
            list.add(studentTemplate.opsForValue().get(key));
        }
        return ResponseEntity.ok().body(list);
    } // func e


    // 3. 개별 조회
    @GetMapping("/find")
    public ResponseEntity<?> find(@RequestParam int sno){
        String key = "student:" + sno;
        Object result = studentTemplate.opsForValue().get(key);
        return ResponseEntity.ok().body(result);
    } // func e

    // 4. 개별 삭제
    @DeleteMapping("")
    public ResponseEntity<?> delete(@RequestParam int sno){
        String key = "student:" + sno;
        Boolean result = studentTemplate.delete(key);
        return ResponseEntity.ok().body(result);
    } // func e

    // 4. 개별 수정
    @PutMapping("")
    public ResponseEntity<?> update(@RequestBody StudentDto studentDto){
        String key = "student:" + studentDto.getSno();
        studentTemplate.opsForValue().set( key , studentDto );
        return ResponseEntity.ok().body("수정 완료");
    } // func e


    // * 인증코드 발급 해서 래디스 유효기간 정하기
    // TTL : 레디스에 저장된 엔트리(key-value) 를 특정한 기간(시간)이 되면 자동 삭제
    @GetMapping("/auth/send")
    public ResponseEntity<?> authSend(@RequestParam String phone){

        // 1. key 구상 , "auth:고객전화번호"
        String key = "auth:" + phone; // 번호마다 1개씩 인증코드

        // 2. 인증코드 난수 6자리 생성
        String code = String.format("%06d" , new Random().nextInt(999999));

        // 3. Redis에 저장
        redisTemplate.opsForValue().set(key , code , Duration.ofSeconds(10)); // 10초 유효기간 설정

        // (컨셉잡는 데로 알아서) 4. API 이용하여 고객 전화번호에게 인증코드 전송

        return ResponseEntity.ok().body("인증코드 발급 완료 :" + code);

    } // func e

    @GetMapping("/auth/verify")
    public ResponseEntity<?> verifyCode(@RequestParam String phone , @RequestParam String code){

        // 1. key 구상
        String key = "auth:" + phone;

        // 2. 조회할 key 이용한 value 호출
        Object result = redisTemplate.opsForValue().get(key);

        // 3. 비교
        if(result == null ) {
            return ResponseEntity.ok().body("인증 실패 : 인증 만료 또는 코드 불일치");
        }else if (result.equals(code) ) {
            redisTemplate.delete(key); // 인증성공하면 인증코드 삭제
            return ResponseEntity.ok().body("인증 성공!");
        }else {
            return ResponseEntity.ok().body("인증실패");
        }
    } // func e

} // class e
