package example.day07;

import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    // 1. 게시물 등록
    @Insert("insert into board(bcontent , bwriter)values(#{bcontent},#{bwriter}) ")
    public boolean boardWrite( BoardDto boardDto);

    // 2. 게시물 전체조회
    @Select("select * from board")
    public List<BoardDto> boardPrint();

    // 3. 게시물 개별조회
    @Select("select * from board where bno = #{bno}")
    public BoardDto boardFind( int bno );

    // 4. 게시물 삭제
    @Delete("delete from board where bno = #{ bno }")
    public boolean boardDelete( int bno );

    // 5. 게시물 수정
    @Update("update board set bcontent = #{bcontent} where bno =#{bno} ")
    public boolean boardUpdate(BoardDto boardDto );

} // class e
