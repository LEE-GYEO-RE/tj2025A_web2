package example.day01._1스프링스레드;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class ThreadService {

    // 1. 반복문 끝날때까지 await 걸림
    public int thread1(){
        // 값 저장 변수
        int result = 0;

        // 반복문
        for( int i = 1 ; i<= 10; i++ ){
            result +=i; // 누적
            System.out.println("ThreadService.thread1");
            // ** 만약 서비스 처리가 늦어진다면 반환 어떻게 되는지? **
            // 예시] 현재 스레드 1초간 일시정지 , *10 -? HTTP request(응답) 10초
            try {
                Thread.sleep(1000);
            }catch (Exception e ){
                System.out.println(e);
            }
        } // for e

        // 누적값 반환
        return  result;
    } // func e

    // 2. response 응답이 먼저 옴
    // 사용자한테 처리 완료라 응답하고 , 처리
    // 예시] 이메일 전송 완료됐다고 봐도 아직 안오는 경우 있음
    @Async
    public void thread2(){
        int result = 0;
        for( int i = 1 ; i<= 10; i++){
            result += i;
            try {
                Thread.sleep(1000);
            }catch (Exception e ){
                System.out.println(e);
            }
        } // for e
        // return result;
    } // func e

} // class e
