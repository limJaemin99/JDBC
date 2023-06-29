package partD.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//FileOutputStream 클래스는 출력스트림 OutputStream 의 자식 클래스 입니다.
//바이트 기반 스트림	//
//예시 2 : 입력장치 - 콘솔 ▶ 프로그램 ▶ 출력장치 - 파일
public class D02FileOutputStream {
	public static void main(String[] args) {
		int count = 0;
		int b;
		System.out.println("↓ 파일에 저장할 내용을 입력하세요 ↓");
		OutputStream fo = null;
		try {
			fo = new FileOutputStream("D:/iclass0419/temp/d02.txt");		//출력장치 파일 시스템은 경로와 파일명으로 객체 생성
			while((b = System.in.read()) != -1) {							//※ 폴더는 미리 만들어놔야함(txt파일은 자동 생성)
				count++;
				fo.write(b);
			}
		} catch (IOException e) {
			System.err.println("입출력 오류 : "+e.getMessage());
		} 
		finally {
			if(fo != null) {
				try {
					fo.close();			//파일 출력 장치에 대한 close 꼭 필요.
				}catch (IOException e) {	//▶ 한개의 파일만 입력할때는 close가 없어도 잘 동작하지만,
												//파일 입력 등 다른 실행과 동시에 일어날때는 실행에 오류가 생길 수 있다.
				}//catch
			}//if
		}//finally 
		System.out.println("=== "+count+" 바이트 파일에 저장 ===");
		
	}//main end
}//main class end
