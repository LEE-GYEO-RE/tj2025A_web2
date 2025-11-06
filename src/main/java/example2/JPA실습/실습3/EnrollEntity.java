package example2.JPA실습.실습3;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data@NoArgsConstructor@AllArgsConstructor
@Builder
@Table(name = "eenroll")
public class EnrollEntity extends BaseTime{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int enrollId;
    private String status;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "courseId")
    private CourseEntity courseEntity;

    @ManyToOne(cascade = CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "studentId")
    private StudentEntity studentEntity;

}
