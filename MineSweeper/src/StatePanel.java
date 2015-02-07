import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class StatePanel extends JPanel {

	JLabel _label = new JLabel("MineSweeper");
	
	public StatePanel() {
		super();
		setSize(MainFrame.GAME_SIDE_LENGTH_X, 10);
		add(_label);
	}
	
	public void setText(String str){
		_label.setText(str);
	}
}
