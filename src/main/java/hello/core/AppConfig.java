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



}



//    // 이제 애플리케이션에 대한 환경 구성에 대한건 여기서 설정해준다.
//
//    public MemberService memberService() {
//        return new MemberServiceImpl(memberRepository());
//    }
//
//    public OrderService orderService() {
////        이걸 하려면 Impl에 미리 생성자를 만들어 놓아야한다. 기억해라!!!!!!!
//        return new OrderServiceImpl(memberRepository(), discountPolicy());
//    }
//
//    // 역할이 드러나게 리팩터링
//    // 중복이 제거
//    // 역할과 구현 클래스가 한눈에 들어온다.
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//    }
//    public DiscountPolicy discountPolicy() {
////        return new FixDiscountPolicy();
//        return new RateDiscountPolicy();
//    }


//    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());
//    }
//
//    public OrderService orderService() {
////        이걸 하려면 Impl에 미리 생성자를 만들어 놓아야한다. 기억해라!!!!!!!
//        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
//    }
