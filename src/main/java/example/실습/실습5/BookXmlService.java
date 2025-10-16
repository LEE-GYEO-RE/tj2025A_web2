package example.실습.실습5;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class BookXmlService {

    private final BookXmlMapper bookXmlMapper;

    // ==== 실습 5 ====

    // 1. ALTER - 컬럼 추가
    public void alter1() {
        bookXmlMapper.alter1();
    } // func e

    // 2. ALTER - 컬럼 수정
    public void alter2() {
        bookXmlMapper.alter2();
    } // func e

    // 3. 평균 재고보다 많은 도서
    public List<BookDto> query1() {
        return bookXmlMapper.query1();
    } // func e

    // 4. 가장 많이 대출된 도서
    public BookDto query2() {
        return bookXmlMapper.query2();
    } // func e

    // ==== 실습 6 ====

    // 1. view1 생성
    public void view1(){
        bookXmlMapper.view1();
    } // func e

    // 2. view2 생성
    public void view2(){
        bookXmlMapper.view2();
    } // func e

    // 3. view1 조회
    public Map<String , Object >selectView1(){
        return bookXmlMapper.selectView1();
    } // func e

    // 4. view2 조회
    public List<BookDto> selectView2(){
        return bookXmlMapper.selectView2();
    } // func e


} // class e
