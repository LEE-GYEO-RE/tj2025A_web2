package 평가5;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ReviewDto {
    private int rno;
    private int mno;
    private String r_title;
    private String r_content;
    private String r_pwd;

}
