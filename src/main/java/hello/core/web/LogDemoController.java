package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
//@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;  // 얘는 지금 가짜이다.

    @Autowired
    public LogDemoController(LogDemoService logDemoService, MyLogger myLogger) {
        this.logDemoService = logDemoService;
        this.myLogger = myLogger;
    }

    @RequestMapping("log-demo")
    @ResponseBody  // 뷰를 반환하지 않고, 문자열(text)를 반환하고 싶다면 ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {   // httpServletRequest: 자바에서 제공하는 표준 서블릿 규약. 고객 요청 정보를 받을 수 있다.
        // httpServletRequest의 생존 범위는 고객의 요청이 들어왔을 떄 부터 나갈 때 까지인데
        // 지금은 고객의 요청이 들어온게 없어서 오류가 난다.


        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLogger.getClass());  // 스프링이 조작한 애가 빈으로 등록된다

        myLogger.setRequestURL(requestURL);   // 여기서 진짜를 찾아서 동작한다.

        myLogger.log("controller test");
        logDemoService.logic("testId");

        return "OK";
    }


}
