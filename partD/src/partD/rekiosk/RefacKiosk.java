package partD.rekiosk;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

//리팩토링 : 코드를 객체지향에 맞도록 수정
public class RefacKiosk {
	private JFrame frame;
	
	public RefacKiosk() {
		initialize();
	}
	
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 480, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		ToppingPannel ptopping = new ToppingPannel();
		MenuPanel pmenu = new MenuPanel();
		CartPanel pcart = new CartPanel();
		
		JPanel panel_Topping = ptopping.createPanel(pmenu, pcart);
		panel_Topping.setBounds(0, 0, 474, 477);

		JPanel panel_cart = pcart.createPanel(pmenu,ptopping);
		panel_cart.setBounds(0, 0, 474, 691);

		panel_Topping.setVisible(false);
		panel_Topping.setLayout(null);
		frame.getContentPane().add(panel_Topping);

		panel_cart.setLayout(null);
		panel_cart.setVisible(false);
		frame.getContentPane().add(panel_cart);
		
		JPanel panel_Menu = pmenu.createPanel(ptopping, pcart);
		panel_Menu.setBounds(0, 0, 474, 691);
		frame.getContentPane().add(panel_Menu);
		panel_Menu.setLayout(null);

	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RefacKiosk window = new RefacKiosk();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
