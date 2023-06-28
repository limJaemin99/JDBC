package partD.rekiosk;

import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

public class CartPanel {
	
	//이 클래스에서 생성하려는 카트 패널 객체 필드를 선언하고 객체 생성하여 대입
	private JPanel panel_Cart= new JPanel();
	
	//위 객체의 getter
	public JPanel getPanel_Cart() {
		return panel_Cart;
	}
	
	//접근해야하는 다른 패널 private 로 선언
	private JPanel panel_Menu;			//접근해야하는 다른 패널
	
	//다른 패널에서 접근하는 이 카트 패널의 변수 선언 ▶ 전역변수 선언
	JLabel lbltotalP = new JLabel("0원");
	JTextArea textArea = new JTextArea(TextArea.SCROLLBARS_VERTICAL_ONLY, 0);
	String[] btnString = new String[6];

	// 다른 2개의 패널을 접근하기 위해 인자로 받음. 카트 패널의 컴포넌트들을 만들고, 만들어진 카트 패널 리턴함.
	public JPanel createPanel(MenuPanel pmenu, ToppingPannel ptopping ) {
		this.panel_Menu = pmenu.getPanel_Menu();
		
		textArea.setBounds(33, 29, 404, 429);
		textArea.setEditable(false);
		textArea.setText("제품명\t\t제품단가\t수량\n\n");
		panel_Cart.add(textArea);
		
		
		JLabel lblNewLabel = new JLabel("총 금액");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 24));
		lblNewLabel.setBounds(90, 510, 148, 68);
		panel_Cart.add(lblNewLabel);

		lbltotalP.setFont(new Font("굴림", Font.BOLD, 20));
		lbltotalP.setHorizontalAlignment(SwingConstants.RIGHT);
		lbltotalP.setBounds(224, 527, 142, 37);
		panel_Cart.add(lbltotalP);


/////////// 장바구니 패널의 실행버튼 컴포넌트 - 뒤돌아가기
		JButton btnClean = new JButton("뒤돌아가기"); // 뒤로가기버튼 클릭 시, 모든 값 초기화되도록 설정.
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				panel_Cart.setVisible(false);
				panel_Menu.setVisible(true);
				panel_Cart.setVisible(false);

			}

		});
		btnClean.setBounds(59, 600, 97, 23);
		panel_Cart.add(btnClean);

		/////////// 장바구니 패널의 실행버튼 컴포넌트 - 결제하기
		JButton btnconfirm = new JButton("결제하기");
		btnconfirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { // 결제 버튼 클릭 시, 포장/매장 선택 후 결제. OK 버튼을 누르면 결제한 금액과 함께 안내 메시지 출력.

				if (pmenu.total_price > 0) { // 결제할 금액이 0원보다 커야 결제버튼 활성화되도록 설정.

					String[] st = { "포장", "매장" };
					JOptionPane.showInputDialog(null, "이대로 주문하시겠습니까?\n 포장여부를 체크해주세요.", "결제완료",
							JOptionPane.QUESTION_MESSAGE, null, st, "포장");

					JOptionPane.showMessageDialog(null, "결제 완료 되었습니다.\n" + "결제 금액은 " + pmenu.total_price + " 원 입니다.");
					for(int i=0;i<6;i++) {
						btnString[i]="";
//						ptopping.c_count = 0;
//						ptopping.h_count = 0;
						
						pmenu.spinner[i].setValue(0);
						pmenu.total_price = 0;
						ptopping.side_total = 0;
//						ptopping.count1 = 0;
//						ptopping.count2 = 0;
					}
					textArea.setText("제품명\t\t제품단가\t수량\n\n");
					panel_Cart.setVisible(false);
					panel_Menu.setVisible(true);
					panel_Cart.setVisible(false);
				} else { // 결제할 금액이 0원인경우, 결제하기 버튼이 클릭되지 않도록 설정.
					JOptionPane.showMessageDialog(null, "결제할 내용이 없습니다.");
				}

			}
		});
		btnconfirm.setBounds(299, 600, 97, 23); // btnClean.setBounds(59, 600, 97, 23); 뒤돌아가기
		panel_Cart.add(btnconfirm);
		
		return panel_Cart;
	}
	

}
