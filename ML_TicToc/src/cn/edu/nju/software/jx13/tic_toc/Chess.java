package cn.edu.nju.software.jx13.tic_toc;

public class Chess {
	
	private int x;
	private int y;
	private int positionID;//id=3x+y 3进制(1,2) -> 1*3+2 = 5  range from 0 to 8
	private Players holder;
	
	public Chess (int x, int y, Players holder) {
		this.x = x;
		this.y = y;
		this.holder = holder;
		this.positionID = x * 3 + y;
	}
	
	public Chess (int x, int y) {
		this.x = x;
		this.y = y;
		this.positionID = x * 3 + y;
	}
	
	public Chess (Chess chess) {
		this.x = chess.getX();
		this.y = chess.getY();
		this.holder = chess.getHolder();
		this.positionID = chess.getPositionID();
	}

	public Players getHolder() {
		return holder;
	}
	public void setHolder(Players holder) {
		this.holder = holder;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	public int getPositionID() {
		return positionID;
	}

	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}
	
}
