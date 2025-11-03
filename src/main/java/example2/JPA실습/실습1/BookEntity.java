package example2.JPA실습.실습1;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class BookEntity {

    @Id
    private int id;
    private String name;
    private String author;
    private String publisher;
}
