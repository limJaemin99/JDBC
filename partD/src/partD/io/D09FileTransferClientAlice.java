package partD.io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
//ip확인 명령어 - ipconfig

//서버와 클라이언트 사이에서 소켓을 만들어 TCP 프로토콜 통신 (D9,D10)

//바이트 기반 스트림  //
//예시 9: 입력장치- 네트워크 소켓 ▶ 프로그램 ▶  출력장치-네트워크 소켓  +---+ 네트워크를 이용해서 클라이언트가 서버에게 파일을 보냅니다.(서버를 먼저 실행하기)
//소켓은 네트워크 상의 컴퓨터가 데이터를 보내거나 받기 위해 사용하는 것이 네크워크 연결 접속점 이다.TCP와 UDP 프로토콜 즉 OSI 7 계층의 전송계층에서 동작한다.
public class D09FileTransferClientAlice {
	//클라이언트 동작하는 main
	public static void main(String[] args) {
		
		System.out.println("[연결 요청]");
		
		try(		
				Socket socket=new Socket();			//클라이언트 소켓
				
				) 	{
			socket.connect(new InetSocketAddress("192.168.0.54", 5050));	//클라이언트가 연결 요청
			//소켓연결을 요청하는 서버의 IP주소, 서버와 약속한 애플리케이션 포트번호
			System.out.println("[연결 성공]");					//연결 오류일 때는 예외 발생
			
			InputStream is = socket.getInputStream();		//소켓으로 만드는 입력 스트림
			OutputStream os = socket.getOutputStream();		//소켓으로 만드는 출력 스트림
			
			//인코딩된문자열 파일명, Long 타입 파일크기 데이터를 네트워크로 출력할 소켓의 보조스트림 
			DataOutputStream dos = new DataOutputStream(os);	
			//서버가 보낸 데이터(인코딩된 문자열)를 받기 위해 네트워크에서 입력할 소켓의 보조스트림 
			DataInputStream dis = new DataInputStream(is);	
			
			String filename="C:/IOExam/dog.png";
			File f= new File(filename);
			long fsize =f.length();		//파일의 크기를 구함
			
			System.out.println("전송할 파일은 " + filename + "입니다. 파일크기 : " + fsize + " 바이트.");
		
			//출력 1) writeUTF 메소드는 UTF 로 인코딩된 문자열을 출력하는 메소드(서버 입력 1번과 짝궁)
			dos.writeUTF(filename); 		//출력

			FileInputStream fis = new FileInputStream(filename);	//파일 장치에 대한 입력 스트림
			BufferedInputStream bis = new BufferedInputStream(fis);	//파일 장치에 대한 보조 스트림
			
			int b;int cnt=0;
			
			//출력 2) writeLong 메소드는 출력 데이터를 실수 형식으로 보냅니다.(서버 입력 2번과 짝궁)
			dos.writeLong(fsize);			//출력
			
			//입력 별도) bis 는 이미지 파일에 대한 버퍼입력스트림으로 데이터 가져오기(클라이언트에서만 별도로 실행)
			while((b=bis.read())!=-1) {		//파일 장치에서 입력
				
				//출력 3) dos 는 Socket 에 대한 출력 스트림. writeXXX 에서 XXX없으면 일반적인 바이트기반 출력(서버 입력 3번과 짝궁)
				dos.write(b);				//소켓으로 출력. 	//bos 사용하면 오류. 출력은 동일한 소켓리소스에 대해 2개의 보조출력스트림을 만들때 오류.
				cnt++;
			}
			if(cnt==fsize) 
				System.out.println("클라이언트 파일 보내기 성공!");

			System.out.println("/// 서버가 보낸 메시지 ///");
			
			//입력 1) 
			System.out.println(dis.readUTF());

			dis.close();
			dos.close();
			bis.close();
			fis.close();
		}catch(IOException e) {
			e.printStackTrace();
//			System.out.println("예외 발생 : " + e.getMessage()); 
			
		}
	}

}