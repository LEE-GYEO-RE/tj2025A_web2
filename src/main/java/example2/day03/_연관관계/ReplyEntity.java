package example2.day03._연관관계;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ereply")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rno; // 댓글 번호
    private String rcontent; // 댓글 내용

    // 단방향 연결
    @ManyToOne( cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "bno") // FK 필드명
    private BoardEntity boardEntity;



} // class e

// 카테고리 <--- 게시물 <--- 댓글
