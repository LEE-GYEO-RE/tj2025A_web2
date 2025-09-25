package example.실습.실습3;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookMapper bookMapper;

    // 대출 기록 저장 후 재고 업데이트
    @Transactional
    public boolean loan( RentalsDto rentalsDto ){
        // 기록 저장
        int loan = bookMapper.loan(rentalsDto);

        // 기록저장 예외처리
        if( loan == 0 ){
            throw new RuntimeException("대출 실패");
        }

        // 재고 업데이트
        int update = bookMapper.update1(rentalsDto.getBook_id());

        // 재고 업데이트 예외처리
        if( update == 0 ){
            throw new RuntimeException("재고 없음");
        }

        return true;
    } // func e


    // 반납 기록 저장 후 재고 업데이트
    @Transactional
    public boolean checkout( RentalsDto rentalsDto ){

        // 기록 저장
        int checkout = bookMapper.checkout(rentalsDto);

        // 기록 저장 예외
        if( checkout == 0 ) {
            throw new RuntimeException("반납 실패");
        }

        // 재고 업데이트
        int update = bookMapper.update2(rentalsDto.getBook_id());

        // 재고 업데이트 예외
        if( update == 0){
            throw new RuntimeException("책 없음");
        }

        return true;
    } // func e


} // class e
