package pieces;
import java.util.ArrayList;



/**
 * The Pawn class is a subclass of Piece and has information for all Pawn chess pieces created.
 */
public class Pawn extends Piece 
{
	
	private boolean firstMove;
	private boolean secondMove;

	/**
	 * The Pawn constructor that initializes the instance variables
	 * @param color the color of the piece
	 * @param x the x position on the board of this piece
	 * @param y the y position on the board of this piece
	 */
	public Pawn(int color, int x, int y) 
	{
		super(color,x,y);
		firstMove = true;
		secondMove=false;
	}
	/**
	 * retrieves the image for this type of piece
	 * @return the string file name of the image
	 */
	public String draw() 
	{
		if(this.getColor() == 0)
			return "wPawn.png";
		else
			return "bPawn.png";

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
		
		if(this.getColor() == 0)
		{
			if(this.firstMove)
			{
				moves.add(""+(xPos+1)+""+(yPos));
				moves.add(""+(xPos+2)+""+(yPos));
				if(this.canMove(xPos+1, yPos-1))
				{
					moves.add(""+(xPos+1)+""+(yPos-1));
				}
				if(this.canMove(xPos+1, yPos+1))
				{
					moves.add(""+(xPos+1)+""+(yPos+1));
				}
			}
			else
			{
				if(this.canMove(xPos+1, yPos))
				{
					moves.add(""+(xPos+1)+""+(yPos));
				}
				if(this.canMove(xPos+1, yPos+1))
				{
					moves.add(""+(xPos+1)+""+(yPos+1));
				}
				if(this.canMove(xPos+1, yPos-1))
				{
					moves.add(""+(xPos+1)+""+(yPos-1));
				}
			}
		}
		else
		{
			if(this.firstMove)
			{
				moves.add(""+(xPos-1)+""+(yPos));
				moves.add(""+(xPos-2)+""+(yPos));
				if(this.canMove(xPos-1, yPos-1))
				{
					moves.add(""+(xPos-1)+""+(yPos-1));
				}
				if(this.canMove(xPos-1, yPos+1))
				{
					moves.add(""+(xPos-1)+""+(yPos+1));
				}
			}
			else
			{
				if(this.canMove(xPos-1, yPos))
				{
					moves.add(""+(xPos-1)+""+(yPos));
				}
				if(this.canMove(xPos-1, yPos+1))
				{
					moves.add(""+(xPos-1)+""+(yPos+1));
				}
				if(this.canMove(xPos-1, yPos-1))
				{
					moves.add(""+(xPos-1)+""+(yPos-1));
				}
			}
		}
		return moves;
	}
	/**
	 * Sets this Pawn as finishing its first move
	 */
	public void setFirstMove(boolean isFirstMove)
	{
		this.firstMove = isFirstMove;
	}
	/**
	 * sets this pawn if its the second move
	 */
	public void setSecondMove(boolean secondMove)
	{
		this.secondMove = secondMove;
	}
	/**
	 * checks whether the move is the first move for this pawn
	 * @return true or false depending on if it is or not
	 */
	public boolean isFirstMove()
	{
		return this.firstMove;
	}
	/**
	 * checks whether the move is the second move for this pawn
	 * @return true or false depending on if it is or not
	 */
	public boolean isSecondMove()
	{
		return this.secondMove;
	}

}
