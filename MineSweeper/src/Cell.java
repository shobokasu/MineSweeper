import java.awt.Color;
import java.awt.Graphics;


public class Cell {

	private boolean _discovered;
	private boolean _bomb;
	private boolean _flag;
	
	//ÉZÉãÇÃíÜêSç¿ïW
	private int _x;
	private int _y;
	private int _bombNumber;
	
	public Cell(int x, int y) {
		_x = x;
		_y = y;
		_discovered = false;
		_bomb = false;
	}
	
	public void paint(Graphics g){
		if(isBomb()){
			g.setColor(Color.RED);
		}else{
			g.setColor(Color.BLUE);
		}
		g.fillRect(_x, _y
				, (int)(MainFrame.CELL_LENGTH * 0.9)
				, (int)(MainFrame.CELL_LENGTH * 0.9));
		if(!isBomb()){
			g.setColor(Color.WHITE);
			g.drawString(_bombNumber + "", _x + 6, _y + 15);
		}
		if(!isDiscovered()){
			if(_flag){
				g.setColor(Color.YELLOW);
			}else{
				g.setColor(Color.BLACK);
			}
			g.fillRect(_x, _y
					, (int)(MainFrame.CELL_LENGTH * 0.9)
					, (int)(MainFrame.CELL_LENGTH * 0.9));
		}
	}
	
	public int getBombInt(){
		return isBomb() ? 1 : 0;
	}
	
	public void setDiscovered(boolean discovered){
		_discovered = discovered;
	}
	
	public void setBomb(boolean bomb){
		_bomb = bomb;
	}
	
	public boolean isDiscovered(){
		return _discovered;
	}
	
	public boolean isBomb(){
		return _bomb;
	}
	
	public void setBombNumber(int bombNumber){
		_bombNumber = bombNumber;
	}
	
	public void setFlag(boolean flag){
		_flag = flag;
	}
	
	public boolean getFlag(){
		return _flag;
	}
	
	
}
