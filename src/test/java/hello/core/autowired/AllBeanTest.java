package hello.core.autowired;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext ac =  new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);

        // 할인 서비스
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L, "userA", Grade.VIP);
        int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");

        assertThat(discountService).isInstanceOf(DiscountService.class);
        assertThat(discountPrice).isEqualTo(1000);

        int rateDiscountPolicy = discountService.discount(member, 20000, "rateDiscountPolicy");
        assertThat(rateDiscountPolicy).isEqualTo(2000);
    }


    // DiscountService는 Map으로 모든 discountPolicy를 주입받는다.
    // 이때 fixDiscountPolicy, rateDiscountPolicy가 주입된다.
    static class DiscountService {
        // 여기서 DiscountPolicy는 interface라서 "타입"이라고 보면 된다.
        // map의 키에 스프링 빈의 이름을 넣어주고,
        // 그 값으로 DiscountPolicy타입으로 조회한 모든 스프링 빈을 담아준다.
        private final Map<String, DiscountPolicy> policyMap;
        // List<DiscountPolicy>: DiscountPolicy 타입으로 조회한 모든 ㅅ프링 빈을 담아준다.
        // 만약 해당하는 타입의 스프링 빈이 없으면, 빈 컬렉션이나 Map을 주입한다.
        private final List<DiscountPolicy> policies;

        @Autowired
        public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
            this.policyMap = policyMap;
            this.policies = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policyList = " + policies);
        }

        // 여기에는 discountCode로 fixDiscountPolicy나 rateDiscountPolicy가 넘어오면서
        // map에서 매칭되는 빈을 찾아서 실행한다.
        int discount(Member member, int price, String discountCode) {
            // 할인 코드를 빈 이름이랑 매칭 시킨다.
            DiscountPolicy discountPolicy = policyMap.get(discountCode);
            return discountPolicy.discount(member, price);
        }



    }

}
