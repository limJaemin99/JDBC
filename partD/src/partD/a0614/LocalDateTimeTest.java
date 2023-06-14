package partD.a0614;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

public class LocalDateTimeTest {
	public static final String RESET = "\u001B[0m";
	public static final String FONT_RED = "\u001B[31m";
	public static void main(String[] args) {
		
		System.out.print("Local"+FONT_RED+"Date"+RESET+" 현재 "+FONT_RED+"날짜"+RESET+" : ");
		LocalDate currentDate = LocalDate.now();
		System.out.println(currentDate);
		// LocalDate 현재 날짜 : 2023-06-14
		
		
		System.out.print("Local"+FONT_RED+"Time"+RESET+" 현재 "+FONT_RED+"시간"+RESET+" : ");
		LocalTime currentTime = LocalTime.now();
		System.out.println(currentTime);	// 10억분의 1초, μs 나노초(nano sec)
		// LocalDate 현재 시간 : 11:11:49.883231900
		
		
		System.out.print("Local"+FONT_RED+"DateTime"+RESET+" 현재 "+FONT_RED+"날짜시간"+RESET+" : ");
		LocalDateTime current = LocalDateTime.now();
		System.out.println(current);
		// LocalDate 현재 날짜시간 : 2023-06-14T11:13:57.459694900
										 // ↑ 'T' 가 중요함 ★★★★★
		
		
		// 특정 날짜와 시간을 지정해서 객체를 생성한다.
		System.out.println("\n특정 날짜와 시간을 지정해서 객체를 생성한다.");
		LocalDate mybirth = LocalDate.of(1999, 9, 9);
		LocalTime mybirth_time = LocalTime.of(9, 52);
		System.out.println("Local"+FONT_RED+"Date"+RESET+".of(1999, 9, 9) : "+mybirth);
		System.out.println("Local"+FONT_RED+"Time"+RESET+".of(9, 52) : "+mybirth_time);
		
		
		
		// 날짜 사이의 간격 계산
		System.out.println("\n날짜 사이의 간격 계산");
		System.out.print("내가 태어나서 오늘까지 ");
		Period between = Period.between(mybirth,currentDate);
		System.out.print(between.getYears()+" 년 ");
		System.out.print(between.getMonths()+" 개월 ");
		System.out.print(between.getDays()+" 일");
		

		
		// 기간을 년, 월, 일 단위로 계산
		System.out.println("\n기간을 년, 월, 일 단위로 계산");
		System.out.print("내가 태어나서 오늘까지 ");
		System.out.print(ChronoUnit.DAYS.between(mybirth, current)+" 일 / ");
		System.out.print(ChronoUnit.MONTHS.between(mybirth, current)+" 개월 / ");
		System.out.print(ChronoUnit.YEARS.between(mybirth, current)+" 년");
		
		/*
		 	[enum]
		 	1. 클래스처럼 보이게 하는 상수

			2. 서로 관련있는 상수들끼리 모아 상수들을 대표할 수 있는 이름으로 타입을 정의하는 것

			3. Enum 클래스 형을 기반으로 한 클래스형 선언 
		 */
		
		
	}//main end
}//main class end
