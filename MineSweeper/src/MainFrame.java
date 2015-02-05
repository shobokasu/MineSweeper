import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	
	private MineSweeper _game;
	private StatePanel _statePanel;
	private JPanel _mainPanel;
	private GamePanel _gamePanel;
	
	public final static int CELL_LENGTH = 30;
	public final static int CELL_AMOUNT = 10;
	public final static int BOMM_AMOUNT = 10;
	public final static int GAME_SIDE_LENGTH = CELL_AMOUNT * CELL_LENGTH;
	public MainFrame() {
		super();
		setSize(GAME_SIDE_LENGTH + 50, GAME_SIDE_LENGTH + 200);
		
		_mainPanel = new JPanel();
		_mainPanel.setLayout(new BorderLayout());
		add(_mainPanel);
		
		_game = new MineSweeper(CELL_AMOUNT, CELL_AMOUNT, BOMM_AMOUNT);

		_statePanel = new StatePanel();
		_gamePanel = new GamePanel(this);

		_mainPanel.add(_statePanel, BorderLayout.NORTH);
		_mainPanel.add(_gamePanel, BorderLayout.CENTER);
		setVisible(true);
		
	}
	public static void main(String[] args) {
		new MainFrame();
	}
	
	public MineSweeper getGame(){
		return _game;
	}

}
