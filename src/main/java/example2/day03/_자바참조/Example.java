package example2.day03._자바참조;

public class Example {
    public static void main(String[] args) {

        System.out.println("출력");


        // JPA는 영속성 : 데이터베이스랑 자바랑 비슷
        // 데이터베이스의 테이블 == 클래스 == 엔티티클래스
        // 데이터베이스의 테이블내 행/레코드(1줄) == 인스턴스 == 엔티티객체

        // [1] 카테고리 2개 생성 , PK( 상위 테이블 )
        Category category1 = new Category();
        category1.setCno(1); category1.setCname("공지사항");
        Category category2 = new Category();
        category2.setCno(2); category2.setCname("자유게시판");

        // [2] 게시물 생성 FK( 하위 테이블 )
        // 공지사항에 기시물 작성
        Board board1 = new Board();
        board1.setBno(1);
        board1.setBtitle("공지1");
        board1.setBcontent("공지사항");
        //** 1번 게시물에 1번(공지사항객체) 참조 **//
        board1.setCategory(category1);
        System.out.println(board1.getCategory().getCname());

        // [3] 공지사항 데이터로 게시물 조회 , <양방향>
        // 공지사항 객체에 1번 게시물을 대입한다.
        category1.getBoardList().add(board1);
        System.out.println(category1.getBoardList());

        // ToString : 객체 호출시 참조 주소값 대신 문자열로 멤버변수로 뭐가 있는지 출력
        // 즉] 멤버변수가 어떤 게 있는지 확인

        // [4] 상황1 : 1번 공지사항에 게시물 작성
        Board board2 = new Board();
        board2.setBno(2); board2.setBtitle("공지2");
        board2.setBcontent("공지내용2");
        // ** 단방향 참조 ** //
        board2.setCategory(category1);
        // ** 양방향 참조 ** //
        category1.getBoardList().add(board2);

        // 상황1 결과 : 카테고리로 게시물 조회 , 게시물로 카테고리 조회
        System.out.println(category1.getBoardList());
        System.out.println(board2.getCategory());



    } // main e
} // class e
