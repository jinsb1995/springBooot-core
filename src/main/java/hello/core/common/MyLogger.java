package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//@Scope(value = "request")
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)   // proxy(가짜를 만든다)
public class MyLogger {

    private String uuid;
    private String requestURL;

    // 빈이 생성되는 시점을 알 수가 없어서 외부의 Setter로 입력을 받아준다.
    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "] [" + requestURL + "] : " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "]  request scope bean create: " + this );
    }

    @PreDestroy
    public void destroy() {
        System.out.println("[" + uuid + "]  request scope bean close: " + this );
        System.out.println("");
    }



}
