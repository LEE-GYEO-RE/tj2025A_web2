package example.day01._2스프링스케줄링;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service // 빈 등록
public class ScheduleService {

    // 1. 3초마다 자동으로 실행하는 서비스 메소드
    @Scheduled( fixedRate = 3000) // 3초
    public void task1(){
        System.out.println("[3초] ScheduleService.task1");
    } // func e

    // 2. 5초마다 자동으로 실행하는 서비스 메소드
    final int time = 5000; // 계산식 또는 변수는 쓰려면 상수 처리 (final 사용)
    @Scheduled( fixedRate = time ) // 5초
    public void task2(){
        System.out.println("[5초] ScheduleService.task2");
    } // func e

    // * (서버) 시스템 날짜/시간 기준으로 자동실행 스케줄링(백그라운드/비동기/멀티스레드)
    // 3. 현재 시스템의 매 시간 0시 0분 5초 될때마다 자동실행
    // 자바 킨 컴퓨터 서버 시간 기준
    // @Scheduled( cron = 초 분 시 일 월 요일) : 특정한 시기에 자동실행가능
    @Scheduled( cron = "*/5 * * * * *" ) // 5초마다 실행 0/5 : 매시간 5초가 될때마다
    public void task3(){
        System.out.println("[cron 5초] ScheduleService.task3");
    } // func e

    @Scheduled(cron = " 0 */1 * * * *") // 1분마다 자동실행
    public void task4(){
        System.out.println("[cron 1분]ScheduleService.task4");
    } // func e


} // class e
















