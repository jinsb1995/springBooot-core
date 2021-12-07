package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);   // 구현체로 꺼내야 테스트코드를 볼 수 있기 떄문에 구현체로 꺼낸다.

        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = memberService.getMemberRepository();

        // 세 놈 다 같은 주소값을 가지고있다.
        System.out.println("memberService ->   memberRepository1 = " + memberRepository1);
        System.out.println("orderService  ->   memberRepository2 = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);


//        AppConfig bean = ac.getBean(AppConfig.class);
//        System.out.println("bean = " + bean);
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName + " bean = " + bean);
        }


        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);

    }


    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        // 순수한 클래스라면 class hello.core.AppConfig가 출력되어야 한다.
        // 예상과 다르게 class hello.core.AppConfig$$EnhancerBySpringCGLIB$$e5aa413 이런게 나왔다.
        // 이건 내가 만든 클래스가 아니라, 스프링이 CGLIB라는 바이트코드 조작 라이브러리를 사용해서
        // AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것이다.

        // 결론: CGLIB이 내가 만든 클래스를 상속받아서 새로운 클래스를 만든 뒤 그 클래스를 빈으로 등록한 것.
        // 그 임의의 다른 클래스가 싱글톤이 되도록 보장해준다.

        System.out.println("bean.getClass() = " + bean.getClass());

    }


}
