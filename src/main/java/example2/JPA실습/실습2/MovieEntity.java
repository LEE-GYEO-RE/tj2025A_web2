package example2.JPA실습.실습2;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie")
@Data@Builder@NoArgsConstructor@AllArgsConstructor
public class MovieEntity extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int movieId; // 영화번호

    @Column(nullable = false , length = 100)
    private String title;

    @Column(nullable = false , length = 100)
    private String director;

    @Column(nullable = false , length = 100)
    private String releaseDate;

    @Column(nullable = false , length = 100)
    private int rating;

    public MovieDto toDto(){
        return MovieDto.builder()
                .movieId(this.movieId)
                .title(this.title)
                .director(this.director)
                .releaseDate(this.releaseDate)
                .rating(this.rating)
                .build();
    }


}
