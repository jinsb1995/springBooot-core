package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
//        ac.getBean();

    }

    static class TestBean {

        @Autowired(required = false)
        public void setNoBean1(Member member1) {
            // 자동 주입할 대상이 없으면 메서드자체가 호출이 안된다.
            System.out.println("member1 = " + member1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member2) {
            // 없는걸 집어넣은거다
            // 호출은 되는데 null이 들어온다.
            System.out.println("member2 = " + member2);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member3) {
            // 없는걸 집어넣은거다
            // 들어오는 빈이 없으면 Optional.empty가 들어온다
            System.out.println("member3 = " + member3);
        }

    }

}
