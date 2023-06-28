package partD.gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class KioskTest {
	/*
	1. Kiosk.java 클래스를 KioskTest.java 로 복사하지말고 직접 코딩하세요.
	2. 메뉴 항목 6개가 만들어질 때 반복되는 코드를 반복문으로 변경해보세요.
	3. btnStringX 문자열 변수도 배열로 변경, 메뉴항목 6개의 컴포넌트도 배열로 변경해서 합니다.
	4. 그 외에 코딩하면서 배열로 바뀌는게 필요하다 판단되면 변경해서 해보세요.
*/
	private JFrame frame;
	String[] menu = {"치즈피자","불고기피자","파인애플피자","새우피자","레전드피자","양고기피자"};
	int[] cost = {8000,9000,13000,15000,20000,23000};
	String[] side = {"치즈 추가","햄 추가"};
	int[] side_cost = {500,700};
	int side_total = 0;		//토핑 추가 가격 합계
	int total_price = 0;	//총 구매금액 합계
	int c_count = 0;		//치즈 토핑 갯수 합계
	int h_count = 0;		//햄 토핑 갯수 합계
	int count1 = 0;
	int count2 = 0;
	
	String btnString1 = "";	//장바구니 목록에 포함될 문자열 1
	String btnString2 = "";	//			 .
	String btnString3 = "";	//			 .
	String btnString4 = "";	//			 .
	String btnString5 = "";	//			 .
	String btnString6 = "";	//장바구니 목록에 포함될 문자열 6

	String c_choose = "";
	String h_choose = "";

	JLabel lbltotalP = new JLabel("0원");

	JLabel lbltotalT = new JLabel("0원");
	
	
	
}//class end
