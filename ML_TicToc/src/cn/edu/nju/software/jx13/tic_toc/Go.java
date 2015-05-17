package cn.edu.nju.software.jx13.tic_toc;

import java.util.ArrayList;

public class Go {

	public static void main(String[] args) {
		int x = 0;
		int o = 0;
		int draw = 0;
		double len = 0;
		String id = new String();
		for(int i = 0; i < 100000; i ++) {
			id = getXMiddleFirstGameID();
			if(id.endsWith("0")){
				o ++;
			}
			if(id.endsWith("1")) {
				x ++;
			}
			if(id.endsWith("2")) {
				draw ++;
			}
			len += (id.length() - 2);
		}
		System.out.println("**************************");
		System.out.println("X win " + x + " times");
		System.out.println("O win " + o + " times");
		System.out.println(draw + " times draw");
		System.out.println("avg len is " + len/100000.0);
		System.out.println("**************************");
	}
	
	public static Players changeTurn (Players turnHolder) {
		if(turnHolder.equals(Players.O)) {
			return Players.X;
		}else {
			return Players.O;
		}
	}
	
	public static String getXMiddleFirstGameID() {
		//1->X 0->O
		//首位表示先手，后面依次positionID填充，末尾表示胜者，2平局
		String gameID = new String();
		
		ChessBoard chessBoard = new ChessBoard();
		Players turnHolder = Players.X;//human goes first
		if(turnHolder.toString().equals("X")) {//先手
			gameID += "1";
		}else {
			gameID += "0";
		}
		Chess chess;
		while(chessBoard.checkWinner() == Result.TBD) {
			if(chessBoard.getChessNumber() == 0) {
				chess = new Chess(1, 1, turnHolder);//X先手放中间
			}else {
				chess = chessBoard.getRandomNullPosition(turnHolder);
			}
			chessBoard.placeChess(chess);
			System.out.println(chess.getHolder() + " at (" + chess.getX() + ", " + chess.getY() + ")" + " id->" + chess.getPositionID());
			System.out.println(chessBoard.getChessNumber() + " in total");
			gameID += chess.getPositionID();
			chessBoard.printBoard();
			turnHolder = changeTurn(turnHolder);
		}
		
		if(chessBoard.checkWinner().toString().equals("DRAW")) {
			System.out.println("Draw!");
			gameID += "2";//平局
		}else {
			System.out.println("The winner is " + chessBoard.checkWinner());
			if(chessBoard.checkWinner().toString().equals("X")) {
				gameID += "1";//X is winner
			}else {
				gameID += "0";//O is winner
			}
		}
		System.out.println("gameID is " + gameID);
		return gameID;
	}
	
	public static String getRandomGameID() {
		//1->X 0->O
		//首位表示先手，后面依次positionID填充，末尾表示胜者，2平局
		String gameID = new String();
		
		ChessBoard chessBoard = new ChessBoard();
		Players turnHolder = Players.X;//human goes first
		if(turnHolder.toString().equals("X")) {//先手
			gameID += "1";
		}else {
			gameID += "0";
		}
		while(chessBoard.checkWinner() == Result.TBD) {
			Chess chess = chessBoard.getRandomNullPosition(turnHolder);
			chessBoard.placeChess(chess);
			System.out.println(chess.getHolder() + " at (" + chess.getX() + ", " + chess.getY() + ")" + " id->" + chess.getPositionID());
			System.out.println(chessBoard.getChessNumber() + " in total");
			gameID += chess.getPositionID();
			chessBoard.printBoard();
			turnHolder = changeTurn(turnHolder);
		}
		
		if(chessBoard.checkWinner().toString().equals("DRAW")) {
			System.out.println("Draw!");
			gameID += "2";//平局
		}else {
			System.out.println("The winner is " + chessBoard.checkWinner());
			if(chessBoard.checkWinner().toString().equals("X")) {
				gameID += "1";//X is winner
			}else {
				gameID += "0";//O is winner
			}
		}
		System.out.println("gameID is " + gameID);
		return gameID;
	}
	
	public static ArrayList<String> getPermutation(int upper) {//getPermutation(9).size() == 362880
		ArrayList<String> result = new ArrayList<String>();
		if(upper == 1) {
			result.add("1");
			return result;
		}else {
			ArrayList<String> previousList = getPermutation(upper - 1);
			for(String previousSingle: previousList) {
				for(int i = 0; i <= previousSingle.length(); i ++) {
					StringBuffer previousSingleBuffer = new StringBuffer(previousSingle);
					result.add(previousSingleBuffer.insert(i, upper).toString());
				}
			}
			return result;
		}
	}

}
