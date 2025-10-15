package example.실습.실습5;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookXmlService {

    private final BookXmlMapper bookXmlMapper;


    // books 테이블에 가격 필드 추가
    public void alter1(){
        try {
        bookXmlMapper.alter1();
            System.out.println("BookXmlService.alter1");
        }catch (Exception e ){
        e.printStackTrace();
        }
    }


    // books 테이블에 title 책 필드 수정

    // 평균 재고보다 많은 재고 가진 도서 조회

    // 가장 많이 대출한 도서 조회


} // class e
