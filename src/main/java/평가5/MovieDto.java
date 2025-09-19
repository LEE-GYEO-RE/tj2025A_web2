package 평가5;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MovieDto {
    private int mno;
    private String title;
    private String director;
    private String genre;
    private String content;
    private String pwd;
}
