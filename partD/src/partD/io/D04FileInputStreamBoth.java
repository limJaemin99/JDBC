package partD.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;


//바이트 기반 스트림  //
//예시 4 : 입력장치 - 파일 ▶ 프로그램 ▶ 출력장치 - 콘솔  +---+ byte[] 바이트 배열 사용하기 전/후 실행 시간 비교
public class D04FileInputStreamBoth {
	
	//★ 읽어올 파일의 인코딩 형식과 출력하는 프로그램(이클립스)의 인코딩 형식이 일치해야 파일을 정상적으로 읽어올 수 있다 ★
	
    public static void main(String[] args) throws IOException {
        InputStream fin = new FileInputStream("D:/iclass0419/temp/PFRO.log");
        //파일이 해당 경로에 있는지 확인해보세요. 없으면 (.log) 파일중 있는것으로 하세요.
        int rByte;

        System.out.println("[파일을 바이트 단위로 읽어서 System.out 에 출력]\n\n");
        long cnt1=0;
        long start = System.nanoTime();
        while(true) {
            rByte = fin.read();   //읽은 바이트값(여기서는 데이터)  0~255 리턴

            if(rByte == -1) break;	//파일 입력 이므로 파일의 끝(EOF)일때 read() 리턴은 -1
            
          //if((rByte = fin.read()) == -1) break; ◀ 위의 두 문장을 하나로 합쳤을 경우
            cnt1++;		//읽은 횟수 read 카운트 ▶ 배열 사용 안할때는 읽은 바이트 수와 같음
            System.out.write(rByte);     //출력부분      
        }
        long worktime = System.nanoTime()-start;
        fin.close();		//같은 파일을 한번 더 읽기위해 close 필요 ★★★★★★★★★★
        
        System.out.println("[파일을 바이트buf(배열)크기로 읽어서 System.out 에 출력]\n\n");
        fin = new FileInputStream("D:/iclass0419/temp/PFRO.log");	//같은 파일이지만 다시 읽어오기 위해 스트림 객체 생성

        byte[] buf = new byte[10];      //byte[] buf = new byte[fis.available()];   //현재 파일 포인터 위치에서 읽을 수 있는 바이트 수 반환
        int bcnt=0;			//실제로 읽은 바이트 수. 마지막에는 10바이트 보다 적은 데이터를 읽을수 있다.
        long cnt2=0;		//
        start = System.nanoTime();

        while(true) {
            bcnt=fin.read(buf);         //fis.read(buf,0,buf.length)
            System.out.write(buf);	//출력부분
            if(bcnt==-1) break;
            cnt2+=bcnt;
            //data+=new String(buf,0,bcnt);
        }
        
        System.out.println("\n첫 번째 => "+ cnt1 + "바이트 읽음.");
        System.out.println(" *실행시간 : " + worktime +"ns");

        System.out.println("\n두 번째 => "+ cnt2 + "바이트 읽음.");
        System.out.println("**실행시간 : " + (System.nanoTime()-start) +"ns");

        fin.close();

    }
}
