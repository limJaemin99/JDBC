package partD.rekiosk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class MenuPanel {
	
	//이 클래스에서 생성하려는 메뉴 패널 객체 필드를 선언하고 객체 생성하여 대입
	private JPanel panel_Menu = new JPanel();
	
	//위 객체의 getter
	public JPanel getPanel_Menu() {
		return panel_Menu;
	}
	
	//접근해야하는 다른 패널 private 로 선언
	private JPanel panel_Cart;
	private JPanel panel_Topping;		//접근해야하는 다른 패널
	
	//다른 패널에서 접근하는 이 메뉴 패널의 변수 선언 ▶ 전역변수 선언
	private String[] menu = { "치즈피자", "불고기피자", "파인애플피자", "새우피자", "레전드피자", "양고기피자" };
	private int[] cost = { 8000, 9000, 13000, 15000, 20000, 23000 };
	int total_price = 0;
	String topping;
	String[] btnString = new String[6];
	
	//다른 패널에서 접근해야하는 이(this) 메뉴 패널의 컴포넌트 선언 ▶ 전역변수 선언
	JSpinner[] spinner = new JSpinner[6];
	JLabel[] lblpazzaName = new JLabel[6];
	
	// 출력되는 숫자 3개마다 , 출력되도록 설정. 자주 사용할 것이므로 메소드로 정의함.
	private String priceLabel(int num) { 
		DecimalFormat dc = new DecimalFormat("#,###,###,###");
		String d = dc.format(num);
		return d + "원";
	}
	
	// 다른 2개의 패널을 접근하기 위해 인자로 받음. 메뉴 패널의 컴포넌트들을 만들고, 만들어진 메뉴 패널 리턴함.
	public JPanel createPanel(ToppingPannel ptopping, CartPanel pcart) {
		this.panel_Topping=ptopping.getPanel_Topping();
		this.panel_Cart = pcart.getPanel_Cart();

///////// 메뉴 패널 컴포넌트들

		JButton[] btnDecide = new JButton[6];
		int x, y;
		
		JLabel[] lblpc = new JLabel[6];
		
	
		for(int i=0;i<6;i++) {
			final int idx = i;			//익명클래스 내부에서 i값을 전달받기 위해 선언한 변수. 익명클래스 내부에서 직접 i 변수 사용 못함.
			
			
			/// 메뉴 버튼 - 이미지 설정
			btnDecide[i] = new JButton(String.valueOf(i));
			btnDecide[i].setIcon(new ImageIcon("./image/"+(i+1)+".jpg"));
			if(i%2==0) x=52; 	else x=253;
			y= 58+163*(i/2);
			btnDecide[i].setBounds(x, y, 147, 95);
			panel_Menu.add(btnDecide[i]);

		
			// 메뉴 가격 표시 레이블 : 가격 정수값에 포맷 적용하는 메소드 priceLabel
			lblpc[i] = new JLabel(priceLabel(cost[i]));
			lblpc[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblpc[i].setBounds(x, y+93, 108, 29);    // x=그림x, y=그림y+93
			panel_Menu.add(lblpc[i]);
		
		
			// 주문 개수 증감하는 스피너
			spinner[i] = new JSpinner();
			spinner[i].setModel(new SpinnerNumberModel(0, 0, 20, 1));
			spinner[i].setBounds(x+103, y+93, 44, 29);			//x=그림x+103 , y=그림y+93
			spinner[i].addChangeListener(new ChangeListener() {
				
				@Override
				public void stateChanged(ChangeEvent e) { // 스피너 값 변경되면 실행하는 메소드
					if ((int) spinner[idx].getValue() > 0)
						btnString[idx] = "\n"+menu[idx] + "\t\t" + priceLabel(cost[idx]) + "\t" + (int) spinner[idx].getValue() + "\n";
				}
			});
			panel_Menu.add(spinner[i]);
			
			// 메뉴이름 레이블
			lblpazzaName[i] = new JLabel(menu[i]);
			lblpazzaName[i].setHorizontalAlignment(SwingConstants.CENTER);
			lblpazzaName[i].setBounds(x, y+120, 147, 22); 		//x=그림x, y=그림y+130
			panel_Menu.add(lblpazzaName[i]);
			

			// 메뉴 버튼 클랙했을 때 실행하는 액션 -> 토핑 추가 선택 패널 보이기
			btnDecide[i].addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) { // 피자메뉴의 spinner 값이 0보다 커지는 경우, 해당 피자 버튼을 클릭 시 토핑 선택 화면이 출력되도록
																// 설정.
					if ((int) spinner[idx].getValue() > 0) { // 선택 수량이 1 이상일 때만
						ptopping.selected = idx;			//선택된 i 값 전달하기
						ptopping.lblTitle.setText(lblpazzaName[idx].getText() + "\t-토핑 추가 옵션");
						ptopping.spinner_corn.setValue(ptopping.toppingC[idx]);
						ptopping.spinner_ham.setValue(ptopping.toppingH[idx]);
//						ptopping.side_total=0;
						panel_Topping.setVisible(true);
						panel_Menu.setVisible(false);
						
//						btnString[idx] = menu[idx] + "\t\t" + priceLabel(cost[idx]) + "\t" + (int) spinner[idx].getValue() + "\n\n";
	
					}
				}
			});
		
		}
	
		/////////// 메뉴 선택 패널의 실행버튼 컴포넌트 - 장바구니
		JButton btncart = new JButton("Cart");
		btncart.setBounds(259, 552, 141, 68);
		panel_Menu.add(btncart);
		
		btncart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				panel_Cart.setVisible(true);
				panel_Menu.setVisible(false);
				panel_Topping.setVisible(false);
				
				total_price =0;
				pcart.textArea.setText("제품명\t\t제품단가\t수량\n\n");
				
				int side_total=0;
				for(int i=0;i<6;i++) {
					topping="";
					total_price += (cost[i] * (int) spinner[i].getValue());  // Cart 버튼 클릭시, 장바구니 화면이 출력되고 주문한 메뉴의 총 금액이 출력되도록 계산
					if (ptopping.toppingC[i] > 0)  	// spinner 값이 증가/ 감소 할때마다 장바구니에 표시될 문자열 변경
						topping += "ㄴ.." +
								ptopping.side[0] + "\t\t" + priceLabel(ptopping.side_cost[0]) + "\t" + ptopping.toppingC[i] + "\n" ;
					if (ptopping.toppingH[i] > 0)
						topping += "ㄴ.." +
								ptopping.side[1] + "\t\t" + priceLabel(ptopping.side_cost[1]) + "\t" + ptopping.toppingH[i] + "\n" ;		
					
					side_total += ptopping.toppingC[i]*ptopping.side_cost[0] + ptopping.toppingH[i]*ptopping.side_cost[1];
					
					if(btnString[i]!=null) {
						pcart.textArea.append(btnString[i]);
						pcart.textArea.append(topping);
					}	
				}
				
				total_price += side_total;
				pcart.lbltotalP.setText(priceLabel(total_price));
			}
			
		});
	
		return panel_Menu;
	
	
	}

}
