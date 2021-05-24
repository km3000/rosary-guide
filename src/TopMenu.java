import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JRadioButtonMenuItem;
import javax.swing.JEditorPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

//Class for the top menu bar
public class TopMenu extends JMenuBar implements ActionListener{
	
	Window win;
	
	JMenu control;
		JMenu newStuff;
			JMenuItem joyful;
			JMenuItem sorrowful;
			JMenuItem glorious;
		JMenu language;
			JRadioButtonMenuItem english;
			JRadioButtonMenuItem latin;
		JMenuItem exit;
	JMenu view;
		JMenu changeSize;
			JMenuItem x800x600;
			JMenuItem x1024x600;
			JMenuItem x1280x720;
			JMenuItem x1360x768;
			JMenuItem x1600x900;
			JMenuItem x1920x1080;
			JMenuItem maximize;
		JMenuItem fullscreen;
		JMenuItem visible;
	JMenu help;
		JMenuItem manual, about;

	
	public TopMenu (Window window) {
		win = window;
		
		control = new JMenu("Control");
		newStuff = new JMenu("New");
			joyful = new JMenuItem("Joyful Mysteries");
				joyful.addActionListener(this);
			sorrowful = new JMenuItem("Sorrowful Mysteries");
				sorrowful.addActionListener(this);
			glorious = new JMenuItem("Glorious Mysteries");
				glorious.addActionListener(this);
			newStuff.add(joyful);
			newStuff.add(sorrowful);
			newStuff.add(glorious);
		language = new JMenu("Language");
			english = new JRadioButtonMenuItem("English");
				english.addActionListener(this);
			latin = new JRadioButtonMenuItem("Latin");
				latin.addActionListener(this);
			english.setSelected(true);
			language.add(english);
			language.add(latin);
		exit = new JMenuItem("Quit Program");
			exit.addActionListener(this);
		control.add(newStuff);
		control.add(language);
		control.add(exit);
		
		view = new JMenu("View");
		changeSize = new JMenu("Change Size");
			x800x600 = new JMenuItem("800x600");
				x800x600.addActionListener(this);
			x1024x600 = new JMenuItem("1024x600");
				x1024x600.addActionListener(this);
			x1280x720 = new JMenuItem("1280x720");
				x1280x720.addActionListener(this);
			x1360x768 = new JMenuItem("1360x768");
				x1360x768.addActionListener(this);
			x1600x900 = new JMenuItem("1600x900");
				x1600x900.addActionListener(this);
			x1920x1080 = new JMenuItem("1920x1080");
				x1920x1080.addActionListener(this);
			maximize = new JMenuItem("Maximize");
				maximize.addActionListener(this);
			changeSize.add(x800x600);
			changeSize.add(x1024x600);
			changeSize.add(x1280x720);
			changeSize.add(x1360x768);
			changeSize.add(x1600x900);
			changeSize.add(x1920x1080);
			changeSize.add(maximize);
		fullscreen = new JMenuItem("Toggle Fullscreen (Ctrl + F)");
			fullscreen.addActionListener(this);
		visible = new JMenuItem("Toggle Menu Bar Visibility (G)");
			visible.addActionListener(this);
		view.add(changeSize);
		view.add(fullscreen);
		view.add(visible);
		
		help = new JMenu("Help");
		manual = new JMenuItem("Manual");
			manual.addActionListener(this);
		about = new JMenuItem("About");
			about.addActionListener(this);
		help.add(manual);
		help.add(about);
		
		this.add(control);
		this.add(view);
		this.add(help);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(joyful)) {
			win.main.setMysteries("joyful");
			win.main.startGame();
		}
		if(e.getSource().equals(sorrowful)) {
			win.main.setMysteries("sorrowful");
			win.main.startGame();
		}
		if(e.getSource().equals(glorious)) {
			win.main.setMysteries("glorious");
			win.main.startGame();
		}
		if(e.getSource().equals(english)) {
			win.main.setLanguage("en");
		}
		if(e.getSource().equals(latin)) {
			win.main.setLanguage("la");
		}
		if(e.getSource().equals(exit)) {
			System.exit(0);
		}
		if(e.getSource().equals(x800x600) || 
				e.getSource().equals(x1024x600) || 
				e.getSource().equals(x1280x720) || 
				e.getSource().equals(x1360x768) || 
				e.getSource().equals(x1600x900) || 
				e.getSource().equals(x1920x1080) || 
				e.getSource().equals(maximize)) {
			win.changeSize(((JMenuItem) e.getSource()).getText());
		}
		if(e.getSource().equals(fullscreen)) {
			win.fullscreen();
		}
		if(e.getSource().equals(visible)) {
			win.toggleMenuBar();
		}
		if(e.getSource().equals(about)) {
			JEditorPane ep = new JEditorPane("text/html", "<html>"
					+ "<body>"
					+ "Rosary Guide<br>"
					+ "Version "+Main.VERSION+"<br>"
					+ "<a href=\"https://github.com/km3000/rosary-guide\">"
					+ "https://github.com/km3000/rosary-guide</a>"
					+ "</body>"
					+ "</html>");
			ep.addHyperlinkListener(new HyperlinkListener() {
		          @Override
		          public void hyperlinkUpdate(HyperlinkEvent e) {
		              if (e.getEventType().equals(HyperlinkEvent.EventType.ACTIVATED)) {
		            	  try {
							Desktop.getDesktop().browse(new URI("https://github.com/km3000/rosary-guide"));
						} catch (IOException | URISyntaxException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		              }
		          }
		         
		      });
			ep.setEditable(false);
			ep.setBackground(UIManager.getColor ( "Panel.background" ));
			JOptionPane.showMessageDialog(win, ep, "About", JOptionPane.PLAIN_MESSAGE);
		}
	}
}
