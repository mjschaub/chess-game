/**
 * 
 */
package pieces;

import java.util.ArrayList;

/**
 * @author mjschaub
 *	The Rider Piece class
 */
public class Rider extends Piece 
{

	
	
	/**
	 * Constructor to initialize the Rider piece
	 * @param color the color of the piece
	 * @param x the x coordinate
	 * @param y the y coordinate
	 */
	public Rider(int color, int x, int y) 
	{
		super(color, x, y);
	}

	/**
	 * Gets the image for this type of piece
	 * @return the String name of the file
	 */
	public String draw() 
	{
		if(this.getColor()== 0)
			return "wRider.png";
		else
			return "bRider.png";
	}

	/**
	 * Shows the possible moves for this piece on the board
	 * @return the ArrayList of moves
	 */
	public ArrayList<String> showPossibleMoves() 
	{
		ArrayList<String> moves = new ArrayList<String>();
		int xPos = this.getX();
		int yPos = this.getY();
		
		if(this.getColor() == 0)
		{
			for(int i = yPos-1; i > -1; i--)
			{
				moves.add(""+xPos+""+i);
			}
		}
		else
		{
		
			for(int i = yPos+1; i < 8; i++)
			{
				moves.add(""+xPos+""+i);
			}
		}
			
		return moves;
		
	}

}
