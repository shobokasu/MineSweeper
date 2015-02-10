import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class MineSweeper {
	
	private int _height;
	private int _width;
	private int _bombAmount;
	private MainFrame _mainFrame;
	private int _turn;
	
	private Cell [][] _cell;
	
	public MineSweeper(MainFrame mainFrame, int width, int height, int bombAmount) {
		setBombAmount(bombAmount);
		setHeight(height);
		setWidth(width);
		_mainFrame = mainFrame;
		initMines();
	}
	
	private void initMines(){
		_turn = 0;
		_cell = new Cell[getWidth()][getHeight()];
		for(int i = 0; i < getWidth() ; i ++){
			for(int j = 0; j < getHeight(); j++){
				final int x = (int)(i * MainFrame.CELL_LENGTH + MainFrame.CELL_LENGTH/ 2);
				final int y = (int)(j * MainFrame.CELL_LENGTH + MainFrame.CELL_LENGTH/ 2);
				_cell[i][j] = new Cell(x, y);
			}
		}
		for(int i = 0; i < MainFrame.BOMM_AMOUNT; i ++){
			final int I = (int) (Math.random() * MainFrame.CELL_AMOUNT_X);
			final int J = (int) (Math.random() * MainFrame.CELL_AMOUNT_Y);
			if(!_cell[I][J].isBomb()){
				_cell[I][J].setBomb(true);
				_cell[I][J].setBombNumber(-99999);
			}else{
				i --;
			}
		}
		for(int i = 0; i < getWidth() ; i ++){
			for(int j = 0; j < getHeight(); j++){
				_cell[i][j].setBombNumber(countAroundBomb(i, j));
			}
		}
	}
	
	private int  getSubscriptX(MouseEvent e){
		return (int)((e.getX() - MainFrame.CELL_LENGTH / 2) / MainFrame.CELL_LENGTH );
	}
	
	private int  getSubscriptY(MouseEvent e){
		return (int)((e.getY() - MainFrame.CELL_LENGTH / 2) / MainFrame.CELL_LENGTH );
	}
	
	public void clickedAction(MouseEvent e){
		final int x = getSubscriptX(e);
		final int y = getSubscriptY(e);
		if(isOutOfBoundX(x) || isOutOfBoundY(y)){
			return;
		}
		if(e.getButton() == MouseEvent.BUTTON1){
			if(!_cell[x][y].isDiscovered()){
				nextTurn();
			}
			openCell(x,y);
			openDiscoveredZeroCell();
		}else if(e.getButton() == MouseEvent.BUTTON2){
			if(_cell[x][y].getBombNumber() == getAmountAroundFlag(x, y)){
				System.out.println("aaa");
				openAroundNonFlagCell(x, y);
			}
		}else if(e.getButton() == MouseEvent.BUTTON3){
			if(_cell[x][y].isFlag()){
				_cell[x][y].setFlag(false);
			}else{
				_cell[x][y].setFlag(true);
			}
		}
		judge();
	}
	
	private void nextTurn(){
		setTurn(getTurn() + 1);
		_mainFrame.getStatePanel().setTextTurn(getTurn());
	}
	
	private void openDiscoveredZeroCell(){
		int count = 0;
		while(count < Math.max(MainFrame.CELL_AMOUNT_X, MainFrame.CELL_AMOUNT_Y)){
			for(int i = 0; i < getWidth() ; i ++){
				for(int j = 0; j < getHeight(); j++){
					if(_cell[i][j].isDiscovered() && _cell[i][j].getBombNumber() == 0){
						openAroundZeroCell(i, j);
					}
				}
			}
			count ++;
		}
	}
	
	private void openAroundZeroCell(int i, int j){
		if(isOutOfBound(i, j)){
			return;
		}
		if(_cell[i][j].getBombNumber() != 0){
			return;
		}
		openAroundCell(i, j);
	}
	
	private void openAroundCell(int i, int j){
		if(isOutOfBound(i, j)){
			return;
		}
		if(_cell[i][j].isBomb()){
			return;
		}
		openCell(i - 1, j - 1);
		openCell(i - 1, j);
		openCell(i - 1, j + 1);
		openCell(i, j - 1);
		openCell(i, j + 1);
		openCell(i + 1, j - 1);
		openCell(i + 1, j);
		openCell(i + 1, j + 1);
	}
	
	private void openAroundNonFlagCell(int i, int j){
		if(isOutOfBound(i, j)){
			return;
		}
		if(_cell[i][j].isBomb()){
			return;
		}
		if(!_cell[i][j].isDiscovered()){
			return;
		}
		openNonFlagCell(i - 1, j - 1);
		openNonFlagCell(i - 1, j);
		openNonFlagCell(i - 1, j + 1);
		openNonFlagCell(i, j - 1);
		openNonFlagCell(i, j + 1);
		openNonFlagCell(i + 1, j - 1);
		openNonFlagCell(i + 1, j);
		openNonFlagCell(i + 1, j + 1);
	}
	
	private void openCell(int i, int j){
		if(isOutOfBound(i, j)){
			return;
		}
		_cell[i][j].setDiscovered(true);
		_cell[i][j].setFlag(false);
	}
	
	private void openNonFlagCell(int i, int j){
		if(isOutOfBound(i, j)){
			return;
		}
		if(_cell[i][j].isFlag()){
			return;
		}
		openCell(i, j);
	}
	
	private void setStatePanelText(String str){
		_mainFrame.getStatePanel().setText(str);
	}
	
	private void judge(){
		int count = 0;
		for(int i = 0; i < getWidth() ; i ++){
			for(int j = 0; j < getHeight(); j++){
				if(_cell[i][j].isBomb() && _cell[i][j].isDiscovered()){
					gameOver();
					return;
				}
				if(_cell[i][j].isDiscovered() && !_cell[i][j].isBomb()){
					count ++;
				}
			}
		}
		if(count == getRightRemainder()){
			goal();
			if(getTurn() == 1){
				initMines();
			}
		}
	}
	
	private int getAmountAroundFlag(int i, int j){
		int result = 0;
		result += getValidFlagInt(i - 1, j - 1);
		result += getValidFlagInt(i - 1, j);
		result += getValidFlagInt(i - 1, j + 1);
		result += getValidFlagInt(i, j - 1);
		result += getValidFlagInt(i, j + 1);
		result += getValidFlagInt(i + 1, j - 1);
		result += getValidFlagInt(i + 1, j);
		result += getValidFlagInt(i + 1, j + 1);
		System.out.println(result);
		return result;
	}
	
	private int getValidFlagInt(int i, int j){
		if(isOutOfBoundX(i) || isOutOfBoundY(j)){
			return 0;
		}else{
			return _cell[i][j].getValidFlagInt();
		}
	}
	
	private int getRightRemainder(){
		return MainFrame.CELL_AMOUNT_X*MainFrame.CELL_AMOUNT_Y - MainFrame.BOMM_AMOUNT;
	}
	
	private void gameOver(){
		setStatePanelText("GAME OVER");
	}
	
	private void goal(){
		setStatePanelText("GOAL");
	}
	
	private int countAroundBomb(int i, int j){
		int result = 0;
		result = getBombInt(i - 1, j - 1) + getBombInt(i - 1, j) + getBombInt(i - 1, j + 1)
					+ getBombInt(i, j -1) + getBombInt(i, j + 1)
					+ getBombInt(i + 1, j - 1) + getBombInt(i + 1, j ) + getBombInt(i + 1, j + 1);
		return result;
	}
	
	private boolean isOutOfBoundX(int i){
		return  i < 0 || MainFrame.CELL_AMOUNT_X - 1 < i;
	}
	
	private boolean isOutOfBoundY(int j){
		return  j < 0 || MainFrame.CELL_AMOUNT_Y - 1 < j;
	}
	
	private boolean isOutOfBound(int i, int j){
		return  isOutOfBoundX(i) || isOutOfBoundY(j);
	}
	
	private int getBombInt(int i, int j){
		if(isOutOfBoundX(i) || isOutOfBoundY(j)){
			return 0;
		}else{
			return _cell[i][j].getBombInt();
		}
	}
	
	public void gamePaint(Graphics g){
		for(int i = 0; i < getWidth() ; i ++){
			for(int j = 0; j < getHeight(); j++){
				_cell[i][j].paint(g);
			}
		}
	}
	
	public void setBombAmount(int bombAmount){
		_bombAmount = bombAmount;
	}
	
	public int getBombAmount(){
		return _bombAmount;
	}
	
	public void setHeight(int height){
		_height = height;
	}
	public void setWidth(int width){
		_width = width;
	}
	public int getHeight(){
		return _height;
	}
	public int getWidth(){
		return _width;
	}
	
	public int getTurn(){
		return _turn;
	}
	
	public void setTurn(int turn){
		_turn = turn;
	}
	
}
