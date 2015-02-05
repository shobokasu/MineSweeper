import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class StatePanel extends JPanel {

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawLine(0, 0, 20, 20);
	}
	
	public StatePanel() {
		super();
		setSize(MainFrame.GAME_SIDE_LENGTH_X, 10);
		add(new JButton("a"));
	}
}
