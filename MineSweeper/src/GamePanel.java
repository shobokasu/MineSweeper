import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Time;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class GamePanel extends JPanel implements MouseListener{
	
	private MainFrame _mainFrame;


	public GamePanel(MainFrame mainFrame) {
		super();
		_mainFrame = mainFrame;
		addMouseListener(this);
		setSize(MainFrame.GAME_SIDE_LENGTH_X, MainFrame.GAME_SIDE_LENGTH_Y);
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
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	static int i = 0;
	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}
