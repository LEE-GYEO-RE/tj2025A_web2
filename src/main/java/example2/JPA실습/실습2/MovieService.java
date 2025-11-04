package example2.JPA실습.실습2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MovieService {

    private final MovieRepository movieRepository;

    // 등록
    public MovieDto post( MovieDto movieDto){
        MovieEntity movieEntity = movieDto.toEntity();
        MovieEntity result = movieRepository.save(movieEntity);

        if( result.getMovieId() >= 0 ){
            return result.toDto();
        }
        return movieDto;
    } // func e

    // 전체 조회
    public List<MovieDto> find(){
        List<MovieEntity> movieEntityList = movieRepository.findAll();

        return movieEntityList
                .stream()
                .map(MovieEntity :: toDto)
                .collect(Collectors.toList());
    } // func e

    // 개별 조회
    public MovieDto findId(int movieId){
        Optional<MovieEntity> optional = movieRepository.findById(movieId);

        if(optional.isPresent()){
            MovieEntity movieEntity = optional.get();
            return movieEntity.toDto();
        }
        return null;
    } // func e

    // 개별 수정
    public MovieDto put(MovieDto movieDto){
        Optional<MovieEntity> movieEntity = movieRepository.findById(movieDto.getMovieId());

        if(movieEntity.isPresent()){
            MovieEntity movie = movieEntity.get();
            movie.setTitle(movieDto.getTitle());
            movie.setDirector(movieDto.getDirector());
            movie.setReleaseDate(movieDto.getReleaseDate());
            movie.setRating(movieDto.getRating());
            return movie.toDto();
        }
        return movieDto;
    } // func e

    // 개별 삭제
    public Boolean delete( int movieId){
        if( movieRepository.existsById(movieId)){
            movieRepository.deleteById(movieId);
            return true;
        }
        return false;
    } // func e


} // class e
