package example.실습.실습5;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@CrossOrigin( value = "http://localhost:5173")
public class MemberController {

    private final MemberService memberService;

    // 등록
    @PostMapping("")
    public ResponseEntity<Boolean> addMember(@RequestBody MemberDto memberDto){
        boolean result = memberService.addMember(memberDto);
        return ResponseEntity.status( 200 ).body(result);
    } // func e

    // 삭제
    @DeleteMapping("")
    public ResponseEntity<Boolean> deleteMember( @RequestParam int mno ){
        boolean result = memberService.deleteMember( mno );
        return ResponseEntity.status( 200 ).body(result);
    } // func e

    // 조회
    @GetMapping("")
    public List<MemberDto> memberList(){
        List<MemberDto> result = memberService.memberList();
        return result;
    } // func e


} // class e
