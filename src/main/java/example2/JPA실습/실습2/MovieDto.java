package example2.JPA실습.실습2;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {

    private int movieId;
    private String title;
    private String director;
    private String releaseDate;
    private int rating;

    public MovieEntity toEntity(){
        return MovieEntity.builder()
                .movieId(this.movieId)
                .title(this.title)
                .director(this.director)
                .releaseDate(this.releaseDate)
                .rating(this.rating)
                .build();
    }
}
