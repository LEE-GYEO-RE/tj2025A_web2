package example.실습.실습1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling // 스케줄링 활성화
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class);

        // [ 조건 ]
        //   1. 최소 클래스 파일을 사용하여 구현 : AppStart , TaskService , TaskDao
        //   2. 매 30초마다 모든 제품의 재고는 3개씩 감소한다.
        //   3. 매 1분마다 모든 제품 정보를 조회/출력 한다. *console 화면에 모든 제품 정보가 보이도록*
        //   4. 매 5분마다 재고가 10 이하인 상품의 재고를 +20개 추가한다.
    } // main e
} // class e
