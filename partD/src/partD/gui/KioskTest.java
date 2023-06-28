package partD.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import koreait.jdbc.day05.Check_SalesDAO;

public class KioskTest {
	/*
	 * 1. Kiosk.java 클래스를 KioskTest.java 로 복사하지말고 직접 코딩하세요. 2. 메뉴 항목 6개가 만들어질 때 반복되는
	 * 코드를 반복문으로 변경해보세요. 3. btnStringX 문자열 변수도 배열로 변경, 메뉴항목 6개의 컴포넌트도 배열로 변경해서 합니다.
	 * 4. 그 외에 코딩하면서 배열로 바뀌는게 필요하다 판단되면 변경해서 해보세요.
	 */
	private JFrame frame;
	String[] menu = { "치즈피자", "불고기피자", "파인애플피자", "새우피자", "레전드피자", "양고기피자" };
	int[] cost = { 8000, 9000, 13000, 15000, 20000, 23000 };
	String[] side = { "치즈 추가", "햄 추가" };
	int[] side_cost = { 500, 700 };
	int side_total = 0; // 토핑 추가 가격 합계
	int total_price = 0; // 총 구매금액 합계
	int c_count = 0; // 치즈 토핑 갯수 합계
	int h_count = 0; // 햄 토핑 갯수 합계
	int count1 = 0;
	int count2 = 0;

	String btnString1 = ""; // 장바구니 목록에 포함될 문자열 1
	String btnString2 = ""; // .
	String btnString3 = ""; // .
	String btnString4 = ""; // .
	String btnString5 = ""; // .
	String btnString6 = ""; // 장바구니 목록에 포함될 문자열 6

	String c_choose = "";
	String h_choose = "";

	JLabel lbltotalP = new JLabel("0원");
	JLabel lbltotalT = new JLabel("0원");

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					KioskTest window = new KioskTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}// Override end
		});// invokeLater() end
	}// main end

	public KioskTest() {
		initialize();
	}// 생성자

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 480, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		///// 메뉴 선택 패널///////////////////////////
		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 474, 691);
		frame.getContentPane().add(menuPanel);
		menuPanel.setLayout(null);
		//////////////////////////////////////////

		///// 토핑 추가 패널///////////////////////////
		JPanel toppingPanel = new JPanel();
		toppingPanel.setBackground(Color.orange);
		toppingPanel.setBounds(0, 0, 474, 477);
		frame.getContentPane().add(toppingPanel);
		toppingPanel.setLayout(null);
		toppingPanel.setVisible(false);
		//////////////////////////////////////////

		///// 장바구니 패널////////////////////////////
		JPanel cartPannel = new JPanel();
		cartPannel.setBounds(0, 0, 474, 691);
		frame.getContentPane().add(cartPannel);
		cartPannel.setLayout(null);
		cartPannel.setVisible(false);
		//////////////////////////////////////////

		// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━//

		///// 메뉴 버튼 - 이미지 설정
		JButton[] buttons = new JButton[6];
		String[] buttonLabels = { "1", "2", "3", "4", "5", "6" };
		String[] imageNums = { "./image/1.jpg", "./image/2.jpg", "./image/3.jpg", "./image/4.jpg", "./image/5.jpg",
				"./image/6.jpg" };
//		int x = 0;
//		int y = 0;
		int[] x = { 52, 253, 52, 253, 52, 253 };
		int[] y = { 58, 58, 221, 221, 382, 382 };

		for (int i = 0; i < buttonLabels.length; i++) {

//			//1,3,5 ▶ x=52		//1,2 ▶ y=58
//			//2,4,6 ▶ x=253		//3,4 ▶ y=221
//								//5,6 ▶ y=382
//			if(i%2==0) x=52;
//			else x=253;
//			
//			if(i<2) y=58;
//			else if(i<4) y=221;
//			else if(i<6) y=382;

			buttons[i] = new JButton(buttonLabels[i]);
			buttons[i].setIcon(new ImageIcon(imageNums[i]));
			buttons[i].setBounds(x[i], y[i], 147, 95);
			menuPanel.add(buttons[i]);

		} // for end
			//////////////////////////////////////////

		///// 메뉴 가격 표시 레이블 : 가격 정수값에 포맷 적용하는 메소드 priceLabel
		JLabel[] priceLabels = new JLabel[6];

		int[] prices = { 8000, 9000, 13000, 15000, 20000, 23000 };
		int[] newY = { 151, 151, 315, 316, 477, 478 };
		System.arraycopy(newY, 0, y, 0, y.length); // 기존 배열y에 새로운 데이터 복사

		for (int i = 0; i < 6; i++) {

			priceLabels[i] = new JLabel(priceLabel(prices[i]));
			priceLabels[i].setHorizontalAlignment(SwingConstants.CENTER);
			priceLabels[i].setBounds(x[i], y[i], 108, 29);
			menuPanel.add(priceLabels[i]);

		} // for end
			//////////////////////////////////////////

		///// 주문 개수 증감하는 스피너
		JSpinner[] spinners = new JSpinner[6];
		String[] btnStrings = new String[6];

		int[] newY2 = { 151, 151, 313, 313, 475, 475 };
		System.arraycopy(newY2, 0, y, 0, y.length); // 기존 배열y에 새로운 데이터 복사

		for (int i = 0; i < 6; i++) {

			final int index = i; // final 변수로 선언하여 클로저 문제 해결

			spinners[i] = new JSpinner();
			spinners[i].setModel(new SpinnerNumberModel(0, 0, 20, 1));
			spinners[i].setBounds(x[i] + 103, y[i], 44, 29);
			spinners[i].addChangeListener(new ChangeListener() {
				@Override
				public void stateChanged(ChangeEvent e) {
					if ((int) spinners[index].getValue() > 0)
						btnStrings[index] = menu[index] + "\t\t" + priceLabel(cost[index]) + "\t"
								+ (int) spinners[index].getValue() + "\n\n";
				}// override end
			});// addChangeListener() end

			menuPanel.add(spinners[i]);

		} // for end
			//////////////////////////////////////////

		///// 메뉴이름 레이블
		JLabel[] nameLabel = new JLabel[6];
		int[] newY3 = { 189, 189, 350, 352, 516, 517 };
		System.arraycopy(newY3, 0, y, 0, y.length); // 기존 배열y에 새로운 데이터 복사

		for (int i = 0; i < 6; i++) {

			nameLabel[i] = new JLabel(menu[i]);
			nameLabel[i].setHorizontalAlignment(SwingConstants.CENTER);
			nameLabel[i].setBounds(x[i], y[i], 147, 22);
			menuPanel.add(nameLabel[i]);
			
		}//for end
		//////////////////////////////////////////

		/////메뉴 버튼 클릭했을 때 실행하는 액션 -> 토핑 추가 선택 패널 보이기
		for (int i = 0; i < 6; i++) {
			
			final int index = i; // final 변수로 선언하여 클로저 문제 해결
			
			buttons[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					if((int)spinners[index].getValue() > 0) {
						toppingPanel.setVisible(true);
						menuPanel.setVisible(false);
						
						btnStrings[index] = menu[index]+"\t\t"+priceLabel(cost[index])+"\t"+(int)spinners[index].getValue()+"\n\n";
					}
				}//override end
			});//actionPerformed() end
		}//for end
		//////////////////////////////////////////
		
		/////토핑 추가 패널 컴포넌트들
		JLabel addCheese = new JLabel("치즈 추가");
		addCheese.setHorizontalAlignment(SwingConstants.CENTER);
		addCheese.setBounds(73,88,125,47);
		toppingPanel.add(addCheese);
		
		JSpinner spinCheese = new JSpinner();
		spinCheese.setModel(new SpinnerNumberModel(0,0,5,1));
		spinCheese.setBounds(269,95,84,47);
		
		spinCheese.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
			    int cheeseCount = (int) spinCheese.getValue();
			    int cheeseDiff = cheeseCount - count1;
			    int cheeseCost = side_cost[0] * cheeseDiff;

			    side_total += cheeseCost;
			    lbltotalT.setText(priceLabel(side_total));
			    lbltotalP.setText(priceLabel(total_price));

			    count1 = cheeseCount;
			}//override end
		});//addChangeListener() end
		
		toppingPanel.add(spinCheese);
		
		/*
			1. spinCheese의 값이 변경되었을 때 stateChanged 메서드가 호출됩니다.
			2. spinCheese의 현재 값인 cheeseCount를 가져옵니다.
			3. 이전 count1 값과 cheeseCount 값의 차이를 계산하여 cheeseDiff에 저장합니다. 이 값은 증가 또는 감소한 피자 치즈 수량입니다.
			4. cheeseDiff를 기반으로 피자 치즈에 대한 가격을 계산하여 cheeseCost에 저장합니다. side_cost[0]는 피자 치즈의 단가입니다.
			5. side_total에 cheeseCost를 더합니다. 이는 피자 치즈의 가격을 전체 총액에 반영하는 것입니다.
			6. 변경된 side_total과 total_price 값을 lbltotalT와 lbltotalP에 각각 업데이트하여 화면에 표시합니다.
			7. count1 값을 cheeseCount로 업데이트합니다.
		*/
		
		
		JLabel addHam = new JLabel("햄 추가");
		addHam.setHorizontalAlignment(SwingConstants.CENTER);
		addHam.setBounds(73,195,125,22);
		toppingPanel.add(addHam);
		
		JSpinner spinHam = new JSpinner();
		spinHam.setModel(new SpinnerNumberModel(0,0,5,1));
		spinHam.setBounds(269, 195, 84, 47);
		
		spinHam.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
			    int hamCount = (int) spinHam.getValue();
			    int hamDiff = hamCount - count2;
			    int hamCost = side_cost[1] * hamDiff;

			    side_total += hamCost;
			    lbltotalT.setText(priceLabel(side_total));
			    lbltotalP.setText(priceLabel(total_price));

			    count2 = hamCount;
			}//override end
		});//addChangeListener() end
		
		toppingPanel.add(spinHam);
		
		
		JLabel cartLabel = new JLabel("추가된 금액");
		cartLabel.setFont(new Font("굴림", Font.BOLD, 12));
		cartLabel.setBounds(114, 307, 84, 22);
		toppingPanel.add(cartLabel);
		
		
		
		
		//////////////////////////////////////////
		
		
		
	}// initialize() end

	///// 출력되는 숫자 3개마다 ,로 나뉘어 출력되도록 설정
	private String priceLabel(int num) {
		DecimalFormat dc = new DecimalFormat("#,###,###,###");
		String d = dc.format(num);
		return d + "원";
	}// pricaLabel end

}// class end
