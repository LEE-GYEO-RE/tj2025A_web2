package example2.day02;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 엔티티는 서비스에서만 사용 , Controller는 Dto 사용하는게 일반적
// JPA + MyBatis 사용하거나 , 영속 문제 , 보안 등등
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GoodsDto {

    private int gno;
    private int gprice;
    private String create_date;
    private String update_date;
    private String gname;
    private String gdesc;

    // ++++++++++ DTO --> Entity +++++++++
    // ++ Controller -> Service ++
    public GoodsEntity toEntity(){
        return GoodsEntity.builder()
                .gno(this.gno)
                .gprice(this.gprice)
                .gname(this.gname)
                .gdesc(this.gdesc)
                // date 자동이니 제외
                .build();
    }

}
