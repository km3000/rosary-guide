import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Window extends JFrame {
	
	KeyListener inputter;
	boolean isFull = false;
	
	//These variables save the size and position of the frame before entering full screen mode.
	private int returnWidth = 800;
	private int returnHeight = 600;
	private int returnX = 600;
	private int returnY = 600;
	private int returnState = 0;
	
	GraphicsDevice myScreen = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
	Main main;
	
	JLabel topBar;
	JLabel bottomBar;
	JLabel dedication;
	JPanel images;
	JPanel middleContent;
	GridLayout paneBox;
	BoxLayout bigPaneBox;
	BufferedImage rosary;
	BufferedImage mystery;
	BufferedImage person;
	ImageIcon rosaryIcon;
	ImageIcon mysteryIcon;
	ImageIcon personIcon;
	JLabel rosaryLabel;
	JLabel mysteryLabel;
	JLabel personLabel;
	JLabel prayerLabel;
	
	
	public Window(Main e) {
		main = e;
		initWindow();
		inputter = new InputManage(this);
		this.addKeyListener(inputter);
		topBar = new JLabel("Click Control > New > ... to Begin");
			topBar.setHorizontalAlignment(JLabel.CENTER);
			topBar.setForeground(Color.white);
			topBar.setFont(new Font("Times New Roman", Font.PLAIN, 35));
			this.add(topBar, BorderLayout.NORTH);
		bottomBar = new JLabel();
			bottomBar.setHorizontalAlignment(JLabel.CENTER);
			bottomBar.setForeground(Color.white);
			bottomBar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			this.add(bottomBar, BorderLayout.SOUTH);
		dedication = new JLabel("Dedicated to Our Lady of Fatima");
			dedication.setForeground(Color.white);
			//dedication.setHorizontalAlignment(JLabel.LEFT);
			this.add(dedication, BorderLayout.SOUTH);
		prayerLabel = new JLabel();
			prayerLabel.setForeground(Color.white);
			prayerLabel.setSize(this.getWidth(), this.getHeight()/3);
			prayerLabel.setHorizontalAlignment(JLabel.LEFT);
		initImages();
	}
	
	//Sets up the JFrame
	private void initWindow() {
		this.setTitle("Rosary Guide");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("icon.png");
		this.setIconImage(icon.getImage());
		this.setSize(800,600);
		this.setResizable(false);
		this.setFocusable(true);
		this.setLocationRelativeTo(null);
	}
	
	//Call this method to toggle full screen mode
	public void fullscreen () {
		if(isFull) {
			isFull = false;
			myScreen.setFullScreenWindow(null);
			this.setExtendedState(returnState);
			this.setSize(returnWidth, returnHeight);
			this.setLocation(returnX, returnY);
		}
		else {
			if(myScreen.isFullScreenSupported()) {
				isFull = true;
				returnWidth = this.getSize().width;
				returnHeight = this.getSize().height;
				returnX = this.getLocation().x;
				returnY = this.getLocation().y;
				returnState = this.getExtendedState();
				this.setExtendedState(JFrame.MAXIMIZED_BOTH); 
				myScreen.setFullScreenWindow(this);
			}
		}
		if(main.getNumber() > 0) {
			loadImages();
		}
		this.add(dedication, BorderLayout.SOUTH);
	}
	
	//Called when the window size is changed, NOT when full screen is toggled
	public void changeSize (String size) {
		if(isFull) {
			fullscreen();
		}
		switch (size) {
			case "800x600": this.setSize(800, 600);
				break;
			case "1024x600": this.setSize(1024, 600);
				break;
			case "1280x720": this.setSize(1280, 720);
				break;
			case "1360x768": this.setSize(1360, 768);
				break;
			case "1600x900": this.setSize(1600, 900);
				break;
			case "1920x1080": this.setSize(1920, 1080);
				break;
			//case "Maximize": this.setExtendedState(MAXIMIZED_BOTH);
			//	break;
			default: break;
		}
		if(main.getNumber() > 0) {
			loadImages();
		}
		this.add(dedication, BorderLayout.SOUTH);
	}
	
	public void toggleMenuBar () {
		if(this.getJMenuBar().isVisible()) {
			this.getJMenuBar().setVisible(false);
		}
		else {
			this.getJMenuBar().setVisible(true);
		}
	}
	
	//Instantiates several Swing objects
	public void initImages () {
		images = new JPanel();
		middleContent = new JPanel();
		paneBox = new GridLayout(0, 3);
		bigPaneBox = new BoxLayout(middleContent, BoxLayout.PAGE_AXIS);
		images.setLayout(paneBox);
		middleContent.setLayout(bigPaneBox);
		images.setBackground(Color.darkGray);
		middleContent.setBackground(Color.darkGray);
		rosaryIcon = new ImageIcon();
		mysteryIcon = new ImageIcon();
		personIcon = new ImageIcon();
		rosaryLabel = new JLabel();
		mysteryLabel = new JLabel();
		personLabel = new JLabel();
		images.add(rosaryLabel);
		images.add(mysteryLabel);
		images.add(personLabel);
		middleContent.add(images);
		middleContent.add(prayerLabel);
		this.add(middleContent);
	}
	
	//This method refreshes the images displayed according to what the variables in the Main class say should be displayed
	public void loadImages () {
		double width = this.getWidth()/4.0 - 50;
		setBottomBarText();
		this.add(bottomBar, BorderLayout.SOUTH);
		if(main.getNumber() < 7) {
			main.setMystery("null");
			loadImage(rosary, rosaryIcon, rosaryLabel, "beads/1.png", width);
			erasePic(mystery, mysteryIcon, mysteryLabel);
		}
		if(main.getNumber() >= 7 && main.getNumber() < 20) {
			main.setMystery(Mysteries.getMystery(main.getMysteries(), 1));
			loadImage(rosary, rosaryIcon, rosaryLabel, "beads/2.png", width);
			loadImage(mystery, mysteryIcon, mysteryLabel, main.getMysteries()+"/"+main.getMystery()+"/2.jpg", width);
		}
		if(main.getNumber() >= 20 && main.getNumber() < 33) {
			main.setMystery(Mysteries.getMystery(main.getMysteries(), 2));
			loadImage(rosary, rosaryIcon, rosaryLabel, "beads/3.png", width);
			loadImage(mystery, mysteryIcon, mysteryLabel, main.getMysteries()+"/"+main.getMystery()+"/1.jpg", width);
		}
		if(main.getNumber() >= 33 && main.getNumber() < 46) {
			main.setMystery(Mysteries.getMystery(main.getMysteries(), 3));
			loadImage(rosary, rosaryIcon, rosaryLabel, "beads/4.png", width);
			loadImage(mystery, mysteryIcon, mysteryLabel, main.getMysteries()+"/"+main.getMystery()+"/1.jpg", width);
		}
		if(main.getNumber() >= 46 && main.getNumber() < 59) {
			main.setMystery(Mysteries.getMystery(main.getMysteries(), 4));
			loadImage(rosary, rosaryIcon, rosaryLabel, "beads/5.png", width);
			loadImage(mystery, mysteryIcon, mysteryLabel, main.getMysteries()+"/"+main.getMystery()+"/1.jpg", width);
		}
		if(main.getNumber() >= 59 && main.getNumber() < 72) {
			main.setMystery(Mysteries.getMystery(main.getMysteries(), 5));
			loadImage(rosary, rosaryIcon, rosaryLabel, "beads/6.png", width);
			loadImage(mystery, mysteryIcon, mysteryLabel, main.getMysteries()+"/"+main.getMystery()+"/1.jpg", width);
		}
		if(main.getNumber() == 72) {
			main.setMystery("null");
			loadImage(rosary, rosaryIcon, rosaryLabel, "beads/7.png", width);
			erasePic(mystery, mysteryIcon, mysteryLabel);
		}
		if(main.getPerson().equals("Jesus")) {
			loadImage(person, personIcon, personLabel, "christ/1.jpg", width);
		}
		else {
			if (main.getPerson().equals("Mary")) {
				loadImage(person, personIcon, personLabel, "mary/1.jpg", width);
			}
			else {
				if (main.getPerson().equals("Trinity")) {
					loadImage(person, personIcon, personLabel, "trinity/1.jpg", width);
				}
				else {
					erasePic(person, personIcon, personLabel);
				}
			}
		}
		prayerLabel.setText("<html>"+main.getPrayerText()+"</html>");
		prayerLabel.setSize(this.getWidth(), this.getHeight()/3);
		prayerLabel.setAlignmentX(LEFT_ALIGNMENT);
		middleContent.add(prayerLabel);
		this.setVisible(true);
		images.repaint();
		images.revalidate();
		middleContent.repaint();
		middleContent.revalidate();
		this.add(middleContent);
	}
	
	public void loadImage (BufferedImage buf, ImageIcon ico, JLabel jlab, String path, double width) {
		Image img = null;
		try{
			img = ImageIO.read(new File("pics/"+path));
		} catch (IOException e) {
			System.err.println("Error: Picture " + path + " does not exist!");
		}
		int height = (int) (width/img.getWidth(null)*img.getHeight(null));
		buf = new BufferedImage ((int) (width), height, BufferedImage.TRANSLUCENT);
		Graphics g = buf.createGraphics();
		g.drawImage(img, 0, 0, (int) (width), height, this);
		g.dispose();
		ico.setImage(buf);
		jlab.setIcon(ico);
		jlab.setVisible(true);
		images.add(jlab);
	}
	
	public void setBottomBarText () {
		bottomBar.setText(String.valueOf(main.getNumber()) + "/72");
	}
	
	public void erasePic (BufferedImage buf, ImageIcon ico, JLabel jlab) {
		jlab.setVisible(false);
	}
	
}