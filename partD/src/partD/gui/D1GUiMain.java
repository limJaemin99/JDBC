package partD.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

//								   구버전 <------> 신버전
//자바에서 지원하는 그래픽 UI 라이브러리 : awt ▶ swing ▶ javaFX

//필수적인것은 JFrame 을 상속하여 윈도우를 준비
//	▶ ActionListener 인터페이스를 구현하여 사용자가 일으키는 동작(이벤트)을 처리하는 메소드를 재정의

//이벤트 중심의 동작이므로 반복문(while,for,...)을 사용하지 않아도 윈도우가 종료될때까지 자동으로 반복됨
public class D1GUiMain extends JFrame implements ActionListener{
	
	//serializable 인터페이스를 구현했을때 필요한 상수
	private static final long serialVersionUID = 1L;

	//윈도우를 구성하는  컴포넌트
	JTextField tf1,tf2,tf3;
	JButton b1,b2,b3,b4;
	JLabel la1,la2,la3,la4,la5;
	JList<String> jlt;
	
	String str5; 
	
	//생성자 : 컴포넌트의 크기, 위치 등 필요한 속성을 정의하고 프레임에 추가
	public D1GUiMain() {
		
		//컴포넌트 배치를 위해서 [레이아웃 관리자]를 사용할 수 있으나, 여기서는 [레이아웃] 없이 모든 컴포넌트를 좌표로 지정.
							// ex) window builder
		la1 = new JLabel("English ");
		la1.setBounds(50, 50, 100, 20);   //윈도우 안에서의 좌표와 크기   x위치, y위치, 너비, 높이
		
		tf1 = new JTextField();
		tf1.setBounds(150, 50, 150, 20);

		la2 = new JLabel("Korean");
		la2.setBounds(50, 100, 100, 20);
		
		tf2 = new JTextField();
		tf2.setBounds(150, 100, 150, 20);
		
		la3 = new JLabel("검색어");
		la3.setBounds(50, 150, 100, 20);
		
		tf3 = new JTextField();
		tf3.setBounds(150, 150, 150, 20);
		//tf3.setEditable(false);   //텍스트 필드 사용자 편집 불가
		
		b1 = new JButton("단어추가");
		b1.setBounds(50, 200, 90, 40);

		b2 = new JButton("파일저장");
		b2.setBounds(150, 200, 90, 40);
		
		b3 = new JButton("단어검색");
		b3.setBounds(250, 200, 90,40);
		
		b4 = new JButton("종료");
		b4.setBounds(350, 200, 90, 40);
		
		b1.addActionListener(this); //버튼 클릭(*이벤트)시 실행되도록 사용자 Action 검사
		b2.addActionListener(this);		//(버튼 클릭 이벤트에 대해 실행할 내용을 정의한 구현체 지정)
		b3.addActionListener(this);			//*이벤트 : 사용자가 일으키는 동작
		b4.addActionListener(this);

		la5 = new JLabel();
		la5.setBounds(50, 250, 400, 20);
		
		la4 = new JLabel("레벨");
		la4.setBounds(350, 50, 100, 20);
		//레벨 리스트 생성 - 내용생성
		DefaultListModel<String> l1 = new DefaultListModel<>();  
        l1.addElement("easy");  
        l1.addElement("so so");  
        l1.addElement("difficult");  
        // 레벨 리스트 내용의 컴포넌트 생성
        jlt = new JList<>(l1);  
        jlt.setBounds(350,70, 75,75);  
        
        //메소드에 객체가 지정되지 않은것은 부모인 JFrame 의 메소드를 실행
        //add : 프레임에 컴포넌트 추가하기
        add(jlt);  
		add(la1);add(la2);add(la3);add(la4);add(la5);
		add(tf1);add(tf2);add(tf3);
		add(b1);add(b2);add(b3);add(b4);
		
		//setXXX : 프레임의 속성 설정(레이아웃, 좌표, 크기)
		setLayout(null);		//레이아웃 사용안하고 좌표로만
		setSize(500,350);       //윈도우 사이즈. setBounds 외에 크기만 설정할 때 사용하는 메소드
											//위치 좌표는 기본 x=0 , y=0
		setResizable(false);	//윈도우 크기조절 불가
		setVisible(true);		//윈도우 화면 표시를 위해 꼭 필요
		//////////////////////////////////////////////////////
	}
	
	
	
	public static void main(String[] args) {
		new D1GUiMain();   //메인메소드 실행내용 - 자기 자신 클래스 객체를 생성
	}

	
	//b1.addActionListener(this); 로 설정된 버튼에 대해서만 아래 메소드가 동작을 합니다.
	@Override
	public void actionPerformed(ActionEvent e) {  //implements ActionListener 의 추상메소드
							     //  ㄴ 이벤트 : 사용자 동작 (버튼 클릭...) 컴포넌트를 e 변수가 참조
		if(e.getSource()==b1) {  //e 컴포넌트 검사 조건식  //첫번째 버튼
			la5.setText("b1");	//la5 이름의 레이블에 문자열 b1 표시하기
			tf1.setText(jlt.getSelectedValue());
		}else if(e.getSource()==b2) {		//두번째 버튼
			la5.setText(tf2.getText());
			JOptionPane.showMessageDialog(this, "필수 입력내용입니다.");	//알림 상자 표시
		}else if(e.getSource()==b3) {       //세번째 버튼
			JOptionPane.showMessageDialog(this, "필수 입력내용입니다.");
			la5.setForeground(Color.PINK);	//글씨 색상 변경
		}else if(e.getSource()==b4) {       //네번째 버튼
			dispose();				//윈도우 화면 해제
			JOptionPane.showMessageDialog(this, "종료합니다.");
			System.exit(0);        //종료. 0이 아닌 값은 JVM 에게 비정상종료임을 전달.
		}
		
	}
	//문자열 포맷 설정하기
	//String temp = String.format("%-20s %-20s %3s \t%-15s","English","Korean","lev","date");

}//class end
