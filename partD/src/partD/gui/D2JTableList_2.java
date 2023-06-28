package partD.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;


//"이" 라고 검색시 "이" 가 포함된 단어를 전부 출력
@SuppressWarnings("serial")
public class D2JTableList_2 extends JFrame {
	DefaultTableModel dm;
	JTable jt;
	String[] cols = {"English","Korean","Level","Write date"};
	
	public D2JTableList_2() {
		//프레임이 닫힐 때 프로그램이 종료되도록 설정하는 메소드
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//테이블 컴포넌트에 표시될 데이터를 리스트에 저장
		List<Word> list = new ArrayList<>();
		list.add(new Word("kkk", "케이", 1, LocalDate.parse("2021-05-01")));
		list.add(new Word("yyy", "와이", 2, LocalDate.parse("2021-05-01")));
		list.add(new Word("aaa", "에이", 2, LocalDate.parse("2023-06-28")));
		String find="";
		
		//테이블 컴포넌트 생성 : DefaultTableModel 객체 생성 후 JTable 생성자 인자로 전달.
		dm = new DefaultTableModel(null, cols);   //cols는 테이블 제목. 배열로 전달.
		jt = new JTable(dm);
		
		String[] data = new String[4];
		
		//리스트에 저장된 데이터를 테이블 행으로 추가하기 - 검색기능 관련된 flag 변수는 참고하세요.
			//▶ 데이터는 배열로 전달해야 합니다.
		for(int i=0;i<list.size();i++) {
			Word temp = list.get(i);
			boolean flag;
			if(find.equals("")) flag =true;
			else
				flag = temp.getEnglish().equals(find);
			
			if(flag) {
				data[0] = temp.getEnglish();	//Word 객체의 영어를 문자열 배열로 저장
				data[1] = temp.getKorean();		//		"		한글		"
				data[2] = String.valueOf(temp.getLevel());	// " 레벨	"
				data[3] = temp.getWday().toString();	//	"	 날짜	"
				
				dm.addRow(data);		// 테이블 모델 객체에 배열 추가
			}
		}
		
		//Container 는 프레임안에서 다른 컴포넌트들을 그룹화 시키는 역할
			// 다른 Pane 객체들도 포함할 수 있다.
				// 여기서는 JScrollPane 을 포함하는데, 이것은 스크롤 표시하는 역할의 pane 입니다.
				// (단순 컴포넌트가 아닌, 특정한 기능을 갖는 pane 들이 많이 있다)
		Container ctn = getContentPane();   
		JScrollPane jsp = new JScrollPane(jt);  
		//JScrollPane 에 테이블을 담아서 테이블에 내용이 많아지면 스크롤을 표시합니다.
		
		JLabel la1 = new JLabel("검색 단어");
		la1.setBounds(10,10,100,30);

		JTextField jtf1 = new JTextField();
		jtf1.setBounds(120,10,200,30);
		
		String[] temp = {"english","korean"};
		JComboBox<String> jc = new JComboBox<>(temp);
		jc.setBounds(330, 10, 100, 30);
		
		JButton btn = new JButton("단어 검색");
		btn.setBounds(440, 10, 100, 30);
		
		//ActionListener 설정하는 두번째 다른 형태
		//implements 하지 않고 익명 클래스(구현체) 사용
		btn.addActionListener(new ActionListener() {   //ActionListener 익명 구현 클래스
			//ActionListener 익명 구현 클래스 - 필요한 재정의 메소드를 구현
			
			@Override   
			public void actionPerformed(ActionEvent e) {
				dm = new DefaultTableModel(null, cols);
				jt.setModel(dm);
				for(int k=dm.getRowCount()-1;k>=0;k--)
					//테이블에서 기존 데이터 삭제 ▶ 마지막 행부터 삭제
					dm.removeRow(k);
				
				String find = jtf1.getText();
				if(jc.getSelectedIndex() == 0) {
					for(int i=0;i<list.size();i++) {
						Word temp = list.get(i);
						if(temp.getEnglish().contains(find)) {
							data[0] = temp.getEnglish();
							data[1] = temp.getKorean();
							data[2] = String.valueOf(temp.getLevel());	//String.valueOf(기본형타입) 으로 기본형타입 ▶ String 타입으로 변환
							data[3] = temp.getWday().toString();		//toString() 으로 localDate 타입 ▶ String 타입으로 변환
							dm.addRow(data);
						}
					}
				} else {
					for(int i=0;i<list.size();i++) {
						Word temp = list.get(i);
						if(temp.getKorean().contains(find)) {
							data[0] = temp.getEnglish();
							data[1] = temp.getKorean();
							data[2] = String.valueOf(temp.getLevel());
							data[3] = temp.getWday().toString();
							dm.addRow(data);
						}
					}
				}
			}
		});//btn.addActionListener() 메소드 end
		
		//JScrollPane 객체의 좌표(컨테이너 기준)와 크기
		jsp.setBounds(10, 60, 550, 500);
		
		//컨테이너에 담기는 컴포넌트들
		ctn.setLayout(null);
		ctn.add(btn);
		ctn.add(la1);
		ctn.add(jtf1);
		ctn.add(jc);
		ctn.add(jsp);	//JScrollPane도 최상위 컨테이너(ctn)에 담기기
		
		pack();
		
		setBounds(500,50,600,600);	//프레임의 속성들 설정 (프레임의 좌표는 화면 기준)
		setTitle("WordBook List");
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new D2JTableList_2();
	}
}
