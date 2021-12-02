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

        /*
            스프링은 모든게 ApplicationContext에서 시작된다.
            이게 스프링 컨테이너다.
            이게 모든 어노테이션을 관리해준다.
            AnnotationCon... 생성자에 AppConfig class를 넣어주면 @Bean이 붙은 생성된 객체를 다 스프링 컨테이너에 집어넣어서 관리해준다.

            스프링 컨테이너는 @Configuration이 붙은 클래스를 설정정보로 사요한다.
            여기에서 @Bean이 붙은 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다.

            Scope가 할당이 안되어있으면 싱글톤이라는 뜻임
         */
        /*
            ApplicationContext가 제공하는 부가기능
            - 메시지소스를 활용한 국제화 기능
            - 환경변수 : 로컬, 개발, 운영 등을 구분해서 처리  |  staging 환경 - 운영환경과 비슷한 환경이다.
            - 애플리케이션 이벤트 :  이벤트를 발행하고 ㄱ독하는 모델을 편리하게 지원
            - 편리한 리소스 조회
         */
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
//                                                       메서드 이름           타입
        MemberService memberService = ac.getBean("memberService", MemberService.class);

        Member m = new Member(1L, "memberA", Grade.VIP);

        memberService.join(m);

        Member findMember = memberService.findMember(1L);
        System.out.println("new Member : " + m.getName());
        System.out.println("findMember : " + findMember.getName());
    }
}
