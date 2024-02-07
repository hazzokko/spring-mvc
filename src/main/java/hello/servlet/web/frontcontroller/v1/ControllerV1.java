package hello.servlet.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ControllerV1 {

    // 서블릿과 모양이 동일한 인터페이스를 생성
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
