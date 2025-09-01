package example.실습.실습1;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

@Repository
public class 실습1Dao {

    private String db_url="jdbc:mysql://localhost:3306/springweb2";
    private String db_user="root";
    private String db_pwd="1234";

    public Connection conn;

    public 실습1Dao() {connect();}

    private void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(db_url, db_user, db_pwd);
            System.out.println("Dao.connect");
        } catch (Exception e) {System.out.println(e);}//catch end
    }//func end


    // 모든 제품 재고 3개씩 감소
    public boolean updateStock(){
        try {
            String sql = "update products set stock_quantity = stock_quantity - 3";
            PreparedStatement ps = conn.prepareStatement(sql);
            int count = ps.executeUpdate();
            return count >= 1;
        }catch (Exception e ){
            System.out.println(e);
        }
        return false;
    } // func e

    // 모든 제품 정보 조회
    public List<Map<String , Object>> productsPrint(){
        List<Map<String , Object>> list = new ArrayList<>();
        try {
            String sql = "select * from products";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Map<String , Object> map = new HashMap<>();
                map.put("product_name" , rs.getString("product_name") );
                map.put("stock_quantity" , rs.getInt("stock_quantity"));
                list.add(map);
            }
        }catch (Exception e ){
            System.out.println(e);
        }
        return list;
    } // func e

    // 재고 10 이하인 상품 재고 +20개 추가
    public boolean productsStock(){
        try {
            String sql = "update products set stock_quantity = stock_quantity + 20 where stock_quantity <= 10";
            PreparedStatement ps = conn.prepareStatement(sql);
            int count = ps.executeUpdate();
            return count >= 1;
        }catch (Exception e ){
            System.out.println(e);
        }
        return false;
    } // func e


} // class e
