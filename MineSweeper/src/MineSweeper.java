import java.awt.Event;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class MineSweeper {
	
	private int _height;
	private int _width;
	private int _bombAmount;
	
	private Cell [][] _cell;
	
	public MineSweeper(int width, int height, int bombAmount) {
		setBombAmount(bombAmount);
		setHeight(height);
		setWidth(width);
		initMines();
	}
	
	private void initMines(){
		_cell = new Cell[getWidth()][getHeight()];
		for(int i = 0; i < getWidth() ; i ++){
			for(int j = 0; j < getHeight(); j++){
				final int x = (int)(i * MainFrame.CELL_LENGTH + MainFrame.CELL_LENGTH/ 2);
				final int y = (int)(j * MainFrame.CELL_LENGTH + MainFrame.CELL_LENGTH/ 2);
				_cell[i][j] = new Cell(x, y);
				//ƒNƒŠƒbƒN”»’è‚ª‚¸‚ê‚½‚©‚ç’¼‚µ‚Ä‚¨‚­
			}
		}
		for(int i = 0; i < MainFrame.BOMM_AMOUNT; i ++){
			final int I = (int) (Math.random() * MainFrame.CELL_AMOUNT_X);
			final int J = (int) (Math.random() * MainFrame.CELL_AMOUNT_Y);
			if(!_cell[I][J].isBomb()){
				_cell[I][J].setBomb(true);
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
		if(e.getButton() == MouseEvent.BUTTON1){
			_cell[getSubscriptX(e)][getSubscriptY(e)].setDiscovered(true);
		}else if(e.getButton() == MouseEvent.BUTTON3){
			if(_cell[getSubscriptX(e)][getSubscriptY(e)].getFlag()){
				_cell[getSubscriptX(e)][getSubscriptY(e)].setFlag(false);
			}else{
				_cell[getSubscriptX(e)][getSubscriptY(e)].setFlag(true);
			}
		}
		openAroundCell(getSubscriptX(e), getSubscriptY(e), true);
		judge();
	}
	
	private void openAroundCell(int i, int j, boolean flag){
		System.out.println(i + "," + j);
		if(flag){
			openAroundCell(i - 1, j - 1, false);
		}
		if(isOutOfBoundX(i) || isOutOfBoundY(j)){
			return;
		}else if(_cell[i][j].isDiscovered() && _cell[i][j].getBombNumber() != 0){
			return;
		}else if(!_cell[i][j].isDiscovered() && _cell[i][j].getBombNumber() == 0){
			_cell[i][j].setDiscovered(true);
			openAroundCell(i - 1, j - 1, false);
			openAroundCell(i - 1, j, false);
			openAroundCell(i - 1, j + 1, false);
			openAroundCell(i, j - 1, false);
			openAroundCell(i, j + 1, false);
			openAroundCell(i + 1, j - 1, false);
			openAroundCell(i + 1, j , false);
			openAroundCell(i + 1, j + 1, false);
		}
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
		}
	}
	
	private int getRightRemainder(){
		return MainFrame.CELL_AMOUNT_X*MainFrame.CELL_AMOUNT_Y - MainFrame.BOMM_AMOUNT;
	}
	
	private void gameOver(){
		System.out.print("GAMEOVER");
	}
	
	private void goal(){
		System.out.print("GOAL");
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
	
}
