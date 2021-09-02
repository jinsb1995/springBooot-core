package hello.core.lifecycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url: " + url);
//        connect();
//        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    // 연결이 된 상태에서 call을 부를수 있는 것
    // 연결한 서버에 메세지를 보낼 수 있다.
    public void call(String message) {
        System.out.println("call : " + url + ", message: " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close : " + url);
    }

    // 서비스 시작시
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메세지");
    }

    // 서비스 종료시
    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }







    // interface -  implements InitializingBean, DisposableBean
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("afterPropertiesSet afterPropertiesSet afterPropertiesSet afterPropertiesSet afterPropertiesSet");
//        // 외존 관계 주입이 끝나면 호출해 주겠다.
//        connect();
//        call("초기화 연결 메세지");
//    }
//
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("destroy destroy destroy destroy destroy destroy ");
//        disconnect();
//    }
}
