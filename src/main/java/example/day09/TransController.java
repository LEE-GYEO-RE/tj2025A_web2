package example.day09;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/day09/trans")
@RequiredArgsConstructor
public class TransController {

    private final TransService transService;

    // 1.
    @PostMapping
    public boolean trans1(){
        return transService.trans1();
    }

    // 2. 신동엽이 서장훈에게 10만원 보내는 예제
    @PostMapping("/transfer")
    public boolean transfer(@RequestBody Map< String , Object > transInfo ){
        return transService.transfer(transInfo);
    }

} // class e
