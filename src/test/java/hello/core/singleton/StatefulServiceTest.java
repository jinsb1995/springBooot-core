package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {


    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        // ThreadA: A사용자 10000원 주문
        int userAPrice = statefulService1.order("userA", 10000);
        // ThreadB: B사용자 20000원 주문
        int userBPrice = statefulService2.order("userB", 20000);

        // ThreadA: 사용자A 주문 금액 조회
        System.out.println("price = " + userAPrice);


//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }


//    @Test
//    void statefulServiceSingleton() {
//        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
//
//        StatefulService statefulService1 = ac.getBean(StatefulService.class);
//        StatefulService statefulService2 = ac.getBean(StatefulService.class);
//
//        // ThreadA: A사용자 10000원 주문
//        statefulService1.order("userA", 10000);
//        // ThreadB: B사용자 20000원 주문
//        statefulService2.order("userB", 20000);
//
//        // ThreadA: 사용자A 주문 금액 조회
//        int price = statefulService1.getPrice();
//        // 얼마 나와야함? 10000원 예상했는데 20000원이 나옴. 왜냐하면 중간에 B가 20000원으로 바꿔버렸기 때문에..
//        System.out.println("price = " + price);
//
//        /*
//            싱글톤의 공유 필드는 항상 조심해야 한다.
//            스프링 빈은 항상 무상태(stateless) 로 설계하자!
//         */
//
//        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
//    }
//

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }


}
