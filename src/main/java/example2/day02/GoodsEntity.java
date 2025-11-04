package example2.day02;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // 해당 클래스 데이터베이스 테이블과 매핑
@Table(name = "goods") // 테이블 이름 정의 ,
@Data@Builder@NoArgsConstructor@AllArgsConstructor // 룸북
public class GoodsEntity extends BaseTime {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY) // auto_increment
    private int gno; // 제품번호

    @Column(nullable = false , length = 100) // @Column( 속성명 = 값 , 속성명 = 값 )
    // nullable = false : null 제외
    // length = 100 : 글자수 100
    private String gname; // 제품명        자바 String -> DB varchar(255)

    @Column(nullable = true) // null 포함
    private int gprice; // 제품 가격

    @Column(columnDefinition = "varchar(100) default '제품설명' not null ")
    // columnDefinition = "SQL 구문 직접 작성"
    private String gdesc; // 제품 설명

    // ++++++++ Entity -> DTO +++++++++
    // ++ Service -> Controller ++
    public GoodsDto toDto(){
        // 객체생성방법1 : new 클래스명(값 , 값);
        // 객체생성방법2 : 클래스명.builder().속성명(값1).속성명(값2) :
        return GoodsDto.builder()
                .gno(this.gno) // this : 현재 메소드를 호출한 인스턴스(객체)
                .gname(this.gname)
                .gprice(this.gprice)
                .gdesc(this.gdesc)
                .create_date(this.getCreateDate().toString())
                .update_date(this.getUpdateDate().toString())
                .build();
    }

}
