package hello.core.singleton;

/*
    정리
        1. static 영역에 객체 instance를 미리 하나 생성해서 올려둔다.
        2. 이 객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회할 수 있다.
           이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
        3. 딱 1개의 객체 인스턴스만 존재햐야 하므로, 생성자를 private으로 막아서
           혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는것을 막는다.

        싱글톤 패턴을 적용하면 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라,
        이미 만들어진 객체를 공유해서 효율적으로 사용할 수 있다.
        하지만 싱글톤 패턴은 다음과 같은 수 많은 문제점들을 가지고 있다.



    싱글톤 패턴 문제점
        - 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
        - 의존관계상 클라이언트가 구체 클래스에 의존한다. -> DIP를 위반한다.
              예) 구체클래스.getinstance() 이런식으로 가져와야 하기 때문에 불편함
        - 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
        - 테스트 어려움.
        - 내부 속성을 변경하거나 초기화 하기 어렵다.
        - private생성자로 자식 클래스를 만들기 어렵다.
        - 결론적으로 유연성이 떨어진다.
        - 안티패턴으로 불리기도 한다.
 */
public class SingletonService {

    // 자바가 뜰 때 바로 singletonService 의 static 영역에 있는 걸 내부적으로 실행해서 객체를 생성해서 instance에 참조를 넣어놓는다.

    // 자기 자신을 내부에 private final로 선언한다.
    // static 으로 만들어두면 클래스 레벨로 올라가기 때문에 딱 하나만 존재한다.
    private static final SingletonService instance = new SingletonService();

    // 조회할 땐 이걸 쓰면 된다
    public static SingletonService getInstance() {
        return instance;
    }

    // private 생성자를 생성해서 외부에서 객체를 생성하지 못하게 만든다.
    private SingletonService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }



}
