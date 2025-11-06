package example2.day03._자바참조;

import lombok.Data;

@Data
public class Board {

    private int bno; // 게시물 번호
    private String btitle; // 게시물 제목
    private String bcontent; // 게시물 내용
    private Category category; // FK 참조



} // class e
