package partD.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import koreait.jdbc.day05.Check_SalesDAO;
import koreait.jdbc.day05.Check_SalesDTO;


@SuppressWarnings("serial")
public class D3JTableListEx extends JFrame{
	
	DefaultTableModel dm;
	JTable jt;
	String[] cols = {"회원 번호","회원 성명","등급","매출 금액"};
	
	//DAO 클래스에 싱글톤 생성해놨으므로 싱글톤 객체 불러오기
	Check_SalesDAO cDao = Check_SalesDAO.getCheck_SalesDAO();
	
	public D3JTableListEx() throws SQLException{
		//프레임이 닫힐 때 프로그램이 종료되도록 설정하는 메소드
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// ,(쉼표)로 숫자 구분 - DecimalFormat 
		DecimalFormat df = new DecimalFormat("###,###,###,###");
		
		//테이블 컴포넌트에 표시될 데이터를 리스트에 저장
		List<Check_SalesDTO> list = cDao.selectAll();	//싱글톤 객체 사용
		String find = "";
		
		//테이블 컴포넌트 생성 : DefaultTableModel 객체 생성 후 JTable 생성자 인자로 전달
		dm = new DefaultTableModel(null,cols);
		jt = new JTable(dm);
		
		String[] data = new String[4];
		
		//리스트에 저장된 데이터를 행으로 추가하기 - 검색기능 관련된 flag 변수는 참고하세요.
			//▶ 데이터는 배열로 전달해야 합니다.
		for (int i = 0; i < list.size(); i++) {
			Check_SalesDTO temp = list.get(i);
			boolean flag;
			if(find.equals("")) flag = true;
			else flag = temp.getCustname().equals(find);
			
			//String.valueOf(인자) : 인자가 기본형 타입만 가능. 기본형 타입 데이터를 문자열로 변환
									//▶ boolean, char, short, int, long, folat, double
			//toString() 메소드는 객체를 대표하는 문자열. 대부분은 재정의를 해서 사용.
			if (flag) {
				data[0] = String.valueOf(temp.getCustno());
				data[1] = temp.getCustname();
				data[2] = temp.getGrade();
				data[3] = String.valueOf(df.format(temp.getSales()));
				
				dm.addRow(data);
			}//if end
		}//for end
		
		Container ctn = getContentPane();
		JScrollPane jsp = new JScrollPane(jt);
		
		JLabel la1 = new JLabel("검색할 고객");
		la1.setBounds(10,30,100,30);
		
		JTextField jtf1 = new JTextField();
		jtf1.setBounds(120,10,200,30);
		
		String[] temp = {"회원 번호","회원 성명","등급"};
		JComboBox<String> jc = new JComboBox<>(temp);
		jc.setBounds(330,10,100,30);
		
		JButton btn = new JButton("단어 검색");
		btn.setBounds(440,10,100,30);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dm = new DefaultTableModel(null,cols);
				jt.setModel(dm);
				for (int i = dm.getRowCount()-1; i >=0; i--) {
					dm.removeRow(i);
				}
				
				String find = jtf1.getText();
				if(jc.getSelectedIndex() == 0) {
					for (int i = 0; i < list.size(); i++) {
						Check_SalesDTO temp = list.get(i);
					
						if(String.valueOf(temp.getCustno()).contains(find)) {
							data[0] = String.valueOf(temp.getCustno());
							data[1] = temp.getCustname();
							data[2] = temp.getGrade();
							data[3] = String.valueOf(df.format(temp.getSales()));
						
							dm.addRow(data);
						}//if Custno end
					}//for end
				}//if end
				else if(jc.getSelectedIndex() == 1) {
					for (int i = 0; i < list.size(); i++) {
						Check_SalesDTO temp = list.get(i);
					
						if(temp.getCustname().contains(find)) {
							data[0] = String.valueOf(temp.getCustno());
							data[1] = temp.getCustname();
							data[2] = temp.getGrade();
							data[3] = String.valueOf(df.format(temp.getSales()));
						
							dm.addRow(data);
						}//if Custno end
					}//for end
				}//else if end
				else {
					for (int i = 0; i < list.size(); i++) {
						Check_SalesDTO temp = list.get(i);
					
						if(temp.getGrade().contains(find)) {
							data[0] = String.valueOf(temp.getCustno());
							data[1] = temp.getCustname();
							data[2] = temp.getGrade();
							data[3] = String.valueOf(df.format(temp.getSales()));
						
							dm.addRow(data);
						}//if Custno end
					}//for end
				}//else end
			}//override end
		});//메소드 end
		
		jsp.setBounds(10,60,550,500);
		
		ctn.setLayout(null);
		ctn.add(btn);
		ctn.add(la1);
		ctn.add(jtf1);
		ctn.add(jc);
		ctn.add(jsp);
		
		pack();
		
		setBounds(500,50,600,600);
		setTitle("고객 매출");
		setVisible(true);
		
	}//생성자 end
	
	public static void main(String[] args) throws SQLException {
		new D3JTableListEx();
	}
	
}//class end
