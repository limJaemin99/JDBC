package partD.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


//바이트 기반 스트림	//
//예시 5 : 입력장치 - 파일 ▶ 프로그램 ▶ 출력장치 - 파일
public class D05FileToFile {
	
	//byte 배열을 프로그램 버퍼로 사용 할 것인가? - 선택해서 하세요.
	
	public static void main(String[] args) {
		
		// 1. 배열 사용 방법
		long worktime = 0;
		int bcnt = 0;
		long cnt = 0;
		
		// 2. 바이트 단위로 하는 방법
//		int count=0; 	//바이트 단위로 입/출력할 경우 1바이트씩 불러오기 때문에 count++을 사용할 수 있다.
		
		try(
		//파일 입력 스트림(입력 장치)		
		InputStream fin = new FileInputStream("D:/iclass0419/temp/cafe.jpg");		//복사하고싶은 파일
		//파일 출력 스트림(출력 장치)		
		OutputStream fout = new FileOutputStream("D:/iclass0419/temp/cafe_copy.jpg");	//복사해서 생성될 파일
		) {
			byte[] buf = new byte[20];
			long start = System.nanoTime();
			
			while(true) {
				
				// 1. 배열 사용 방법
				bcnt = fin.read(buf);
				fout.write(buf);
				if(bcnt == -1) break;
				cnt += bcnt;	//만약 배열로 묶지 않고 바이트 단위로 읽는 방법을 선택했다면 count++로 사용(1바이트씩 불러오기 때문에)
				worktime = System.nanoTime()-start;
				// 2. 바이트 단위로 하는 방법
//				int b;
//				while((b = fin.read()) != 1) {
//					count++;
//					fout.write(b);
//				}
			}
		}catch (IOException e) {
			System.err.println("출력 오류 : "+e.getMessage());
		}
		
		System.out.println("=== "+cnt+" 바이트 읽음 ===");
		System.out.println("(실행시간 : "+worktime+"ns)");
	}//main end
}//main class end
