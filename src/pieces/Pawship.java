/**
 * 
 */
package pieces;

import java.util.ArrayList;

/**
 * @author mjschaub
 *	The Pawship piece class
 */
public class Pawship extends Piece 
{

	/**
	 * The constructor for the Pawship
	 * @param color the color of the piece
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Pawship(int color, int x, int y) 
	{
		super(color, x, y);
	}

	/**
	 * Gets the image of the piece
	 * @return the string of the file name
	 */
	public String draw() 
	{
		if(this.getColor()== 0)
			return "wPawship.png";
		else
			return "bPawship.png";
	}

	/**
	 * Gets the possible moves assuming the Pawship is the only piece on the board
	 * @return the ArrayList of moves
	 */
	public ArrayList<String> showPossibleMoves() 
	{
		ArrayList<String> moves = new ArrayList<String>();
		int xPos = this.getX();
		int yPos = this.getY();
		if(this.canMove(xPos+1, yPos-1))
		{
			moves.add(""+(xPos+1)+""+(yPos-1));
		}
		if(this.canMove(xPos+1, yPos+1))
		{
			moves.add(""+(xPos+1)+""+(yPos+1));
		}
		if(this.canMove(xPos-1, yPos+1))
		{
			moves.add(""+(xPos-1)+""+(yPos+1));
		}
		if(this.canMove(xPos-1, yPos-1))
		{
			moves.add(""+(xPos-1)+""+(yPos-1));
		}
		return moves;
	}

}
