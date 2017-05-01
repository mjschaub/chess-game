package pieces;
import java.util.ArrayList;


/**
 * The King class is a subclass of Piece and has information for all King chess pieces created.
 */
public class King extends Piece 
{

	private boolean inCheck;
	
	/**
	 * The King constructor that initializes the instance variables
	 * @param color the color of the piece
	 * @param x the x position on the board of this piece
	 * @param y the y position on the board of this piece
	 */
	public King(int color, int x, int y) 
	{
		super(color,x,y);
		inCheck = false;
	}
	/**
	 * retrieves the image for this type of piece
	 * @return the string file name of the image
	 */
	public String draw() 
	{
		if(this.getColor() == 0)
			return "wKing.png";
		else
			return "bKing.png";

	}
	/**
	 * Shows the possible moves that this piece can move given it has no information of other pieces
	 * @return An arraylist of strings that each correspond to a move on the board
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
		if(this.canMove(xPos+1, yPos))
		{
			moves.add(""+(xPos+1)+""+(yPos));
		}
		if(this.canMove(xPos+1, yPos+1))
		{
			moves.add(""+(xPos+1)+""+(yPos+1));
		}
		if(this.canMove(xPos, yPos+1))
		{
			moves.add(""+(xPos)+""+(yPos+1));
		}
		if(this.canMove(xPos-1, yPos+1))
		{
			moves.add(""+(xPos-1)+""+(yPos+1));
		}
		if(this.canMove(xPos-1, yPos))
		{
			moves.add(""+(xPos-1)+""+(yPos));
		}
		if(this.canMove(xPos-1, yPos-1))
		{
			moves.add(""+(xPos-1)+""+(yPos-1));
		}
		if(this.canMove(xPos, yPos-1))
		{
			moves.add(""+(xPos)+""+(yPos-1));
		}
		
		return moves;
	}
	/**
	 * Sets whether this king is in check or not
	 */
	public void putInCheck(boolean inCheck)
	{
		this.inCheck = inCheck;
	}
	/**
	 * Checks if this king is in check
	 * @return true or false if its in check or not
	 */
	public boolean isInCheck()
	{
		return this.inCheck;
	}

}
