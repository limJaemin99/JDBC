package koreait.jdbc.day06;
/*
 	★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ 중요
	싱글톤 패턴 : 객체를 만들 때, 오직 1개만 만들어서 사용하는 패턴
		        웹(멀티쓰레드)에서는 메소드만 있는 DAO 와 같은 클래스를 싱글톤으로 작성합니다.
				static 은 정적인 영역. DAO 를 정적인 영역(메모리)에 만들지는 않습니다.
 	★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★ 중요
*/
public class SingletonClass {
	//1. 미리 객체를 만들어서 전역변수(필드)로 선언해둡니다. private
		//SingletonClass 객체는 오직 한번만 만든다.
	private static SingletonClass single = new SingletonClass();
	
	
	
	//2. 생성자는 private 입니다.
		//▶ 외부의 다른 클래스는 new SingletonClass() 실행 못함
	private SingletonClass() {}
	
	//3. 외부의 다른 클래스에서 객체를 요청할 때 리턴해주는 메소드. public
	public static SingletonClass getInstance() {
		return single;
	}
	
	
}//class end
