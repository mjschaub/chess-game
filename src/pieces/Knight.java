package pieces;
import java.util.ArrayList;




/**
 * The Knight class is a subclass of Piece and has information for all Knight chess pieces created.
 */
public class Knight extends Piece 
{
	
	/**
	 * The Knight constructor that initializes the instance variables
	 * @param color the color of the piece
	 * @param x the x position on the board of this piece
	 * @param y the y position on the board of this piece
	 */
	public Knight(int color, int x, int y) 
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
			return "wKnight.png";
		else
			return "bKnight.png";

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
		if(this.canMove(xPos-2, yPos+1))
		{
			moves.add(""+(xPos-2)+""+(yPos+1));
		}
		if(this.canMove(xPos-1, yPos+2))
		{
			moves.add(""+(xPos-1)+""+(yPos+2));
		}
		if(this.canMove(xPos-2, yPos-1))
		{
			moves.add(""+(xPos-2)+""+(yPos-1));
		}
		if(this.canMove(xPos-1, yPos-2))
		{
			moves.add(""+(xPos-1)+""+(yPos-2));
		}
		if(this.canMove(xPos+2, yPos+1))
		{
			moves.add(""+(xPos+2)+""+(yPos+1));
		}
		if(this.canMove(xPos+2, yPos-1))
		{
			moves.add(""+(xPos+2)+""+(yPos-1));
		}
		if(this.canMove(xPos+1, yPos+2))
		{
			moves.add(""+(xPos+1)+""+(yPos+2));
		}
		if(this.canMove(xPos+1, yPos-2))
		{
			moves.add(""+(xPos+1)+""+(yPos-2));
		}
		
		return moves;
		
	}

}
