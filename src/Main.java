import java.awt.Color;

import org.apache.commons.lang3.StringUtils;

public class Main {  
	
	public final static String VERSION = "0.1";
	
	Window win;
	TopMenu topM;
	private int number = 0;
	private String mysteries = "null";
	private int mystery = 0;
	private String person = "null";
	private String language = "en";
	private ImageLoader imgLoad;
	
	public final static int[] CHECKPOINTS = {2, 7, 20, 33, 46, 59, 72};
	public final static int[] JESUSS = {19, 32, 45, 58, 71};
	public final static int[] MARYS = {3, 8, 21, 34, 47, 60, 72};
	public final static int[] TRINITYS = {6, 18, 31, 44, 57, 70};
	
	public Main () {
		win = new Window(this);
			win.getContentPane().setBackground(Color.darkGray);
		topM = new TopMenu(win);
		win.setJMenuBar(topM);
		win.setVisible(true);
	}
	
	public void startGame () {
		number = 1;
		person = findPerson();
		win.loadImages();
		win.topBar.setText(getHeader());
		imgLoad = new ImageLoader(mysteries);
	}
	
	public String getMysteries() {
		return mysteries;
	}

	public void setMysteries(String mysteries) {
		this.mysteries = mysteries;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getPerson () {
		return person;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public void add () {
		if(number > 0 && number < 72) {
			number ++;
			person = findPerson();
			win.setBottomBarText();
			if(arrayContains(CHECKPOINTS, number) || arrayContains(JESUSS, number) || arrayContains(MARYS, number) || arrayContains(TRINITYS, number)) {
				win.loadImages();
			}
			if(arrayContains(CHECKPOINTS, number)) {
				win.topBar.setText(getHeader());
			}
		}
	}
	
	public void subtract () {
		if(number > 1) {
			number --;
			person = findPerson();
			win.loadImages();
			win.topBar.setText(getHeader());
		}
	}
	
	public String findPerson() {
		if(number >= 3 && number < 6) {
			return "Mary";
		}
		if(number >= 8 && number < 18) {
			return "Mary";
		}
		if(number >= 21 && number < 31) {
			return "Mary";
		}
		if(number >= 34 && number < 44) {
			return "Mary";
		}
		if(number >= 47 && number < 57) {
			return "Mary";
		}
		if(number >= 60 && number < 70) {
			return "Mary";
		}
		if(number == 72) {
			return "Mary";
		}
		if(arrayContains(TRINITYS, number)) {
			return "Trinity";
		}
		if(arrayContains(JESUSS, number)) {
			return "Jesus";
		}
		return "null";
	}
	
	public String getHeader () {
		String mysteriesTitle = "The " + StringUtils.capitalize(mysteries) + " Mysteries";
		if(number > 6 && number < 72) {
			mysteriesTitle += ": The " + Mysteries.getFullMystery(mysteries, mystery);
		}
		return mysteriesTitle;
	}
	
	public int getMystery() {
		return mystery;
	}

	public void setMystery(int mystery) {
		this.mystery = mystery;
	}
	
	public String getPrayerText() {
		return Mysteries.getPrayerText("prayers/" + getLanguage() + "_" + Mysteries.getPrayer(getNumber()) + ".txt");
	}

	public ImageLoader getImgLoad() {
		return imgLoad;
	}

	public static boolean arrayContains (int[] array, int value) {
		for (int i: array) {
			if(i == value) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {  
		new Main();
	}
}  