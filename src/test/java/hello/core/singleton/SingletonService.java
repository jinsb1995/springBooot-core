package hello.core.singleton;

public class SingletonService {

    // 자기 자신을 내부에 private static 으로 선언한다.
    // 이렇게 하면 클래스 레벨로 올라가기 때문에 딱 하나만 존재하게 된다
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance() {
        return instance;  // 조회할 때 얘를 쓴다.
    }

    // 외부에서 new로 인스턴스를 새로 생성하지 못하게 하기 위해 private으로 선언한다.
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }









//    private static final SingletonService instance = new SingletonService();
//
//    // 조회할 때 이걸 쓰면 된다.
//    public static SingletonService getInstance() {
//        // 이 메서드를 호출하면 항상 같은 instance를 반환한다.
//        // 자바가 뜰 때 이미 만들어진 이녀석을 이용해서 받아온다.
//        return instance;
//    }
//
//    private SingletonService() {
//
//    }
//
//
//    public void login() {
//        System.out.println(" 싱글톤 객체 로직 호출 " );
//    }




//    // 1. static 영역에 객체를 딱 1개만 생성해준다.
//    public static final SingletonService instance = new SingletonService();
//
//    // 2. public으로 열어서 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 죄하도록 허용한다.
//    public static SingletonService getInstance() {
//        return instance;
//    }
//
//    // 3. private으로 외부에서 호출하는것을 막는다.
//    private SingletonService() {
//
//    }
//
//    public void logic() {
//        System.out.println("싱글톤 객체 로직 호출");
//    }


}
