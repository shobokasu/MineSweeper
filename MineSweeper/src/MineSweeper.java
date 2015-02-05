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
		_cell = new Cell[getWidth()][getHeight()];
		for(int i = 0; i < getWidth() ; i ++){
			for(int j = 0; j < getHeight(); j++){
				_cell[i][j] = new Cell(i*MainFrame.CELL_LENGTH, j*MainFrame.CELL_LENGTH);
			}
		}
		for(int i = 0; i < MainFrame.BOMM_AMOUNT; i ++){
			final int I = (int) (Math.random() * MainFrame.CELL_AMOUNT);
			final int J = (int) (Math.random() * MainFrame.CELL_AMOUNT);
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
	
	public void clickedAction(MouseEvent e){
		System.out.println(e.getX());
		if(e.getButton() == MouseEvent.BUTTON1){
			_cell[(int)(e.getX() / MainFrame.CELL_LENGTH)][(int)(e.getY() / MainFrame.CELL_LENGTH)].setDiscovered(true);
		}else if(e.getButton() == MouseEvent.BUTTON3){
			if(_cell[(int)(e.getX() / MainFrame.CELL_LENGTH)][(int)(e.getY() / MainFrame.CELL_LENGTH)].getFlag()){
				_cell[(int)(e.getX() / MainFrame.CELL_LENGTH)][(int)(e.getY() / MainFrame.CELL_LENGTH)].setFlag(false);
			}else{
				_cell[(int)(e.getX() / MainFrame.CELL_LENGTH)][(int)(e.getY() / MainFrame.CELL_LENGTH)].setFlag(true);
			}
		}
		for(int i = 0; i < getWidth() ; i ++){
			for(int j = 0; j < getHeight(); j++){
				openAroundCell(i, j);
			}
		}
		judge();
	}
	
	private void openAroundCell(int i, int j){
		if(( i < 0 || i > MainFrame.CELL_AMOUNT - 1) || (j  < 0 || j > MainFrame.CELL_AMOUNT - 1)){
		}else{
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
		if(count == MainFrame.CELL_AMOUNT*MainFrame.CELL_AMOUNT - MainFrame.BOMM_AMOUNT){
			goal();
		}
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
	
	private int getBombInt(int i, int j){
		if(( i < 0 || i > MainFrame.CELL_AMOUNT - 1) || (j  < 0 || j > MainFrame.CELL_AMOUNT - 1)){
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
