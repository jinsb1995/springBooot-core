package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 설정정보 담당
// Configuration이 붙은것도 빈으로 등록이 된다.

/**
 *  Configuration이 없어도 CGLIB가 동작하긴 하는데
 *  Configuration없이 @Bean 만 선언한다면  인스턴스를 새로 생성한 뒤 넣어주게 되니까 싱글톤이 깨진다.
 *
 *  **  정리 **
 * @Bean 만 사용해도 스프링 빈으로 등록이 되지만, 싱글톤을 보장하지 않는다.
 *     memberRepository() 처럼 의존관계 주입이 필요해서 메서드를 직접 호출할 때 싱글톤을 보장하지 않는다.
 *
 * configuration이 없으면 CGLIB가 동작하지 않는다.
 */
@Configuration
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()

    /*
        @Bean을 입력하면 스프링 컨테이너에 등록된다.
        스프링 빈은  @Bean이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다.
     */

    @Bean
    public MemberService memberService() {
        // 생성자 주입
        System.out.println("call ------- AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call ------- AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("call ------- AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }


/**
    ! ! ! ! !  CGLIB 예상 코드 ! ! ! ! !

    @Bean
    public MemberRepository memberRepository() {

        if (memoryMemberRepository 가 이미 스프링 컨테이너에 등록되어 있으면?) {
            return 스프링 컨테이너에서 찾아서 반환;
        } else {
            // 스프링 컨테이너에 없으면

            기존 로직을 호출해서 MemoryMemberRepository를 생성하고 스프링 컨테이너에 등록
            System.out.println("call ------- AppConfig.memberRepository");
            return new MemoryMemberRepository();
        }

    }

    @Bean 이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고,
    스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.














*/










}





















