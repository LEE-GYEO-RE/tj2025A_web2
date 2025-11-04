package example2.day02;

import jakarta.persistence.EntityListeners;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@SpringBootApplication
@EnableWebSecurity // 시큐리티 설정 어노테이션
@EnableJpaAuditing
// @EnableJpaAuditing 필수 주입
// JPA가 데이터베이스를 auditing(모니터링) 하여 변화되면 수정날짜/시간 주입
public class AppStart {
    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args);
    }
    @Bean // 특정 경로 전체를 Spring Security 검사에서 제외
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/api/goods/**");
    }
}