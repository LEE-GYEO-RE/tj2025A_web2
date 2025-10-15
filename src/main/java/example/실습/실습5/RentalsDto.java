package example.실습.실습5;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RentalsDto {
    private int id;
    private int book_id;
    private String member;
    private String rent_date;
    private String return_date;
}
