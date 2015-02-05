import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseListener{
	private MainFrame _mainFrame;

	public GamePanel(MainFrame mainFrame) {
		super();
		addMouseListener(this);
		_mainFrame = mainFrame;
		setSize(MainFrame.GAME_SIDE_LENGTH, MainFrame.GAME_SIDE_LENGTH);
		setLayout(new GridLayout(_mainFrame.getGame().getWidth(), _mainFrame.getGame().getHeight()));
		
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		_mainFrame.getGame().gamePaint(g);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		_mainFrame.getGame().clickedAction(e);
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
