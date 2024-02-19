package hello.servlet;

import hello.servlet.web.springmvc.v1.SpringMemberFormControllerV1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@ServletComponentScan // 스프링이 자동으로 현재 패키지와 하위 패키지를 순회하며 서블릿을 등록
@SpringBootApplication
public class ServletApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServletApplication.class, args);
    }

//    3.0 버전부터는 RequestMapping으로 컨트롤러를 인식하지 않으므로 주석처리
//    @Bean
//    SpringMemberFormControllerV1 springMemberFormControllerV1() {
//        return new SpringMemberFormControllerV1();
//    }
}
