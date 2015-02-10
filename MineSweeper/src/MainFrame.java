import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	
	private MineSweeper _game;
	private JPanel _mainPanel;
	private StatePanel _statePanel;
	private GamePanel _gamePanel;
	
	public final static int CELL_LENGTH = 20;
	public final static int CELL_AMOUNT_X = 30;
	public final static int CELL_AMOUNT_Y = 20;
	public final static int BOMM_AMOUNT = 50;
	
	public final static int GAME_SIDE_LENGTH_X = CELL_AMOUNT_X * CELL_LENGTH;
	public final static int GAME_SIDE_LENGTH_Y = CELL_AMOUNT_Y * CELL_LENGTH;
	
	
	public MainFrame() {
		super();
		setSize(GAME_SIDE_LENGTH_X + 50, GAME_SIDE_LENGTH_Y + 100);
		
		_mainPanel = new JPanel();
		_mainPanel.setLayout(new BorderLayout());
		add(_mainPanel);
		
		_game = new MineSweeper(this, CELL_AMOUNT_X, CELL_AMOUNT_Y, BOMM_AMOUNT);

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
	
	public StatePanel getStatePanel(){
		return _statePanel;
	}

}
