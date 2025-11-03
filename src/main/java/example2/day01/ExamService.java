package example2.day01;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // final 필드에 대한 Autowired
public class ExamService {

    // * 매퍼 객체 --> 리포지토리 객체
    private final ExamRepository examRepository;

    // 1. C , 등록
    public ExamEntity post( ExamEntity examEntity ){
        // 1. 저장할 엔티티를 (매개변수) 받는다.
        // 2. .save() : 리포지토리의 저장 메소드 , 저장 성공시 성공된 엔티티 반환
        ExamEntity saveEntity = examRepository.save(examEntity); // insert 자동 처리
        // 엔티티객체(레코드) , 엔티티(테이블)
        return saveEntity;
    } // func e

    // 2. R , 전체 조회
    public List<ExamEntity> get(){
        // 1. findAll() , 리포지토리의 전체조회 메소드 , 모든 엔티티 객체를 반환
        List<ExamEntity> entityList = examRepository.findAll(); // select 자동 처리
        return entityList;

    } // func e

    // 3-1. U , 특정한 엔티티 수정 : 자동으로 생성 , 수정해서 불안정성 증가
    public ExamEntity put( ExamEntity examEntity){
        // 1. 수정할 엔티티 매개변수로 받기(pk포함)
        // 2. save( 수정할 엔티티)
            // 만약 지정한 엔티티에 pk가 없으면 생성
            // 만약 지정한 엔티티에 존재하면 수정
        ExamEntity putEntity = examRepository.save(examEntity);
        return putEntity;
    } // func e

    // 3-2. U , 특정한 엔티티 수정
    // 주의할점 : 엔티티를 setter 하면 자동으로 DB도 변경된다. 즉 , 엔티티 서비스에서만 써야함
    @Transactional
    public ExamEntity put2( ExamEntity examEntity){
        // 1. 수정할 엔티티 조회 , findAll() : 전체 조회 , findById() : PK 한개 조회
        Optional<ExamEntity> optional = examRepository.findById(examEntity.getCol1());
        // 2. Optional : 자바에서 자주 발생하는 NullPointer 예외 보장 클래스
        // 즉 , null 값에 대한 안전하게 유효성 기능 제공.
        if(optional.isPresent()){ // .isPresent() : 결과물 확인 , 본문 존재하는 지 검사
            // 만약 결과에 entity가 존재하면
            ExamEntity examEntity1 = optional.get();
            examEntity1.setCol2(examEntity.getCol2()); // setter 이용한 엔티티 값 수정
            examEntity1.setCol3(examEntity.getCol3()); // setter 이용한 엔티티 값 수정
            return examEntity1;
        }
        return examEntity;

    } // func e

    // 4. D , 특정한 엔티티 삭제
    public boolean delete( int col1 ){
        // 1. 삭제할 pk번호 받기
        // 2. deleteByID(PK번호)
        examRepository.deleteById(col1);
        return true;
    } // func e

} // class e
