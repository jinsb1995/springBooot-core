package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    /*
        컴포넌트 스캔을 사용하면 @Configuration이 붙은 설정 정보도 자동 등록되기 때문에, AppConfig, TestConfig도 함께 등록되고 실행된다.
        그래서 excludeFilter를 이용해서 '설정정보'는 스캔 대상에서 제외했다.

        @Component 어노테이션이 붙은 클래스를 스캔해서 스프링 빈으로 등록한다.
     */

}