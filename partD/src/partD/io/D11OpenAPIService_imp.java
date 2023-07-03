package partD.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;


//여기서는 TCP 프로토콜이 아닌 HTTP 프로토콜을 사용했다.
public class D11OpenAPIService_imp {
	
	public static void main(String[] args) {
		
		String authkey="AluENEwBXPhUPGfCO8dy3cfaqFJD8yb9";         //open api 사용을 위한 인증키-사이트에서 신청
        String searchdate ="20230630";                        //검색날짜
//        int page=1;
        String file="D:/iclass0419/temp/apiJson.txt";                     //전송받은 데이터를 저장할 파일
        
        //http 프로토콜을 이용하여 서버에 연결하기 위한 자바 클래스 입니다.
        HttpURLConnection connection=null;
        
        //open api 서버 주소	//StringBuilder는 문자열 추가할 때 효율성/편의성을 위해서 사용한다.
        StringBuilder url = new StringBuilder("https://www.koreaexim.go.kr/site/program/financial/exchangeJSON");
        try {
            url.append("?authkey=").append(authkey)            //파라미터 추가. 인증키
                    .append("&searchdate=").append(searchdate)      //        검색날짜
                    .append("&data=AP01");                     //       형식
            
            //최종 url 문자열로 URL(서버에게 보내는 요청 내용) 객체 생성
            URL fullURL = new URL(url.toString());               
            //URL 객체 주소로 연결 요청
            connection = (HttpsURLConnection) fullURL.openConnection(); 
            // 요청 방식 (기본 : GET)
            connection.setRequestMethod("GET");                  
        }catch (IOException e) {
            e.printStackTrace();
        }
        
        
        String line = null;
        String jsonStr = null;
        
        try (	//HttpURLConnection 객체를 통해 문자기반 입력 스트림 생성하기
        		InputStreamReader ir = new InputStreamReader(connection.getInputStream());
        		//문자기반 파일 출력 스트림
            	PrintWriter pw = new PrintWriter(new File(file));	//자동 flush 기능이 있는 메소드 사용
        															//flush : 버퍼에 남아있는 데이터를 전부 내보내는것
            	//문자기반 입출력 보조 스트림 (버퍼)
            	BufferedWriter bw = new BufferedWriter(pw);	//◀ pw가 flush기능이 있으므로 아래의 bw.flush()생략 가능
            	BufferedReader br = new BufferedReader(ir);
        ){
        	while((line = br.readLine()) != null) {
        		//줄 단위로 읽기. 여기서는 데이터가 1개 라인에 모두 저장하여 사실은 반복문 필요없음.
        		jsonStr = line;
        		
        	}
        	System.out.println("================= HttpsURLConnection 으로 읽어온 응답 문자열 =================");
        	System.out.println(jsonStr);//콘솔에 출력
        	bw.write(jsonStr);			//파일에 출력
        	
        	//버퍼를 사용하는 출력일때는 필요함.
        	bw.flush();	//◀ pw가 flush기능이 있으므로 bw.flush()생략 가능
        	
        	
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        //문자열을 자바 리스트로 변환하는 메소드 - 개발자 정의
        System.out.println(jsonToList(jsonStr).size());
        
		
	}//main end
	
	//json 문자열을 자바 객체로 변경함
	private static List<CurrencyRate> jsonToList(String jsonStr) {
		Gson gson = new Gson();
		
		//TypeToken<> 의 생성자는 protected
		//▶ List 등 컬렉션으로 변경할때 사용한다.
		//▶ TypeToken 이 [{} , {} , ... , {}] 형식의 문자열을 {}/,/: 로 분리해서 변수명에 해당하는 알맞은 데이터를 가져온다. 
		//▶ 라이브러리를 사용하지 않을경우 3중 반복문을 사용하여 split()을 통해 같은 결과를 만들 수 있다.
		List<CurrencyRate> list = gson.fromJson(jsonStr, new TypeToken<List<CurrencyRate>>(){}.getType());
		
		System.out.println("================= jsonToList 출력 =================");
		for (CurrencyRate temp : list) {
			System.out.println(temp);
		}
		
		System.out.println("==================================================");
		
		return list;
	}
	
//참고 - 공공 데이터 open API 사이트 : https://www.data.go.kr/	
	
//현재환율 API	      //https://www.koreaexim.go.kr/ir/HPHKIR020M01?apino=2&viewtype=O#tab1
	//https://www.koreaexim.go.kr/ir/HPHKIR020M01?apino=2&viewtype=C&searchselect=&searchword=
	//AluENEwBXPhUPGfCO8dy3cfaqFJD8yb9
//gson.jar 다운	      //project structure 에서 gson 라이브러리 추가 .
	//downloads : https://repo1.maven.org/maven2/com/google/code/gson/gson/
	//doc : https://www.javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/module-summary.html

}//main class end
