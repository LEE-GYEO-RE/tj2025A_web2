package 평가5;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MovieMapper {

    // 영화 등록
    @Insert("insert into movie( title , director , genre , content , pwd ) values " +
            "(#{title} , #{director} , #{genre} , #{content} , #{pwd} )")
    public boolean movieWrite( MovieDto movieDto );

    // 영화 삭제
    @Delete("delete from movie where mno = #{mno} and pwd = #{pwd}")
    public boolean deleteMovie( int mno , String pwd );

    // 영화 목록 조회
    @Select("select * from movie")
    public List<MovieDto> movieList();

    // 토론글 작성
    @Insert("insert into review(mno, r_title, r_content, r_pwd) values " +
            "(#{mno} , #{r_title} , #{r_content} , #{r_pwd}) ")
    public boolean reviewWrite(ReviewDto reviewDto );

    // 토론글 삭제
    @Delete("delete from review where rno = #{rno} and r_pwd = #{r_pwd} ")
    public int deleteReview( int rno , String r_pwd);

    // 토론글 전체 조회
    @Select("select * from review")
    public List<ReviewDto> reviewList();

    // 토론글 개별 조회
    @Select("select * from review where mno = #{mno}")
    public List<ReviewDto> reviewPrint( int mno );




} // class e
