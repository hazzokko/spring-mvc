package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {

    @Override // 서블릿이 호출되면 service() 메서드가 호출된다.
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // 서블릿은 쿼리파라미터를 편하게 읽는 기능을 제공한다.
        String username = request.getParameter("username"); // 쿼리파라미터 조회
        System.out.println("username = " + username);

        // 응답 생성, 값을 넣으면 웹 브라우저의 HTTP 응답 메시지에 데이터가 담겨서 나간다.
        response.setContentType("text/plain"); // header 정보
        response.setCharacterEncoding("utf-8"); // header 정보
        response.getWriter().write("hello " + username); // HTTP message body에 데이터가 담긴다.
    }
}
