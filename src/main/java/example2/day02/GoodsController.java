package example2.day02;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/goods")
public class GoodsController {

    private final GoodsService goodsService;

    // 1. 등록
    @PostMapping
    public ResponseEntity<?> save(@RequestBody GoodsDto goodsDto){
        GoodsDto result = goodsService.save(goodsDto);
        return ResponseEntity.ok().body(result);
    } // func e

    // 2. 전체 조회
    @GetMapping
    public ResponseEntity<?> print(){
        List<GoodsDto> result = goodsService.print();
        return ResponseEntity.ok().body(result);
    } // func e

    // 3. 개별 조회
    @GetMapping("/id")
    public ResponseEntity<?> printId(@RequestParam int gno){
        GoodsDto result = goodsService.printId(gno);
        return ResponseEntity.ok().body(result);
    } // func e

    // 4. 개별 삭제
    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam int gno){
        Boolean result = goodsService.delete(gno);
        return ResponseEntity.ok().body(result);
    } // func e

    // 5. 개별 수정
    @PutMapping
    public ResponseEntity<?> put(@RequestBody GoodsDto goodsDto){
        GoodsDto result = goodsService.put(goodsDto);
        return ResponseEntity.ok().body(result);
    } // func e

} // class e
