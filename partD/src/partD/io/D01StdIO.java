package partD.io;

import java.io.IOException;

//바이트 기반 스트림	//표준 입력 - System.in ┃ 표준 출력 - System.out _ 이 2개는 콘솔이라고 부르며 기본 입출력장치입니다.
//예시 1 : 입력장치 - 콘솔 ▶ 프로그램 ▶ 출력장치 - 콘솔
public class D01StdIO {
	public static void main(String[] args) {

		System.out.println("↓ 화면에 출력할 내용을 입력하세요 ↓");
		int count = 0;
		int b = 0;
		try {
//			while((b = System.in.read() != 1-)) {	//이렇게 조건식을 쓰면 16,17번 라인을 실행하게 됩니다.
		while(true) {
			
			//read()로 읽어온 데이터는 0~255 의 값. 저장만 int 타입 변수로 합니다.
			
			b = System.in.read();		// ① read()로 입력받은것을 변수 b에 저장합니다.
			if(b == -1) break;			// * b가 -1이면 입력 종료.(※ EOF : End Of File)
			count++;
			System.out.write(b);		// ② b를 출력합니다.
//				System.out.print((char)b);		//print는 1바이트씩만 처리한다. (따라서 한글은 깨진다 : ã´)
													//멀티바이트 문자(한글과 같은 다국어 문자) 바이트 단위 입력 인코딩 못함.
		}
//			}
		} catch (IOException e) {
			System.err.println("입출력 오류 : "+e.getMessage());
		}
		System.out.println("=== "+count+" 바이트 전달 ===");
		
	}//main end
}//main class end


/*
	read()는 입력 메소드, write()는 출력 메소드 입니다.
	실행할때 입력한것은 버퍼라는 메모리에 순서대로 쌓이고, 엔터를 치면 버퍼에서 나옵니다.(출력됩니다)
	
	한글 3바이트 ┃ 영문/기호 1바이트
	엔터 2바이트 ┃ 이클립스 : 키보드 입력종료(Ctrl+z) , 인텔리제이 키보드 입력 종료(Ctrl+d)
	
	첫번째 실행에서 Ctrl+z 가 잘 동작하나, 다시 실행하면 Ctrl+z 동작 안하는것은 이클립스(해당버전) 오류입니다.
	코드를 살짝 수정해서 다시 실행시키세요.
*/