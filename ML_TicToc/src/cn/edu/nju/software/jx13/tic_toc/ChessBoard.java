package cn.edu.nju.software.jx13.tic_toc;

import java.util.ArrayList;
import java.util.Random;

public class ChessBoard {
	
	private Chess[][] chessBoard;
	private ArrayList<Chess> nullList;
	private int chessNumber;
	
	public ChessBoard() {
		ArrayList<Chess> tempList = new ArrayList<Chess>();
		Chess[][] tempBoard = new Chess[3][3];
		for (int i = 0; i < 3; i ++) {
			for(int j = 0; j < 3; j ++) {
				Chess chess = new Chess(i ,j);
				tempBoard[i][j] = chess;
				tempList.add(chess);
			}
		}
		chessBoard = tempBoard;
		nullList = tempList;
		chessNumber = 0;
	}
	
	public Result checkWinner() {
		if(chessNumber < 5) {
			return Result.TBD;
		}
		if(((chessBoard[0][0].getHolder() == chessBoard[1][1].getHolder()) && (chessBoard[1][1].getHolder() == chessBoard[2][2].getHolder()) && (chessBoard[1][1].getHolder() != null))
				|| ((chessBoard[0][2].getHolder() == chessBoard[1][1].getHolder()) && (chessBoard[1][1].getHolder() == chessBoard[2][0].getHolder())) && (chessBoard[1][1].getHolder() != null)) {
			return checkThree(chessBoard[1][1].getHolder());
		}
		
		for(int i = 0; i < 3; i ++) {
				if((chessBoard[i][0].getHolder() == chessBoard[i][1].getHolder()) && (chessBoard[i][1].getHolder() == chessBoard[i][2].getHolder()) && (chessBoard[i][0].getHolder() != null))
					return checkThree(chessBoard[i][0].getHolder());
				if((chessBoard[0][i].getHolder()== chessBoard[1][i].getHolder()) && (chessBoard[1][i].getHolder() == chessBoard[2][i].getHolder()) && (chessBoard[0][i].getHolder() != null))
					return checkThree(chessBoard[0][i].getHolder());
		}
		return checkDraw();
	}
	
	public Result checkThree(Players holder) {//由选手判断胜者
		if(holder == null) return null;
		if(holder.equals(Players.O)) return Result.O;
		if(holder.equals(Players.X)) return Result.X;
		return null;
	}
	
	public Result checkDraw() {
		if(this.chessNumber == 9) {
			return Result.DRAW;
		}else return Result.TBD;
	}
	
	public Chess checkGameBall(Players Holder) {//赛点
		Players rivel;
		if(Holder.equals(Result.X)) rivel = Players.O;
		else rivel = Players.X;
		
		return null;//sudo
	}
	
	public boolean placeChess(Chess chess) {
		if(this.chessBoard[chess.getX()][chess.getY()].getHolder() == null) {
			this.chessBoard[chess.getX()][chess.getY()] = chess;
			for(Chess chessNull: nullList) {
				if((chess.getX() == chessNull.getX()) && (chess.getY() == chessNull.getY())) {
					nullList.remove(chessNull);
					break;
				}
			}
			this.chessNumber ++;
			return true;
		}else {
			return false;
		}
	}
	
	public Chess getRandomNullPosition(Players holder) {
		Random ran = new Random();
		int index;
		if(this.nullList.size() == 0) {
			return null;
		}
		if(this.nullList.size() == 1) {
			index = 0;
		}else {
			index = ran.nextInt(this.nullList.size() - 1);
		}
		Chess chess = nullList.get(index);
		Chess chessNull = new Chess(chess);
		chessNull.setHolder(holder);//注意这里是一个引用，直接改变chess的话，ArrayList里面的chess值也会改变！
		return chessNull;
	}
	
	public void printBoard() {
		for (int i = 0; i < 3; i ++) {
			for(int j = 0; j < 3; j ++) {
				if(chessBoard[i][j].getHolder() == null) System.out.print(" ");
				else System.out.print(chessBoard[i][j].getHolder());
			}
			System.out.println();
		}
	}
	
	public ArrayList<Chess> getNullList() {
		return nullList;
	}

	public int getChessNumber() {
		return chessNumber;
	}

	public void setChessNumber(int chessNumber) {
		this.chessNumber = chessNumber;
	}
}
