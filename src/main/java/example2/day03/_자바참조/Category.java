package example2.day03._자바참조;

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data // 룸북
public class Category {

    // 1. 멤버 변수
    private int cno; // 카테고리 번호
    private String cname; // 카테고리 명

    @ToString.Exclude // ToString 제외
    List<Board> boardList = new ArrayList<>(); // PK가 갖는 FK

    // 2. 생성자

    // 3. 메소드

} // class e
