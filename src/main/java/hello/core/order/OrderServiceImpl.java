package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor   // 얘가 final을 기준으로 생성자를 만들어준다.
public class OrderServiceImpl implements OrderService{

    /*
    * 여기서 발생하는 문제
    * 역할과 구현을 충실하게 분리했다.
    * 다형성도 활용하고, 인터페이스와 구현 객체를 분리했다.
    * DIP: 인터페이스에만 의존을 해야하는데(DiscountPolicy)     구현체에도 같이 의존 하고있다.(FixDiscountPolicy, RateDiscountPolicy())
    * OCP: (변경하지 않고 확장) fix에서 Rate로 변경하는 순간 OrderImpl의 소스가 수정되므로 OCP법칙 위반!
    *
    * 이 문제를 해결하려면 누군가가 discountPolicy의 구현 객체를 대신 생성하고 주입해주어야 한다.
    *
    * */

    // final이 있으면 무조건 생성자를 통해 할당되어야한다.
    // final이 있으면 이 값은 무조건 있어야 한다는 뜻이다.  == 필수값
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    // 여기에서 discountPolicy는 Fix가 들어올지, Rate가 들어올지 아무것도 모른다.
    // 다형성에 의해서 그냥 밖에서 주는거 받아먹기만 하면 됨
    // new OrderServiceImpl(memberRepository, discountPolicy);
    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, @Qualifier("mainDiscountPolicy") DiscountPolicy discountPolicy) {
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }


    // 테스트 용도
    public MemberRepository getMemberRepository() {
        // 저 위에 MemoryMemberRepository가 들어갈 예정이다.
        return memberRepository;
    }



}
