package partD.gui;

import java.io.Serializable;
import java.time.LocalDate;

//D2JTableList 에서 사용할 클래스
//Serializable 인터페이스 사용 - 객체의 직렬화가 가능하도록 함.
public class Word implements Serializable {
	
	//Serializable 인터페이스를 구현했을때 필요한 상수
	//Serializable 에서 권장하는 상수 선언 (없으면 경고)
	private static final long serialVersionUID = 1L;
	
	private String english;
	private String korean;
	private int level;    
	private LocalDate wday;    
	
	//생성자
	public Word() { }
	
	public Word(String english, String korean, int level, LocalDate wday) {
		super();
		this.english = english;
		this.korean = korean;
		this.level = level;
		this.wday = wday;
	}

	
	//getter,setter
	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getKorean() {
		return korean;
	}

	public void setKorean(String korean) {
		this.korean = korean;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public LocalDate getWday() {
		return wday;
	}

	public void setWday(LocalDate wday) {
		this.wday = wday;
	}
	
	
	
	
}
