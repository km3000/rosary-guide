import java.io.File;
import java.util.Random;

public class ImageLoader {
	private String pic1;
	private String pic2;
	private String pic3;
	private String pic4;
	private String pic5;
	
	public ImageLoader (String mysts) {
		//File pp1 = new File("./pics/" + mysts + "/" + Mysteries.getMystery(mysts, 1));
		//File[] pics1 = pp1.listFiles();
		File[] pics1 = new File("pics/" + mysts + "/" + Mysteries.getMystery(mysts, 1)).listFiles();
		pic1 = pics1[new Random().nextInt(pics1.length-1)].getName();
		File[] pics2 = new File("pics/" + mysts + "/" + Mysteries.getMystery(mysts, 2)).listFiles();
		pic2 = pics2[new Random().nextInt(pics2.length-1)].getName();
		File[] pics3 = new File("pics/" + mysts + "/" + Mysteries.getMystery(mysts, 3)).listFiles();
		pic3 = pics3[new Random().nextInt(pics3.length-1)].getName();
		File[] pics4 = new File("pics/" + mysts + "/" + Mysteries.getMystery(mysts, 4)).listFiles();
		pic4 = pics4[new Random().nextInt(pics4.length-1)].getName();
		File[] pics5 = new File("pics/" + mysts + "/" + Mysteries.getMystery(mysts, 5)).listFiles();
		pic5 = pics5[new Random().nextInt(pics5.length-1)].getName();
	}

	public String getPic1() {
		return pic1;
	}

	public String getPic2() {
		return pic2;
	}

	public String getPic3() {
		return pic3;
	}

	public String getPic4() {
		return pic4;
	}

	public String getPic5() {
		return pic5;
	}
	
}
