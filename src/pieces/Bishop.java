package pieces;
import java.util.ArrayList;

/**
 * The Bishop class is a subclass of Piece and has information for all Bishop chess pieces created.
 */
public class Bishop extends Piece 
{
	
	/**
	 * The bishop constructor that initializes the instance variables
	 * @param color the color of the piece
	 * @param x the x position on the board of this piece
	 * @param y the y position on the board of this piece
	 */
	public Bishop(int color, int x, int y) 
	{
		super(color,x,y);
	}
	/**
	 * retrieves the image for this type of piece
	 * @return the string file name of the image
	 */
	public String draw() 
	{
		if(this.getColor() == 0)
			return "wBishop.png";
		else
			return "bBishop.png";

	}
	/**
	 * Shows the possible moves that this piece can move given it has no information of other pieces
	 * @return An arraylist of strings that each correspond to a move on the board
	 */
	public ArrayList<String> showPossibleMoves() 
	{
		return showBishopMoves();
	}
	

}
