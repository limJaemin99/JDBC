package partD.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class JFrameTest1 extends JFrame {
	//https://e-you.tistory.com/227 블로그 참조

	/*
	창 크기 지정 - setSize(int width, int height);
	실행했을때 창 위치 지정 - setLocation(int x, int y);
	사용자가 프레임을 닫을때 수행할 작업을 설정 - setDefaultCloseOperation(int operation);
	레이아웃 관리자를 설정 - setLayout(LayoutManager manager);
	매개변수 b의 값에 따라 이 창을 표시/숨김 - setVisible(boolean b);
	
	버튼 객체 - JButton
	프레임에 추가 - add
	*/
	
	//생성자
	public JFrameTest1() {
		setSize(800,500);
		setLocation(100,100);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//FlowLayout 객체 전달하기
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		//버튼을 만들어서
		JButton btn1 = new JButton("버튼1");		
		JButton btn2 = new JButton("버튼2");		
		JButton btn3 = new JButton("버튼3");		
		
		//프레임에 추가하기
		add(btn1);
		add(btn2);
		add(btn3);
		
		setVisible(true);
	}
	
	//run 했을 때 실행순서가 시작되는 main 메소드
	public static void main(String[] args) {
		new JFrameTest1();
	}
	
}//class end
