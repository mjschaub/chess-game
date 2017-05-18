package board;
import java.util.ArrayList;

import pieces.Pawship;
import pieces.Piece;
import pieces.King;
import pieces.Queen;
import pieces.Bishop;
import pieces.Knight;
import pieces.Pawn;
import pieces.Rider;
import pieces.Rook;

/**
 * The Board class to design the chess board and layout of the pieces
 */
public class Board 
{
	public Piece boardLayout[][];
	public ArrayList<Piece> wPieces;
	public ArrayList<Piece> bPieces;
	public int whitePieces;
	public int blackPieces;
	King wKing;
	King bKing;
	Piece lastRemovedPiece;
	Piece fakeLastRemovedPiece;
	
	/**
	 * The default constructor to initialize the pieces of the board and set how many there are
	 */
	public Board()
	{
		wPieces = new ArrayList<Piece>();
		bPieces = new ArrayList<Piece>();
		boardLayout = new Piece[8][8];
		boardLayout[0][0] = new Rook(0,0,0);
		boardLayout[0][1] = new Knight(0,0,1);
		boardLayout[0][2] = new Bishop(0,0,2);
		boardLayout[0][3] = new Queen(0,0,3);
		boardLayout[0][4] = new King(0,0,4);
		wKing = (King) boardLayout[0][4];
		boardLayout[0][5] = new Bishop(0,0,5);
		boardLayout[0][6] = new Knight(0,0,6);
		boardLayout[0][7] = new Rook(0,0,7);
		
		for(int i = 0; i < 8; i++)
			wPieces.add(boardLayout[0][i]);
		for(int i = 0; i < 8; i++)
		{
			boardLayout[1][i] = new Pawn(0,1,i);
			wPieces.add(boardLayout[1][i]);
		}
		
		boardLayout[7][0] = new Rook(1,7,0);
		boardLayout[7][1] = new Knight(1,7,1);
		boardLayout[7][2] = new Bishop(1,7,2);
		boardLayout[7][3] = new Queen(1,7,3);
		boardLayout[7][4] = new King(1,7,4);
		bKing = (King) boardLayout[7][4];
		boardLayout[7][5] = new Bishop(1,7,5);
		boardLayout[7][6] = new Knight(1,7,6);
		boardLayout[7][7] = new Rook(1,7,7);
		
		for(int i = 0; i < 8; i++)
			bPieces.add(boardLayout[7][i]);
		for(int i = 0; i < 8; i++)
		{
			boardLayout[6][i] = new Pawn(1,6,i);
			bPieces.add(boardLayout[6][i]);
		}
		
		whitePieces = 18;
		blackPieces = 18;
		lastRemovedPiece = null;
		fakeLastRemovedPiece = null;
		
		//Comment the lines of code below this in the constructor for a normal game without custom pieces
		/*
		boardLayout[3][7] = new Rider(0,3,7);
		boardLayout[3][5] = new Pawship(0,3,5);
		wPieces.add(boardLayout[3][7]);
		wPieces.add(boardLayout[3][5]);
		boardLayout[4][0] = new Rider(1,4,0);
		boardLayout[4][2] = new Pawship(1,4,2);
		bPieces.add(boardLayout[4][0]);
		bPieces.add(boardLayout[4][2]);
		*/
	}
	/**
	 * Removes the piece at the x, y position from the board
	 * @param x the x position
	 * @param y the y position
	 */
	public void removePiece(int x, int y)
	{
		if(boardLayout[x][y] == null)
			return;
		
		if(boardLayout[x][y].getColor() == 0)
		{
			for(int i = 0; i < wPieces.size(); i++)
				if(wPieces.get(i).getX() == x && wPieces.get(i).getY() == y)
					wPieces.remove(i);
		
			whitePieces--;
		}
		else
		{
			for(int i = 0; i < bPieces.size(); i++)
				if(bPieces.get(i).getX() == x && bPieces.get(i).getY() == y)
					bPieces.remove(i);
			blackPieces--;
		}
		
		boardLayout[x][y] = null;
		
	}
	/**
	 * checks if the spot on the board is empty
	 * @param x the x position
	 * @param y the y position
	 * @return true or false depending on if a piece is in this spot
	 */
	public boolean isEmptySpace(int x, int y)
	{
		return boardLayout[x][y] == null;
		
	}
	/**
	 * Checks if a piece can attack the piece at the x, y position given its color
	 * @param x the x position
	 * @param y the y position
	 * @param ownColor the color of the piece attacking
	 * @return true or false whether it can take that piece
	 */
	public boolean canAttack(int x, int y, int ownColor)
	{
		if(ownColor == 0)
			return boardLayout[x][y].getColor() == 1;
		else
			return boardLayout[x][y].getColor() == 0;
		
	}
	/**
	 * retrieves the piece at that position
	 * @param x the x position
	 * @param y the y position
	 * @return the Piece object at that spot
	 */
	public Piece getPiece(int x, int y)
	{
		if(x < 0 || y < 0 || x >7 || y >7)
			return null;
		return boardLayout[x][y];
		
	}
	/**
	 * Gets the piece given at the string position from the arraylist
	 * @param str the position given as "xy"
	 * @return the Piece that is at that position
	 */
	public Piece getPiece(String str)
	{
		if(str.equals(""))
			return null;
		return getPiece(Integer.parseInt(str.substring(0,1)),Integer.parseInt(str.substring(1,2)));
	}
	/**
	 * Generates the possible moves from the piece at x and y
	 * @param x the x position
	 * @param y the y position
	 * @return the ArrayList moves that should be shown on the board as possible spots
	 */
	public ArrayList<String> showMoves(int x, int y)
	{
		Piece currPiece = getPiece(x,y);
		if(currPiece == null)
			return null;
		ArrayList<String> moves = currPiece.showPossibleMoves();
		//loop thru moves and depending on the different pieces do different things
		
		for(int i = 0; i < moves.size(); i++)
		{
			Piece currMove = getPiece(moves.get(i));
			
			if(currMove == null && currPiece instanceof Pawn && moves.get(i) != null && moves.get(i) != "" /*&& !(((Pawn) currPiece).isFirstMove())*/)
			{
				int tempX = getMoveX(moves, i);
				int tempY = getMoveY(moves, i);
				
				if(currPiece.getColor() == 0)
				{
					if(((currPiece.getX()+1 == tempX && currPiece.getY()+1 == tempY) || (currPiece.getX()+1 == tempX && currPiece.getY()-1 == tempY)))
						moves.set(i, "");	
				}
				else
				{
					if(((currPiece.getX()-1 == tempX && currPiece.getY()+1 == tempY) || (currPiece.getX()-1 == tempX && currPiece.getY()-1 == tempY)))
						moves.set(i, "");
				}
				continue;
			}
			else if(currMove == null)
				continue;
			
			if(currPiece instanceof Knight || currPiece instanceof King || currPiece instanceof Pawn || currPiece instanceof Pawship)
			{
				if(currMove.getColor() == currPiece.getColor())
				{
					moves.set(i,"");
				}
				
				if(currPiece instanceof Pawn && moves.get(i) != "" && !(((Pawn) currPiece).isFirstMove()))
				{
					int tempX = getMoveX(moves, i);
					int tempY = getMoveY(moves, i);
					if(currPiece.getColor() == 0 && (tempX == currPiece.getX()+1 && tempY == currPiece.getY()))
						moves.set(i,"");
					else if(currPiece.getColor() == 1 && (tempX == currPiece.getX()-1 && tempY == currPiece.getY()))
						moves.set(i,"");
				}
				else if(currPiece instanceof Pawn && (((Pawn) currPiece).isFirstMove()) && moves.get(i) != "")
				{
					int tempX = getMoveX(moves, i);
					int tempY = getMoveY(moves, i);
					if(currPiece.getColor() == 0 && (tempX == currPiece.getX()+1 && tempY == currPiece.getY()) && currMove != null)
					{
						moves.set(i,"");
						moves.set(i+1,"");
					}
					else if(currPiece.getColor() == 0 && (tempX == currPiece.getX()+2 && tempY == currPiece.getY()) && currMove != null)
						moves.set(i, "");
					if(currPiece.getColor() == 1 && (tempX == currPiece.getX()-1 && tempY == currPiece.getY()) && currMove != null)
					{
						moves.set(i,"");
						moves.set(i+1,"");
					}
					else if(currPiece.getColor() == 1 && (tempX == currPiece.getX()-2 && tempY == currPiece.getY()) && currMove != null)
						moves.set(i, "");
				}
			}
			else
			{
				
				if(currMove.getColor() != currPiece.getColor())
				{
					int j = i+1;
					if(j >= moves.size())
						continue;
					int tempX = getMoveX(moves, j);
					int tempY = getMoveY(moves, j);
					removeIllegalMoves(currPiece, moves, j, tempX, tempY);
					
				}
				else
				{
					int j = i;
					int tempX = getMoveX(moves, j);
					int tempY = getMoveY(moves, j);
					moves.set(j++,"");
					if(j >= moves.size())
						continue;
					tempX = getMoveX(moves, j);
					tempY = getMoveY(moves, j);
					removeIllegalMoves(currPiece, moves, j, tempX, tempY);
				}
			}
			
		}
		
		boolean hasAMove = false;
		for(int x1 = 0; x1 < moves.size(); x1++)
			if(moves.get(x1) != "")
				hasAMove = true;
		if(hasAMove)
			return moves;
		else
			return null;
		
	}
	/**
	 * @param currPiece
	 * @param moves
	 * @param j
	 * @param tempX
	 * @param tempY
	 */
	public void removeIllegalMoves(Piece currPiece, ArrayList<String> moves,
			int j, int tempX, int tempY) 
	{
		while(Math.abs(tempX-currPiece.getX()) > 1 || Math.abs(tempY-currPiece.getY()) > 1)
		{
			moves.set(j, "");
			j++;
			if(j >= moves.size())
				break;
			tempX = getMoveX(moves, j);
			tempY = getMoveY(moves, j);
			
		}
	}
	/**
	 * Gets the move's Y coordinate
	 * @param moves arraylist of moves
	 * @param j the index of the array
	 * @return the y position
	 */
	public int getMoveY(ArrayList<String> moves, int j) 
	{
		return Integer.parseInt(moves.get(j).substring(1,2));
	}
	/**
	 * Gets the move's X coordinate
	 * @param moves arraylist of moves
	 * @param j the index of the array
	 * @return the x position
	 */
	public int getMoveX(ArrayList<String> moves, int j) 
	{
		return Integer.parseInt(moves.get(j).substring(0,1));
	}
	/**
	 * move a piece from its current position to a new one
	 * @param fromX the starting x position
	 * @param fromY the starting y position
	 * @param x the ending x position
	 * @param y the ending y position
	 */
	public void makeMove(int fromX, int fromY, int x, int y)
	{
		Piece currPiece = getPiece(fromX,fromY);
		////System.out.println("making move: "+currPiece);
		if(currPiece instanceof Pawn && ((Pawn) currPiece).isFirstMove())
		{
			((Pawn) currPiece).setSecondMove(true);
			((Pawn) currPiece).setFirstMove(false);
		}
		else if(currPiece instanceof Pawn && ((Pawn) currPiece).isSecondMove())
			((Pawn) currPiece).setSecondMove(false);
			
		if(isEmptySpace(x,y))
		{
			boardLayout[fromX][fromY] = null;
			setLastRemovedPiece(null);
			boardLayout[x][y] = currPiece;
			currPiece.setX(x);
			currPiece.setY(y);
		}
		else
		{		
			boardLayout[fromX][fromY] = null;
			setLastRemovedPiece(boardLayout[x][y]);
			removePiece(x,y);
			boardLayout[x][y] = currPiece;
			currPiece.setX(x);
			currPiece.setY(y);
		}
		checkIfInCheck(1);
		checkIfInCheck(0);
	}
	/**
	 * method for the game ending checkers to call to move a piece in a certain situation not affecting actual game play though
	 * @param fromX from x position	
	 * @param fromY from this y position
	 * @param x to this x position
	 * @param y to this y position
	 */
	public void makeFakeMove(int fromX, int fromY, int x, int y)
	{
		Piece currPiece = getPiece(fromX,fromY);
		////System.out.println("making move: "+currPiece);
		if(currPiece instanceof Pawn && ((Pawn) currPiece).isFirstMove())
		{
			((Pawn) currPiece).setSecondMove(true);
			((Pawn) currPiece).setFirstMove(false);
		}
		else if(currPiece instanceof Pawn && ((Pawn) currPiece).isSecondMove())
			((Pawn) currPiece).setSecondMove(false);
			
		if(isEmptySpace(x,y))
		{
			boardLayout[fromX][fromY] = null;
			setLastFakeRemovedPiece(null);
			boardLayout[x][y] = currPiece;
			currPiece.setX(x);
			currPiece.setY(y);
		}
		else
		{		
			boardLayout[fromX][fromY] = null;
			setLastFakeRemovedPiece(boardLayout[x][y]);
			removePiece(x,y);
			boardLayout[x][y] = currPiece;
			currPiece.setX(x);
			currPiece.setY(y);
		}
		checkIfInCheck(1);
		checkIfInCheck(0);
	}
	/**
	 * checks if one of the teams is in check
	 * @param team 0 for white and 1 for black
	 */
	public void checkIfInCheck(int team) 
	{
		boolean didPutInCheck = false;
		
		if(team == 0)
		{
			String kingPos = wKing.getTile();
			
			for(int i = 0; i < bPieces.size(); i++)
			{
				int tempX = bPieces.get(i).getX();
				int tempY = bPieces.get(i).getY();
				ArrayList<String> bMoves = this.showMoves(tempX,tempY);//bPieces.get(i).showPossibleMoves();
				if(bMoves == null)
					continue;
				
				if(bMoves.contains(kingPos))
				{
					wKing.putInCheck(true);
					didPutInCheck = true;
				}
			}
			if(!didPutInCheck)
				wKing.putInCheck(false);
		}
		else
		{
			String kingPos = bKing.getTile();
			
			for(int i = 0; i < wPieces.size(); i++)
			{
				int tempX = wPieces.get(i).getX();
				int tempY = wPieces.get(i).getY();
				ArrayList<String> wMoves = this.showMoves(tempX,tempY);
				//ArrayList<String> wMoves = wPieces.get(i).showPossibleMoves();
				if(wMoves == null)
					continue;
			
				if(wMoves.contains(kingPos))
				{
					////System.out.println("king at "+kingPos+" is in check");
					bKing.putInCheck(true);
					didPutInCheck = true;
				}
			}
			if(!didPutInCheck)
				bKing.putInCheck(false);
		}
		
	}
	/**
	 * undoes the previous move 
	 * @param oldX the old x location to move back to
	 * @param oldY the old y location to move back to
	 * @param x the current x position
	 * @param y the current y position
	 */
	public void undoMove(int oldX, int oldY, int x, int y)
	{
		Piece currPiece = getPiece(x,y);
		//System.out.println("last removed piece: "+lastRemovedPiece);
		if(currPiece instanceof Pawn && ((Pawn) currPiece).isSecondMove())
			((Pawn) currPiece).setFirstMove(true);
		if(lastRemovedPiece == null)
		{
			boardLayout[oldX][oldY] = currPiece;
			boardLayout[x][y] = null;
			currPiece.setX(oldX);
			currPiece.setY(oldY);
		}
		else
		{		
			boardLayout[oldX][oldY] = currPiece;
			boardLayout[x][y] = lastRemovedPiece;
			if(lastRemovedPiece.getColor() == 0)
				wPieces.add(lastRemovedPiece);
			else
				bPieces.add(lastRemovedPiece);
			currPiece.setX(oldX);
			currPiece.setY(oldY);
		}
		checkIfInCheck(1);
		checkIfInCheck(0);
	}
	/**
	 * undoes the previous move 
	 * @param oldX the old x location to move back to
	 * @param oldY the old y location to move back to
	 * @param x the current x position
	 * @param y the current y position
	 */
	public void undoFakeMove(int oldX, int oldY, int x, int y)
	{
		Piece currPiece = getPiece(x,y);
		//System.out.println("last removed piece: "+lastRemovedPiece);
		if(currPiece instanceof Pawn && ((Pawn) currPiece).isSecondMove())
			((Pawn) currPiece).setFirstMove(true);
		if(fakeLastRemovedPiece == null)
		{
			boardLayout[oldX][oldY] = currPiece;
			boardLayout[x][y] = null;
			currPiece.setX(oldX);
			currPiece.setY(oldY);
		}
		else
		{		
			boardLayout[oldX][oldY] = currPiece;
			boardLayout[x][y] = fakeLastRemovedPiece;
			if(fakeLastRemovedPiece.getColor() == 0)
				wPieces.add(fakeLastRemovedPiece);
			else
				bPieces.add(fakeLastRemovedPiece);
			currPiece.setX(oldX);
			currPiece.setY(oldY);
		}
		checkIfInCheck(1);
		checkIfInCheck(0);
	}
	/**
	 * Sets the previously removed piece
	 * @param curr the piece last removed
	 */
	public void setLastRemovedPiece(Piece curr)
	{
		this.lastRemovedPiece = curr;
	}
	/**
	 * Sets the previously removed fake piece
	 * @param curr the piece last removed
	 */
	public void setLastFakeRemovedPiece(Piece curr)
	{
		this.fakeLastRemovedPiece = curr;
	}
	
	
	
	
	
	
}
