package example.day07;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardMapper boardMapper;

    // 1. 게시물 등록
    public boolean boardWrite( BoardDto boardDto ){
        boolean result = boardMapper.boardWrite(boardDto);
        return result;
    } // func e

    // 2. 게시물 전체조회
    public List<BoardDto> boardPrint(){
        List<BoardDto> result = boardMapper.boardPrint();
        return result;
    } // func e

    // 3. 게시물 개별조회
    public BoardDto boardFind(int bno){
        BoardDto result = boardMapper.boardFind(bno);
        return result;
    } // func e

    // 4. 게시물 삭제
    public boolean boardDelete( int bno ){
        boolean result = boardMapper.boardDelete(bno);
        return result;
    } // func e

    // 5. 게시물 수정
    public boolean boardUpdate( BoardDto boardDto ){
        boolean result = boardMapper.boardUpdate(boardDto);
        return result;
    } // func e

} // class e