package pieces;

import java.util.ArrayList;


 

/**
 * The abstract Piece class which has information pertaining to each chess piece and the actions they have to make.
 */
public abstract class Piece 
{
	int x;
	int y;
	int color; //0 for white, 1 for black
	
	public abstract String draw();
	public abstract ArrayList<String> showPossibleMoves();
	/**
	 * The Piece constructor
	 * @param color the color of the piece
	 * @param x the x position
	 * @param y the y position
	 */
	Piece(int color, int x, int y)
	{
		this.color = color;
		this.x = x;
		this.y = y;
	}
	/**
	 * sets the x position
	 * @param x the position to set it to
	 */
	public void setX(int x) 
	{
		this.x = x;
	}
	/**
	 * sets the y position
	 * @param y the position to set it to
	 */
	public void setY(int y) 
	{
		this.y = y;
	}
	/**
	 * gets the x position of this piece
	 * @return the integer x position
	 */
	public int getX() 
	{
		
		return x;
	}
	/**
	 * gets the y position of this piece
	 * @return the integer y position
	 */
	public int getY() 
	{
		
		return y;
	}
	/**
	 * returns the piece's color
	 * @return the integer 0 for white or 1 for black
	 */
	public int getColor() 
	{
		return color;
	}
	/**
	 * Gets this pieces tile in string format
	 * @return the String format of the position
	 */
	public String getTile() 
	{
		return this.x+""+this.y;
	}
	/**
	 * Tests whether the x and y are on the board
	 * @param x the x position
	 * @param y the y position
	 * @return true if it is on the board, false otherwise
	 */
	public boolean canMove(int x, int y) 
	{
		
		if(x > 7 || x < 0 || y > 7 || y < 0) 
			return false;
		return true;
	}
	/**
	 * Calculates the bishop's or part of the queen's moves
	 * @return the arraylist of moves
	 */
	public ArrayList<String> showBishopMoves() 
	{
		ArrayList<String> moves = new ArrayList<String>();
		int xPos = this.getX();
		int yPos = this.getY();
		int i = xPos+1;
		int j = yPos+1;
		while(i < 8 && j < 8)
		{
			moves.add(""+i+""+j);
			i++;
			j++;
		}
		i = xPos-1;
		j = yPos+1;
		while(i > -1 && j < 8)
		{
			moves.add(""+i+""+j);
			i--;
			j++;
		}
		i = xPos+1;
		j = yPos-1;
		while(i < 8 && j > -1)
		{
			moves.add(""+i+""+j);
			i++;
			j--;
		}
		i = xPos-1;
		j = yPos-1;
		while(i > -1 && j > -1)
		{
			moves.add(""+i+""+j);
			i--;
			j--;
		}
		return moves;
	}
	/**
	 * calculates the rook's or part of the queen's moves
	 * @return the arraylist of moves
	 */
	public ArrayList<String> showRookMoves() 
	{
		ArrayList<String> moves = new ArrayList<String>();
		int xPos = this.getX();
		int yPos = this.getY();
		for(int i = xPos+1; i < 8; i++)
		{
			moves.add(""+i+""+yPos);
		}
		for(int i = xPos-1; i > -1; i--)
		{
			moves.add(""+i+""+yPos);
		}
		for(int i = yPos+1; i < 8; i++)
		{
			moves.add(""+xPos+""+i);
		}
		for(int i = yPos-1; i > -1; i--)
		{
			moves.add(""+xPos+""+i);
		}
		
		return moves;
	}
}
