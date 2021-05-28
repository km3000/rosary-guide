import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Window extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
	JPanel mainPanel;
	GridBagLayout gridbag;
	GridBagConstraints gbc;
	BufferedImage rosary;
	BufferedImage mystery;
	BufferedImage person;
	ImageIcon rosaryIcon;
	ImageIcon mysteryIcon;
	ImageIcon personIcon;
	JLabel rosaryLabel;
	JLabel mysteryLabel;
	JLabel personLabel;
	JTextArea prayerText;
	
	
	public Window(Main e) {
		main = e;
		initWindow();
		inputter = new InputManage(this);
		this.addKeyListener(inputter);
		topBar = new JLabel("Click Control > New > ... to Begin");
			topBar.setHorizontalAlignment(JLabel.CENTER);
			topBar.setForeground(Color.white);
			topBar.setFont(new Font("Times New Roman", Font.PLAIN, 30));
			this.add(topBar, BorderLayout.NORTH);
		bottomBar = new JLabel();
			bottomBar.setHorizontalAlignment(JLabel.CENTER);
			bottomBar.setForeground(Color.white);
			bottomBar.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			this.add(bottomBar, BorderLayout.SOUTH);
		dedication = new JLabel("Dedicated to Our Lady of Fatima");
			dedication.setForeground(Color.white);
			this.add(dedication, BorderLayout.SOUTH);
		prayerText = new JTextArea();
			prayerText.setBackground(null);
			prayerText.setForeground(Color.white);
			prayerText.setFocusable(false);
			prayerText.setEditable(false);
			prayerText.setLineWrap(true);
			prayerText.setWrapStyleWord(true);
		initImages();
	}
	
	//Sets up the JFrame
	private void initWindow() {
		this.setTitle("Rosary Guide");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon icon = new ImageIcon("icon.png");
		this.setIconImage(icon.getImage());
		this.setSize(800,600);
		//this.setResizable(false);
		this.setResizable(true);
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
		prayerText.setBounds(this.getWidth()/2 - 350, this.getHeight() - 250, 700, 150);
		this.add(bottomBar, BorderLayout.SOUTH);
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
		prayerText.setBounds(this.getWidth()/2 - 350, this.getHeight() - 250, 700, 150);
		this.add(bottomBar, BorderLayout.SOUTH);
		this.add(dedication, BorderLayout.SOUTH);
		mainPanel.repaint();
		mainPanel.revalidate();
		this.repaint();
		this.revalidate(); 
	}
	
	public void toggleMenuBar () {
		if(this.getJMenuBar().isVisible()) {
			this.getJMenuBar().setVisible(false);
		}
		else {
			this.getJMenuBar().setVisible(true);
		}
	}
	
	//Instantiates several Swing objects and creates the general layout of the content GUI
	public void initImages () {
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.darkGray);
		gridbag = new GridBagLayout();
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.insets = new Insets(50, 50, 50, 50);
		gridbag.setConstraints(mainPanel, gbc);
		mainPanel.setLayout(gridbag);
		rosaryIcon = new ImageIcon();
		mysteryIcon = new ImageIcon();
		personIcon = new ImageIcon();
		rosaryLabel = new JLabel();
		mysteryLabel = new JLabel();
		personLabel = new JLabel();
		gbc.gridx = 0;
		gbc.gridy = 0;
		mainPanel.add(rosaryLabel, gbc);
		gbc.gridx = 1;
		mainPanel.add(mysteryLabel, gbc);
		gbc.gridx = 2;
		mainPanel.add(personLabel, gbc);
		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 1;
		mainPanel.add(prayerText, gbc);
		this.add(mainPanel);
	}
	
	//This method refreshes the images displayed according to what the variables in the Main class say should be displayed
	public void loadImages () {
		double width = this.getWidth()/4.0 - 50;
		setBottomBarText();
		this.add(bottomBar, BorderLayout.SOUTH);
		if(main.getNumber() < 7) {
			main.setMystery(0);
			loadImage(rosary, rosaryIcon, rosaryLabel, "beads/1.png", width);
			erasePic(mystery, mysteryIcon, mysteryLabel);
		}
		if(main.getNumber() >= 7 && main.getNumber() < 20) {
			main.setMystery(1);
			loadImage(rosary, rosaryIcon, rosaryLabel, "beads/2.png", width);
			loadImage(mystery, mysteryIcon, mysteryLabel, main.getMysteries()+"/"+Mysteries.getMystery(main.getMysteries(), main.getMystery())+"/2.jpg", width);
		}
		if(main.getNumber() >= 20 && main.getNumber() < 33) {
			main.setMystery(2);
			loadImage(rosary, rosaryIcon, rosaryLabel, "beads/3.png", width);
			loadImage(mystery, mysteryIcon, mysteryLabel, main.getMysteries()+"/"+Mysteries.getMystery(main.getMysteries(), main.getMystery())+"/1.jpg", width);
		}
		if(main.getNumber() >= 33 && main.getNumber() < 46) {
			main.setMystery(3);
			loadImage(rosary, rosaryIcon, rosaryLabel, "beads/4.png", width);
			loadImage(mystery, mysteryIcon, mysteryLabel, main.getMysteries()+"/"+Mysteries.getMystery(main.getMysteries(), main.getMystery())+"/1.jpg", width);
		}
		if(main.getNumber() >= 46 && main.getNumber() < 59) {
			main.setMystery(4);
			loadImage(rosary, rosaryIcon, rosaryLabel, "beads/5.png", width);
			loadImage(mystery, mysteryIcon, mysteryLabel, main.getMysteries()+"/"+Mysteries.getMystery(main.getMysteries(), main.getMystery())+"/1.jpg", width);
		}
		if(main.getNumber() >= 59 && main.getNumber() < 72) {
			main.setMystery(5);
			loadImage(rosary, rosaryIcon, rosaryLabel, "beads/6.png", width);
			loadImage(mystery, mysteryIcon, mysteryLabel, main.getMysteries()+"/"+Mysteries.getMystery(main.getMysteries(), main.getMystery())+"/1.jpg", width);
		}
		if(main.getNumber() == 72) {
			main.setMystery(0);
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
		prayerText.setText(main.getPrayerText());
		prayerText.setSize(700, 150);
		mainPanel.repaint();
		mainPanel.revalidate();
		this.repaint();
		this.revalidate();
		this.setVisible(true);
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
	}
	
	public void setBottomBarText () {
		bottomBar.setText(String.valueOf(main.getNumber()) + "/72");
	}
	
	public void erasePic (BufferedImage buf, ImageIcon ico, JLabel jlab) {
		jlab.setVisible(false);
	}
	
}