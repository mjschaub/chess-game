package pieces;
import java.util.ArrayList;



/**
 * The Queen class is a subclass of Piece and has information for all Queen chess pieces created.
 */
public class Queen extends Piece 
{
	
	/**
	 * The Queen constructor that initializes the instance variables
	 * @param color the color of the piece
	 * @param x the x position on the board of this piece
	 * @param y the y position on the board of this piece
	 */
	public Queen(int color, int x, int y) 
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
			return "wQueen.png";
		else
			return "bQueen.png";
	}
	/**
	 * Shows the possible moves that this piece can move given it has no information of other pieces
	 * @return An arraylist of strings that each correspond to a move on the board
	 */
	public ArrayList<String> showPossibleMoves() 
	{
		ArrayList<String> moves = new ArrayList<String>();
		ArrayList<String> bishopMoves = showBishopMoves();
		ArrayList<String> rookMoves = showRookMoves();
		
		moves.addAll(bishopMoves);
		moves.addAll(rookMoves);
		
		return moves;
		
	}
	

}
