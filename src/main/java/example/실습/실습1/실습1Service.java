package example.실습.실습1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class 실습1Service {
    @Autowired 실습1Dao dao;

    // 30초 마다 제품 재고 3개 감소
    @Scheduled(cron = "0/30 * * * * *") // 0넣으면 30초마다
    public void updateStock(){
        System.out.println("실습1Service.updateStock");
        dao.updateStock();
    } // func e

    // 1분마다 모든 제품 정보 조회
    @Scheduled(cron = "0 0/1 * * * *") // 0 넣으면 1분마다 , 0/ 빼면 매시각 1분마다
    public void productsPrint(){
        System.out.println("실습1Service.productsPrint");
        dao.productsPrint();
    } // func e

    // 5분마다 재고 10 이하인 상품 재고 +20 추가
    @Scheduled(cron = "0 0/5 * * * *")
    public void productsStock(){
        System.out.println("실습1Service.productsStock");
        dao.productsStock();
    } // func e

} // class e
