package example.실습.실습3;


import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface BookMapper {

    // 도서 대출 기록 저장
    @Insert(" insert into rentals (id , book_id , member ) values " +
            "( #{id} , #{book_id} , #{member} ) ")
    public int loan(RentalsDto rentalsDto);
    // 대출 후 도서 재고 업데이트
    @Update(" update books set stock = stock -1 where id = #{id}" +
            " and stock > 0 ")
    public int update1(int id);


    // 도서 반납 기록 저장
    @Update(" update rentals set return_date = now()" +
            " where book_id = #{book_id} and member = #{member} and return_date is null limit 1 ")
    public int checkout( RentalsDto rentalsDto );
    // 반납 후 재고 업데이트
    @Update(" update books set stock = stock + 1 where id = #{book_id} ")
    public int update2( int id );

}
