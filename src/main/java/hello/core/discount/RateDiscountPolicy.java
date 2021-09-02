package hello.core.discount;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Qualifier("mainDiscountPolicy")
@MainDiscountPolicy  // @Qualifier("mainDiscountPolicy") 이렇게 하면 타입 체크가 힘들어서 애노테이션을 만들어서 사용하는게 더 좋다.
//@Primary // discountPolicy의 구현체들이 모두 빈 등록이 되어있을때 이게 있으면 우선권을 가진다.
public class RateDiscountPolicy implements DiscountPolicy {

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {

        if(member.getGrade() == Grade.VIP) {
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
