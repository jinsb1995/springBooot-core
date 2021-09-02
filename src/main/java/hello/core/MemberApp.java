package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

//      얘가 스프링 container(Bean을 관리)             얘는 Annotation이 붙은 애들을 가져옴
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//                                        메서드 이름       타입
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member m = new Member(1L, "memberA", Grade.VIP);

        memberService.join(m);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member : " + m.getName());
        System.out.println("findMember : " + findMember.getName());
    }
}
