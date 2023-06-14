package partD.a0614;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;

/*
	자바에서 날짜 형식을 지원하는 클래스
	1) java.util 패키지의 Date 와 Calendar 클래스
		▶ Jdk 1.1 버전부터 사용했으나, 지금은 소멸된 기능들이 많음
		
	2) java.sql 패키지의 Date 와 Timestamp 클래스
		▶ Jdk 1.1 버전부터 데이터베이스와 연동할 때 사용
		
	3) java.time 패키지의 LocalDate , LocalTime , LocalDateTime 클래스
		▶ Jdk 1.8 (자바 8) 버전부터 사용, 다양한 기능의 메소드가 제공된다.
*/
public class JavaDateTypeTest {
	public static final String RESET = "\u001B[0m";
	public static final String FONT_RED = "\u001B[31m";
	public static void main(String[] args) {
		
		//10진수값 출력 포맷을 지정합니다. ━━━ ★★★★★
		DecimalFormat df = new DecimalFormat("###,###,###,###");
		
		
		System.out.println("1. 현재 시간을 '1970년 1월 1일 0시' 기준으로 구하기. 단위 : ms (1/1000 초)");
		long now = System.currentTimeMillis();
		System.out.println("현재 시간 = "+df.format(now)+" ms");
		System.out.println("━━━"+FONT_RED+" 현재 시간을 ms 단위로 구하는 방식은 모든 프로그래밍 언어에서 사용합니다 "+RESET+"━━━");
		
		
		
		System.out.println("\n2. 약 53년을 ms로 직접 계산해보기.");
		long oneday = 24 * 60 * 60 * 1000;	// 하루 = 24시간, 1시간 = 60분, 1분 = 60초, 1초 = 1000ms
		long oneyear = 365 * oneday;
		System.out.println("1일 24시간 = "+df.format(oneday)+" ms");
		System.out.println("1년 365일 = "+df.format(oneyear)+" ms");
		
		System.out.println("53년 = "+df.format(53 * oneyear)+" ms");
		System.out.println("54년 = "+df.format(54 * oneyear)+" ms");
		
		System.out.println("53년 = "+df.format(53*oneyear/1000/60/60/24)+" 일");
		System.out.println("54년 = "+df.format(54*oneyear/1000/60/60/24)+" 일");
		
		
		
		System.out.println("\n3. 날짜 클래스로 객체 생성하기.");
		System.out.println("★ java."+FONT_RED+"util"+RESET+".Date 클래스 : "+ new Date());
		System.out.println("★ java."+FONT_RED+"util"+RESET+".Date 클래스 getTime() 메소드 : "+ df.format(new Date().getTime()));
		
		System.out.println("-".repeat(60));

		// java.sql.Date 는 기본 생성자가 없다 ▶ long 타입 인자가 필요하다.
//		System.out.println("java.sql.Date 클래스 : "+ new java.sql.Date());	//오류
		System.out.println("★ java."+FONT_RED+"sql"+RESET+".Date 클래스 : "+ new java.sql.Date(54*oneyear));
		System.out.println("★ java."+FONT_RED+"sql"+RESET+".Date 클래스 : "+ new java.sql.Date(new Date().getTime()));
		
		System.out.println("-".repeat(60));
		System.out.println("java.util.Calendar 클래스 : "+Calendar.getInstance());
		System.out.println("▶ private 생성자이고, 객체를 만들어 전달해주는 getInstance() 메소드");
		
		/*
		[실행 결과]
		java.util.GregorianCalendar[time=1686708192635,
		areFieldsSet=true,areAllFieldsSet=true,lenient=true,
		zone=sun.util.calendar.ZoneInfo[id="Asia/Seoul",offset=32400000,
		dstSavings=0,useDaylight=false,transitions=30,lastRule=null],
		firstDayOfWeek=1,minimalDaysInFirstWeek=1,ERA=1,
		YEAR=2023,MONTH=5,WEEK_OF_YEAR=24,WEEK_OF_MONTH=3,DAY_OF_MONTH=14,
		DAY_OF_YEAR=165,DAY_OF_WEEK=4,DAY_OF_WEEK_IN_MONTH=2,AM_PM=0,HOUR=11,
		HOUR_OF_DAY=11,MINUTE=3,SECOND=12,MILLISECOND=635,
		ZONE_OFFSET=32400000,DST_OFFSET=0]
		*/
		
		
	}//main end
}//main class end
