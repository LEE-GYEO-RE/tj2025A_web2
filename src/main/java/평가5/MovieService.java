package 평가5;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieMapper movieMapper;

    // 영화 등록
    public boolean movieWrite( MovieDto movieDto ){
        boolean result = movieMapper.movieWrite( movieDto);
        return result;
    } // func e

    // 영화 삭제
    public boolean deleteMovie( int mno , String pwd){
        boolean result = movieMapper.deleteMovie( mno, pwd);
        return result;
    } // func e

    // 영화 목록 조회
    public List<MovieDto> movieList(){
        List<MovieDto> result = movieMapper.movieList();
        return result;
    } // func e

    // 토론글 작성
    public boolean reviewWrite( ReviewDto reviewDto ){
        boolean result = movieMapper.reviewWrite(reviewDto);
        return result;
    } // func e

    // 토론글 삭제
    public boolean deleteReview( int rno , String r_pwd ){
        int result = movieMapper.deleteReview( rno, r_pwd);
        return result > 0;
    } // func e

    // 토론글 전체 조회
    public List<ReviewDto> reviewList(){
        List<ReviewDto> result = movieMapper.reviewList();
        return result;
    } // func e

    // 토론글 개별 조회
    public List<ReviewDto> reviewPrint(int mno ){
        List<ReviewDto> result = movieMapper.reviewPrint( mno );
        return result;
    }

} // class e
