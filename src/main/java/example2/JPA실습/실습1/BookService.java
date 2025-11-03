package example2.JPA실습.실습1;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    // 1. 등록
    public BookEntity post( BookEntity book ){
        BookEntity saveEntity = bookRepository.save(book);
        return saveEntity;
    } // func e

    // 2. 전체 조회
    public List<BookEntity> get(){
        List<BookEntity> list = bookRepository.findAll();
        return list;
    } // func e

    // 3. 수정
    public BookEntity put( BookEntity book){
        Optional<BookEntity> optional = bookRepository.findById(book.getId());

        if(optional.isPresent()){
            BookEntity bookEntity = optional.get();
            bookEntity.setId(book.getId());
            bookEntity.setAuthor(book.getAuthor());
            bookEntity.setName(book.getName());
            bookEntity.setPublisher(book.getPublisher());
            return bookEntity;
        }
        return book;
    } // func e

    // 4. 삭제
    public boolean delete( int id){
        bookRepository.deleteById(id);
        return true;
    } // func e

} // class e
