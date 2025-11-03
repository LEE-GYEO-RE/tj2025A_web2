package example2.day01;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // 지정한 엔티티(테이블) 조작하는 인터페이스 주입
public interface ExamRepository
        extends JpaRepository<ExamEntity , Integer> {
    // extends 상속 : 특정한 클래스로 부터 물려받는 행위
    // extends JpaRepository< T , ID >
    // < > 제네릭 : 객체 생성시 해당 타입으로 주입되는 타입
    // *제네릭은 기본타입 불가능*
    // *래퍼클래스 : (int->Integer)처럼 기본타입 포장해서 참조타입 만든다
        // T : 조작할 테이블(엔티티클래스 지칭)
        // ID : 조작할 테이블의 ID(PK) 자료형

} // inter e
