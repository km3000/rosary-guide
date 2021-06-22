import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputManage extends KeyAdapter implements MouseListener{

	Window win;
	
	public InputManage(Window window) {
		win = window;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		switch (keyCode) {
			case KeyEvent.VK_F:
				if(e.isControlDown()) {
					win.fullscreen();
				}
				break;
			case KeyEvent.VK_ESCAPE:
				if(win.isFull) {
					win.fullscreen();
				}
				break;
			case KeyEvent.VK_G:
				win.toggleMenuBar();
				break;
			case KeyEvent.VK_U:
				win.main.setNumber(10);
				break;
			case KeyEvent.VK_RIGHT:
				win.main.add();
				break;
			case KeyEvent.VK_LEFT:
				win.main.subtract();
				break;
			case KeyEvent.VK_ENTER:
				win.main.add();
				break;
			case KeyEvent.VK_SPACE:
				win.main.add();
				break;
			case KeyEvent.VK_BACK_SPACE:
				win.main.subtract();
				break;
			default: break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int mouseCode = e.getButton();
		switch (mouseCode) {
			case MouseEvent.MOUSE_PRESSED:
				if (!win.getJMenuBar().isVisible()) {
					win.toggleMenuBar();
				}
				break;
			default: break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
