package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {

    private Map<String, ControllerV1> controllerMap = new HashMap<>();

    public FrontControllerServletV1() {
        // 서블릿 생성 시 매핑정보를 미리 저장
        controllerMap.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
        controllerMap.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
        controllerMap.put("/front-controller/v1/members", new MemberListControllerV1());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service"); // 실제 개발할 때는 로거로 찍는 것이 좋음

        // 예) /front-controller/v1/members
        String requestURI = request.getRequestURI();

        // ControllerV1 controller = MemberListControllerV1(); -> 이미 생성된 객체이므로 new는 필요없고 원래는 객체 인스턴스의 주소가 들어온다.
        ControllerV1 controller = controllerMap.get(requestURI); // 키값에 해당하는 객체 인스턴스 반환, 인터페이스로 꺼내므로 일관성있는 코드 사용 가능
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 페이지가 없거나 못찾을 때
            return; // 404일 경우 바로 리턴하면 된다.
        }

        controller.process(request, response);
    }
}
