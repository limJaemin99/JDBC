package partD.gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.ImageIcon;
import javax.swing.SpinnerNumberModel;
import javax.swing.JTextArea;
/*
	1. Kiosk.java 클래스를 KioskTest.java 로 복사하지말고 직접 코딩하세요.
	2. 메뉴 항목 6개가 만들어질 때 반복되는 코드를 반복문으로 변경해보세요.
	3. btnStringX 문자열 변수도 배열로 변경, 메뉴항목 6개의 컴포넌트도 배열로 변경해서 합니다.
	4. 그 외에 코딩하면서 배열로 바뀌는게 필요하다 판단되면 변경해서 해보세요.
*/
public class Kiosk {

	private JFrame frame;
	String[] menu = { "치즈피자", "불고기피자", "파인애플피자", "새우피자", "레전드피자", "양고기피자" };
	int[] cost = { 8000, 9000, 13000, 15000, 20000, 23000 };
	String[] side = { "치즈추가", "햄추가" };
	int[] side_cost = { 500, 700 };
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kiosk window = new Kiosk();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Kiosk() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 480, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);


		///////// 메뉴 선택 패널
		JPanel menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 474, 691);
		frame.getContentPane().add(menuPanel);
		menuPanel.setLayout(null);

		//////// 토핑 추가 패널
		JPanel toppingPanel = new JPanel();
		toppingPanel.setBackground(Color.ORANGE);
		toppingPanel.setBounds(0, 0, 474, 477);
		frame.getContentPane().add(toppingPanel);
		toppingPanel.setLayout(null);
		toppingPanel.setVisible(false);

		
		///////// 장바구니 패널
		JPanel cartPannel = new JPanel();
		cartPannel.setBounds(0, 0, 474, 691);
		frame.getContentPane().add(cartPannel);
		cartPannel.setLayout(null);
		cartPannel.setVisible(false);

		
		
		
///////// 메뉴 패널 컴포넌트들

		/// 메뉴 버튼 - 이미지 설정
		JButton btnDecide1 = new JButton("1");
		btnDecide1.setIcon(new ImageIcon("./image/1.jpg"));
		btnDecide1.setBounds(52, 58, 147, 95);
		menuPanel.add(btnDecide1);

		JButton btnDecide2 = new JButton("2"); // \uD53C\uC7902
		btnDecide2.setIcon(new ImageIcon("./image/2.jpg"));
		btnDecide2.setBounds(253, 58, 147, 95);				
		menuPanel.add(btnDecide2);

		JButton btnDecide3 = new JButton("3");
		btnDecide3.setIcon(new ImageIcon("./image/3.jpg"));   
		btnDecide3.setBounds(52, 221, 147, 95);
		menuPanel.add(btnDecide3);

		JButton btnDecide4 = new JButton("4");
		btnDecide4.setIcon(new ImageIcon("./image/4.jpg"));   
		btnDecide4.setBounds(253, 221, 147, 95);
		menuPanel.add(btnDecide4);

		JButton btnDecide5 = new JButton("5");
		btnDecide5.setIcon(new ImageIcon("./image/5.jpg"));
		btnDecide5.setBounds(52, 382, 147, 95);
		menuPanel.add(btnDecide5);

		JButton btnDecide6 = new JButton("6");
		btnDecide6.setIcon(new ImageIcon("./image/6.jpg"));
		btnDecide6.setBounds(253, 382, 147, 95);
		menuPanel.add(btnDecide6);

		// 메뉴 가격 표시 레이블 : 가격 정수값에 포맷 적용하는 메소드 priceLabel
		JLabel lblpc1 = new JLabel(priceLabel(8000));
		lblpc1.setHorizontalAlignment(SwingConstants.CENTER);
		lblpc1.setBounds(52, 151, 108, 29);				//이미지 좌표(52, 58, 147, 95);	//레이블 위치 (52, 151, 108, 29);  x=그림, y=그림+93
		menuPanel.add(lblpc1);

		JLabel lblpcl2 = new JLabel(priceLabel(9000));
		lblpcl2.setHorizontalAlignment(SwingConstants.CENTER);
		lblpcl2.setBounds(253, 151, 108, 29);			//(253, 58, 147, 95);	
		menuPanel.add(lblpcl2);

		JLabel lblpc33 = new JLabel(priceLabel(13000));
		lblpc33.setHorizontalAlignment(SwingConstants.CENTER);
		lblpc33.setBounds(52, 315, 108, 29);			//(52, 221, 147, 95);
		menuPanel.add(lblpc33);

		JLabel lblpc4 = new JLabel(priceLabel(15000));
		lblpc4.setHorizontalAlignment(SwingConstants.CENTER);
		lblpc4.setBounds(253, 316, 108, 29);			//(253, 221, 147, 95);
		menuPanel.add(lblpc4);

		JLabel lblpc5 = new JLabel(priceLabel(20000));
		lblpc5.setHorizontalAlignment(SwingConstants.CENTER);
		lblpc5.setBounds(52, 477, 108, 29);
		menuPanel.add(lblpc5);

		JLabel lblpc6 = new JLabel(priceLabel(23000));
		lblpc6.setHorizontalAlignment(SwingConstants.CENTER);
		lblpc6.setBounds(253, 478, 108, 29);
		menuPanel.add(lblpc6);

		// 주문 개수 증감하는 스피너
		JSpinner spinner1 = new JSpinner();
		spinner1.setModel(new SpinnerNumberModel(0, 0, 20, 1));
		spinner1.setBounds(155, 151, 44, 29);		//(52, 58, 147, 95);      x=그림+103 , y=그림+93
		spinner1.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) { // 스피너 값 변경되면 실행하는 메소드
				if ((int) spinner1.getValue() > 0)
					btnString1 = menu[0] + "\t\t" + priceLabel(cost[0]) + "\t" + (int) spinner1.getValue() + "\n\n";
			}
		});
		menuPanel.add(spinner1);

		JSpinner spinner2 = new JSpinner();
		spinner2.setModel(new SpinnerNumberModel(0, 0, 20, 1));
		spinner2.setBounds(356, 151, 44, 29);		//(253, 58, 147, 95);	
		spinner2.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if ((int) spinner2.getValue() > 0)
					btnString2 = menu[1] + "\t\t" + priceLabel(cost[1]) + "\t" + (int) spinner2.getValue() + "\n\n";

			}
		});
		menuPanel.add(spinner2);

		JSpinner spinner3 = new JSpinner();
		spinner3.setModel(new SpinnerNumberModel(0, 0, 20, 1));
		spinner3.setBounds(155, 313, 44, 29);		//(52, 221, 147, 95);
		spinner3.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if ((int) spinner3.getValue() > 0)
					btnString3 = menu[2] + "\t\t" + priceLabel(cost[2]) + "\t" + (int) spinner3.getValue() + "\n\n";

			}
		});
		menuPanel.add(spinner3);

		JSpinner spinner4 = new JSpinner();
		spinner4.setModel(new SpinnerNumberModel(0, 0, 20, 1));
		spinner4.setBounds(356, 313, 44, 29);		//(253, 221, 147, 95);
		spinner4.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if ((int) spinner4.getValue() > 0)
					btnString4 = menu[3] + "\t\t" + priceLabel(cost[3]) + "\t" + (int) spinner4.getValue() + "\n\n";

			}
		});
		menuPanel.add(spinner4);

		JSpinner spinner5 = new JSpinner();
		spinner5.setModel(new SpinnerNumberModel(0, 0, 20, 1));
		spinner5.setBounds(155, 475, 44, 29);
		spinner5.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if ((int) spinner5.getValue() > 0)
					btnString5 = menu[4] + "\t\t" + priceLabel(cost[4]) + "\t" + (int) spinner5.getValue() + "\n\n";

			}
		});
		menuPanel.add(spinner5);

		JSpinner spinner6 = new JSpinner();
		spinner6.setModel(new SpinnerNumberModel(0, 0, 20, 1));
		spinner6.setBounds(356, 475, 44, 29);
		spinner6.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				if ((int) spinner6.getValue() > 0)
					btnString6 = menu[5] + "\t\t" + priceLabel(cost[5]) + "\t" + (int) spinner6.getValue() + "\n\n";

			}
		});
		menuPanel.add(spinner6);

		// 메뉴이름 레이블
		JLabel lblpazzaName0 = new JLabel(menu[0]);
		lblpazzaName0.setHorizontalAlignment(SwingConstants.CENTER);
		lblpazzaName0.setBounds(52, 189, 147, 22);		//(52, 58, 147, 95);    x=그림, y=그림+130
		menuPanel.add(lblpazzaName0);

		JLabel lblpazzaName0_1 = new JLabel(menu[1]);
		lblpazzaName0_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblpazzaName0_1.setBounds(253, 189, 147, 22);   //(253, 58, 147, 95);	
		menuPanel.add(lblpazzaName0_1);

		JLabel lblpazzaName0_2 = new JLabel(menu[2]);
		lblpazzaName0_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblpazzaName0_2.setBounds(52, 350, 147, 22);	//(52, 221, 147, 95);
		menuPanel.add(lblpazzaName0_2);

		JLabel lblpazzaName0_3 = new JLabel(menu[3]);
		lblpazzaName0_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblpazzaName0_3.setBounds(253, 352, 147, 22);	//(253, 221, 147, 95);
		menuPanel.add(lblpazzaName0_3);

		JLabel lblpazzaName0_4 = new JLabel(menu[4]);
		lblpazzaName0_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblpazzaName0_4.setBounds(52, 516, 147, 22);
		menuPanel.add(lblpazzaName0_4);

		JLabel lblpazzaName0_5 = new JLabel(menu[5]);
		lblpazzaName0_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblpazzaName0_5.setBounds(253, 517, 147, 22);
		menuPanel.add(lblpazzaName0_5);

		// 메뉴 버튼 클릭했을 때 실행하는 액션 -> 토핑 추가 선택 패널 보이기
		btnDecide1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // 피자메뉴의 spinner 값이 0보다 커지는 경우, 해당 피자 버튼을 클릭 시 토핑 선택 화면이 출력되도록
															// 설정.
				if ((int) spinner1.getValue() > 0) { // 선택 수량이 1 이상일 때만
					toppingPanel.setVisible(true);
					menuPanel.setVisible(false);

					btnString1 = menu[0] + "\t\t" + priceLabel(cost[0]) + "\t" + (int) spinner1.getValue() + "\n\n";

				}
			}
		});

		btnDecide2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((int) spinner2.getValue() > 0) {
					toppingPanel.setVisible(true);
					menuPanel.setVisible(false);

					btnString2 = menu[1] + "\t\t" + priceLabel(cost[1]) + "\t" + (int) spinner2.getValue() + "\n\n";

				}
			}
		});

		btnDecide3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((int) spinner3.getValue() > 0) {
					toppingPanel.setVisible(true);
					menuPanel.setVisible(false);

					btnString3 = menu[2] + "\t\t" + priceLabel(cost[2]) + "\t" + (int) spinner3.getValue() + "\n\n";

				}

			}
		});

		btnDecide4.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(spinner4.getValue());
				if ((int) spinner4.getValue() > 0) {
					toppingPanel.setVisible(true);
					menuPanel.setVisible(false);

					btnString4 = menu[3] + "\t\t" + priceLabel(cost[3]) + "\t" + (int) spinner4.getValue() + "\n\n";

				}

			}
		});

		btnDecide5.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((int) spinner5.getValue() > 0) {
					toppingPanel.setVisible(true);
					menuPanel.setVisible(false);

					btnString5 = menu[4] + "\t\t" + priceLabel(cost[4]) + "\t" + (int) spinner5.getValue() + "\n\n";

				}
			}
		});

		btnDecide6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if ((int) spinner6.getValue() > 0) {
					toppingPanel.setVisible(true);
					menuPanel.setVisible(false);

					btnString6 = menu[5] + "\t\t" + priceLabel(cost[5]) + "\t" + (int) spinner6.getValue() + "\n\n";

				}
			}
		});

////////토핑 추가 패널 컴포넌트들
		JLabel lblAddCorn = new JLabel("치즈 추가");
		lblAddCorn.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddCorn.setBounds(73, 88, 125, 47);
		toppingPanel.add(lblAddCorn);

		JLabel lblAddPot = new JLabel("햄 추가");
		lblAddPot.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddPot.setBounds(73, 195, 125, 22);
		toppingPanel.add(lblAddPot);

		JSpinner spinner_corn = new JSpinner();
		spinner_corn.setModel(new SpinnerNumberModel(0,0,5,1));
		spinner_corn.setBounds(269, 95, 84, 47);
		
		
		
		//ChangeListener 는 스피너의 선택값이 바뀔때 발생하는 이벤트를 처리합니다.
		spinner_corn.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) { // 스피너 값 증가/감소 시 결제 전 화면에서 해당 메뉴가 추가될 수 있도록 설정.
				if (count1 < (int) spinner_corn.getValue()) {

					side_total += side_cost[0];		//갯수가 증가해서 가격 더해주기
					lbltotalT.setText(priceLabel(side_total));	//토핑 패널의 레이블 문자열 설정
					lbltotalP.setText(priceLabel(total_price));	//카트 패널의 레이블 문자열 설정
					count1 = (int) spinner_corn.getValue();
				} else if (count1 > (int) spinner_corn.getValue()) {

					side_total -= side_cost[0];		//갯수가 감소해서 가격을 빼주기
					lbltotalT.setText(priceLabel(side_total));	//토핑 패널의 레이블 문자열 설정
					lbltotalP.setText(priceLabel(total_price));	//카트 패널의 레이블 문자열 설정
					count1 = (int) spinner_corn.getValue();
				}

			}

		});
		toppingPanel.add(spinner_corn);

		JSpinner spinner_pot = new JSpinner();
		spinner_pot.setModel(new SpinnerNumberModel(0,0,5,1));
		spinner_pot.setBounds(269, 195, 84, 47);
		spinner_pot.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {

				if (count2 < (int) spinner_pot.getValue()) {

					side_total += side_cost[1];
					lbltotalT.setText(priceLabel(side_total));
					lbltotalP.setText(priceLabel(total_price));
					count2 = (int) spinner_pot.getValue();
				} else if (count2 > (int) spinner_pot.getValue()) {

					side_total -= side_cost[1];
					lbltotalT.setText(priceLabel(side_total));
					lbltotalP.setText(priceLabel(total_price));
					count2 = (int) spinner_pot.getValue();
				}

			}

		});

		toppingPanel.add(spinner_pot);

		JLabel lbltotal_cart = new JLabel("추가된 금액");
		lbltotal_cart.setFont(new Font("굴림", Font.BOLD, 12));
		lbltotal_cart.setBounds(114, 307, 84, 22);
		toppingPanel.add(lbltotal_cart);

		JLabel lblNewLabel_3 = new JLabel("토핑 추가 옵션");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(154, 10, 139, 47);
		toppingPanel.add(lblNewLabel_3);

		JLabel lblNewLabel_2 = new JLabel("700원");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(73, 220, 125, 15);
		toppingPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("500원");
		lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2_1.setBounds(73, 121, 125, 15);
		toppingPanel.add(lblNewLabel_2_1);

		lbltotalT.setFont(new Font("굴림", Font.BOLD, 12));
		lbltotalT.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltotalT.setBounds(272, 307, 81, 28);
		toppingPanel.add(lbltotalT);

		/////////// 토핑 추가 패널의 실행버튼 컴포넌트 - 선택
		JButton btnChoose = new JButton("선택");
		btnChoose.setBounds(284, 374, 113, 41);
		toppingPanel.add(btnChoose);
		btnChoose.addActionListener(new ActionListener() { // 선택 버튼 추가
			@Override
			public void actionPerformed(ActionEvent e) { // 토핑 선택화면에서 선택 버튼 누르면 다시 메인메뉴 화면으로 돌아가도록 설정.
				toppingPanel.setVisible(false);
				menuPanel.setVisible(true);
				if ((int) spinner_corn.getValue() > 0) { // spinner 값이 증가/ 감소 할때마다 추가되는 금액이 출력되로록 설정.
					c_count += (int) spinner_corn.getValue();
					c_choose = side[0] + "\t\t" + priceLabel(side_cost[0]) + "\t" + c_count + "\n\n";

				}
				if ((int) spinner_pot.getValue() > 0) {
					h_count += (int) spinner_pot.getValue();
					h_choose = side[1] + "\t\t" + priceLabel(side_cost[1]) + "\t" + h_count + "\n\n";

				}
				side_total += (side_cost[1] * h_count);
				side_total += (side_cost[0] * c_count);
				spinner_corn.setValue(0);
				spinner_pot.setValue(0);

			}
		});

		
/////////// 장바구니 패널의 컴포넌트들
		JLabel lblNewLabel = new JLabel("총 금액");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 24));
		lblNewLabel.setBounds(90, 510, 148, 68);
		cartPannel.add(lblNewLabel);

		lbltotalP.setFont(new Font("굴림", Font.BOLD, 20));
		lbltotalP.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltotalP.setBounds(224, 527, 142, 37);
		cartPannel.add(lbltotalP);

		JTextArea textArea = new JTextArea(TextArea.SCROLLBARS_VERTICAL_ONLY, 0);
		textArea.setBounds(33, 29, 404, 429);
		textArea.setEditable(false);
		textArea.setText("제품명\t\t제품단가\t수량\n\n");
		cartPannel.add(textArea);
		

///////////  주요 버튼 Action 		
		/////////// 메뉴 선택 패널의 실행버튼 컴포넌트 - 장바구니
		JButton btncart = new JButton("Cart");
		btncart.setBounds(259, 552, 141, 68);
		menuPanel.add(btncart);
		btncart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cartPannel.setVisible(true);
				menuPanel.setVisible(false);
				toppingPanel.setVisible(false);

				total_price = (cost[0] * (int) spinner1.getValue()) // Cart 버튼 클릭시, 장바구니 화면이 출력되고, 주문한 메뉴의 총 금액이 출력되도록
																	// 설정.
						+ (cost[1] * (int) spinner2.getValue()) + (cost[2] * (int) spinner3.getValue())
						+ (cost[3] * (int) spinner4.getValue()) + (cost[4] * (int) spinner5.getValue())
						+ (cost[5] * (int) spinner6.getValue()) + side_total;

				textArea.append(btnString1 + btnString2 + btnString3 + btnString4 + btnString5 + btnString6 + c_choose
						+ h_choose);
				lbltotalP.setText(priceLabel(total_price));
			}

		});

		/////////// 장바구니 패널의 실행버튼 컴포넌트 - 뒤돌아가기
		JButton btnClean = new JButton("뒤돌아가기"); // 뒤로가기버튼 클릭 시, 모든 값 초기화되도록 설정.
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnString1 = "";
				btnString2 = "";
				btnString3 = "";
				btnString4 = "";
				btnString5 = "";
				btnString6 = "";
				c_choose = "";
				h_choose = "";
				c_count = 0;
				h_count = 0;
				spinner1.setValue(0);
				spinner2.setValue(0);
				spinner3.setValue(0);
				spinner4.setValue(0);
				spinner5.setValue(0);
				spinner6.setValue(0);
				total_price = 0;
				side_total = 0;
				count1 = 0;
				count2 = 0;
				textArea.setText("제품명\t\t제품단가\t수량\n\n");
				cartPannel.setVisible(false);
				menuPanel.setVisible(true);
				cartPannel.setVisible(false);

			}

		});
		btnClean.setBounds(59, 600, 97, 23);
		cartPannel.add(btnClean);

		/////////// 장바구니 패널의 실행버튼 컴포넌트 - 결제하기
		JButton btnconfirm = new JButton("결제하기");
		btnconfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // 결제 버튼 클릭 시, 포장/매장 선택 후 결제. OK 버튼을 누르면 결제한 금액과 함께 안내 메시지 출력.

				if (total_price > 0) { // 결제할 금액이 0원보다 커야 결제버튼 활성화되도록 설정.

					String[] st = { "포장", "매장" };
					JOptionPane.showInputDialog(null, "이대로 주문하시겠습니까?\n 포장여부를 체크해주세요.", "결제완료",
							JOptionPane.QUESTION_MESSAGE, null, st, "포장");

					JOptionPane.showMessageDialog(null, "결제 완료 되었습니다.\n" + "결제 금액은 " + total_price + " 원 입니다.");
					btnString1 = "";
					btnString2 = "";
					btnString3 = "";
					btnString4 = "";
					btnString5 = "";
					btnString6 = "";
					c_choose = "";
					h_choose = "";
					c_count = 0;
					h_count = 0;
					total_price = 0;
					side_total = 0;
					count1 = 0;
					count2 = 0;
					spinner1.setValue(0);
					spinner2.setValue(0);
					spinner3.setValue(0);
					spinner4.setValue(0);
					spinner5.setValue(0);
					spinner6.setValue(0);
					textArea.setText("제품명\t\t제품단가\t수량\n\n");
					cartPannel.setVisible(false);
					menuPanel.setVisible(true);
					cartPannel.setVisible(false);
				} else { // 결제할 금액이 0원인경우, 결제하기 버튼이 클릭되지 않도록 설정.
					JOptionPane.showMessageDialog(null, "결제할 내용이 없습니다.");
				}

			}
		});
		btnconfirm.setBounds(299, 600, 97, 23); // btnClean.setBounds(59, 600, 97, 23); 뒤돌아가기
		cartPannel.add(btnconfirm);
	}

	private String priceLabel(int num) { // 출력되는 숫자 3개마다 ,로 나뉘어 출력되도록 설정.
		DecimalFormat dc = new DecimalFormat("#,###,###,###");
		String d = dc.format(num);
		return d + "원";
	}
}