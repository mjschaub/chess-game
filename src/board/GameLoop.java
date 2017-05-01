package board;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import pieces.Piece;


/**
 * @author mjschau2
 *	The main game loop
 */
public class GameLoop  
{
	private boolean isRunning;
	private int turn;
	private static JButton[][] buttons;
	private static Board board;
	private static boolean hasChosenPiece;
	private ArrayList<String> currPossibleMoves;
	private static int fromX;
	private static int fromY;
	private static int undoFromX;
	private static int undoFromY;
	private static boolean canUndo;
	private static GameFrame createdBoard;
	
	/**
	 * the main function to start the chess game
	 * @param args
	 */
	public static void main(String[] args) 
	{
		startGame();
	}
	/**
	 * the method to restart/start a game loop
	 */
	private static void startGame() 
	{
		hasChosenPiece = false;
		canUndo = false;
		board = new Board();
		createdBoard = new GameFrame(board);
		buttons = createdBoard.getTiles();
	}
	/**
	 * game loop constructor
	 */
	public GameLoop()
	{
		this.isRunning = true;
		this.turn = 0;
		
	}
	/**
	 * The player clicked on a button and now a turn begins
	 * @param e the action event that was pressed
	 */
	public void mouseClicked(ActionEvent e) 
	{
		if(!isRunning)
			return;

        int x = 0,y = 0;
        for(int i = 0; i < 8; i++)
        	for(int j = 0; j < 8; j++)
        		if(buttons[i][j].equals(e.getSource()))
        		{
        			x=i;
        			y=j;
        		}
        if(canUndo && x == fromX && y == fromY && !hasChosenPiece)
        {
        	//wants to undo 
        	System.out.println("wants to undo");
        	Piece undoPiece = board.getPiece(undoFromX, undoFromY);
        	board.undoMove(fromX,fromY,undoFromX,undoFromY);
        	if(board.lastRemovedPiece != null)
        		buttons[undoFromX][undoFromY].setIcon(new ImageIcon(getClass().getResource("image/"+board.lastRemovedPiece.draw())));
        	else
        		buttons[undoFromX][undoFromY].setIcon(null);
        	
        	buttons[fromX][fromY].setIcon(new ImageIcon(getClass().getResource("image/"+undoPiece.draw())));
        	this.resetTileBackgrounds();
        	canUndo = false;
        	changeTurn();
        	return;
        	
        }
        else
        {
        	this.resetTileBackgrounds();
        	if(!hasChosenPiece && (board.getPiece(x, y) == null || !isPieceOnTeam(board.getPiece(x, y),this.turn)))
        		return;
        	else if(!hasChosenPiece)
        	{
        		fromX = x;
        		fromY = y;
        		hasChosenPiece = true;
        		currPossibleMoves = board.showMoves(x, y);
        		if(currPossibleMoves == null)
        		{
        			hasChosenPiece = false;
        			return;
        		}
        		for(int i = 0; i < currPossibleMoves.size(); i++)
        		{
        			
        			if(currPossibleMoves.get(i) == "")
        				continue;
        			
        			if(board.wKing.isInCheck() && turn == 0 || board.bKing.isInCheck() && turn == 1)
    				{
    					//make sure this move gets it out of check if not, don't move there
    					if(!wouldGetOutOfCheck(board.getPiece(fromX, fromY),board.getMoveX(currPossibleMoves, i),board.getMoveY(currPossibleMoves, i),fromX,fromY))
    						currPossibleMoves.set(i,"");
    					
    				}
    				else
    				{
    					//make sure moving there doesn't put player in check
    					if(doesPutKingInCheck(board.getMoveX(currPossibleMoves, i),board.getMoveY(currPossibleMoves, i),fromX,fromY))
    						currPossibleMoves.set(i,"");
    				}
        			if(currPossibleMoves.get(i) != "")
        			{
        				int tempX = board.getMoveX(currPossibleMoves, i);
        				int tempY = board.getMoveY(currPossibleMoves, i);
        				buttons[tempX][tempY].setBackground(Color.green);
        			}
        		}
        		return;
        	}
        	else if(hasChosenPiece)
        	{
        		boolean choseValidSpot = false;
        		for(int i = 0; i < currPossibleMoves.size(); i++)
        		{
        			if(currPossibleMoves.get(i).equals(x+""+y))
        			{
        				choseValidSpot = true;
        				if(choseValidSpot)
        				{
        					System.out.println("image icon: "+fromX+","+fromY+" and the piece "+board.getPiece(fromX,fromY));
        					buttons[x][y].setIcon(new ImageIcon(getClass().getResource("image/"+board.getPiece(fromX, fromY).draw())));
        					buttons[fromX][fromY].setIcon(null);
        					board.makeMove(fromX, fromY, x, y);
        					undoFromX = x;
        					undoFromY = y;
        					if(!board.wKing.isInCheck() && !board.bKing.isInCheck())
        						canUndo = true;
        					else
        						canUndo = false;
        					break;
        				}
        			}
        		}
        		resetTileBackgrounds();
        		if(canUndo)
        			buttons[fromX][fromY].setBackground(Color.yellow);
        		hasChosenPiece = false;
        		if(!choseValidSpot)
        		{
        			this.resetTileBackgrounds();
        			return;
        		}
        		
        	}
        	if(board.wKing.isInCheck() && turn == 1)
        	{
        		
        		JOptionPane.showMessageDialog(null, "White is in check!");
        		if(hasCheckmateCondition())
        		{
        			JOptionPane.showMessageDialog(null, "Checkmate!");
        			endGame(1);
        		}
        	}
        	else if(board.bKing.isInCheck() && turn == 0)
        	{
        		
        		JOptionPane.showMessageDialog(null, "Black is in check!");
        		if(hasCheckmateCondition())
        		{
        			JOptionPane.showMessageDialog(null, "Checkmate!");
        			endGame(0);
        		}
        	}
        	else if(isInStalemate())
        	{
        		JOptionPane.showMessageDialog(null, "A stalemate has occured, it's a tie!");
        		Object[] options = {"Start a new game","Stop playing"};
        		int choice = JOptionPane.showOptionDialog(createdBoard.getMyFrame(),
        					"Would you like to play again?",
        					"Game Over",
        					JOptionPane.YES_NO_OPTION,
        					JOptionPane.QUESTION_MESSAGE,
        					null,
        					options,
        					options[1]);
        		if(choice == 0)
        			startGame(); //play another game
        		else
        			this.isRunning = false; //quit
        		
        	}
        	
        	changeTurn();
        }
        	
    }
	/**
	 * checks if there is a player in checkmate
	 * @return true or false
	 */
	private boolean hasCheckmateCondition() 
	{
		//boolean canGetKing = true;
		if (this.turn == 1)
		{
			
			for(int i = 0; i < board.wPieces.size(); i++)
			{
				int oldX = board.wPieces.get(i).getX();
				int oldY = board.wPieces.get(i).getY();
				ArrayList<String> moves = board.showMoves(oldX, oldY);
				if(moves == null)
					continue;
				boolean pieceCanGetKingOut = false;
				for(int j = 0; j < moves.size(); j++)
				{
					if(moves.get(j) == "")
						continue;
					int newX = board.getMoveX(moves, j);
					int newY = board.getMoveY(moves, j);
					board.makeFakeMove(oldX, oldY, newX, newY);
					boolean noBlackPiecesCanAttack = true;
					for(int z = 0; z < board.bPieces.size(); z++)
					{
						int tempX = board.bPieces.get(z).getX();
						int tempY = board.bPieces.get(z).getY();
						ArrayList<String> newMoves = board.showMoves(tempX,tempY);
						if(newMoves == null)
							continue;
						if(newMoves.contains(board.wKing.getTile()))
							noBlackPiecesCanAttack = false;
						
							
					}
					if(noBlackPiecesCanAttack)
						pieceCanGetKingOut = true;
					board.undoFakeMove(oldX, oldY, newX, newY);
				}
				if(pieceCanGetKingOut)
					return false;
			}
			
		}
		else
		{
			for(int i = 0; i < board.bPieces.size(); i++)
			{
				int oldX = board.bPieces.get(i).getX();
				int oldY = board.bPieces.get(i).getY();
				ArrayList<String> moves = board.showMoves(oldX, oldY);
				if(moves == null)
					continue;
				boolean pieceCanGetKingOut = false;
				for(int j = 0; j < moves.size(); j++)
				{
					if(moves.get(j) == "")
						continue;
					int newX = board.getMoveX(moves, j);
					int newY = board.getMoveY(moves, j);
					board.makeFakeMove(oldX, oldY, newX, newY);
					boolean noWhitePiecesCanAttack = true;
					for(int z = 0; z < board.wPieces.size(); z++)
					{
						int tempX = board.wPieces.get(z).getX();
						int tempY = board.wPieces.get(z).getY();
						ArrayList<String> newMoves = board.showMoves(tempX,tempY);
						if(newMoves == null)
							continue;
						if(newMoves.contains(board.bKing.getTile()))
							noWhitePiecesCanAttack = false;
						
							
					}
					if(noWhitePiecesCanAttack)
						pieceCanGetKingOut = true;
					board.undoFakeMove(oldX, oldY, newX, newY);
				}
				if(pieceCanGetKingOut)
					return false;
			}
			
		}
		
		return true;
	}
	/**
	 * checks if the move would put the kind in check which shouldn't be allowed	
	 * @param toX move to x position
	 * @param toY move to y position
	 * @param fromX move from x position
	 * @param fromY move from y position
	 * @return true or false
	 */
	private boolean doesPutKingInCheck(int toX,int toY, int fromX, int fromY) 
	{
		boolean didPutKingInCheck = false;
		board.makeFakeMove(fromX, fromY, toX, toY);
		if(turn == 0)
		{
			for(int i = 0; i < board.bPieces.size(); i++)
			{
				int tempX  = board.bPieces.get(i).getX();
				int tempY  = board.bPieces.get(i).getY();
				ArrayList<String> newMoves = board.showMoves(tempX, tempY);
				if(newMoves == null)
					continue;
				if(newMoves.contains(board.wKing.getTile()))
					didPutKingInCheck = true;
			}
		}
		else
		{
			System.out.println("checking if black move puts king in check");
			for(int i = 0; i < board.wPieces.size(); i++)
			{
				int tempX  = board.wPieces.get(i).getX();
				int tempY  = board.wPieces.get(i).getY();
				ArrayList<String> newMoves = board.showMoves(tempX, tempY);
				System.out.println(newMoves);
				if(newMoves == null)
					continue;
				if(newMoves.contains(board.bKing.getTile()))
					didPutKingInCheck = true;
			}
		}
		board.undoFakeMove(fromX, fromY, toX, toY);
		return didPutKingInCheck;
	}
	/**
	 * checks to see if a move gets a piece out of check
	 * @param piece the piece to move
	 * @param toX moving to x position
	 * @param toY moving to y position
	 * @param fromX moving from x position
	 * @param fromY moving from y position
	 * @return true or false
	 */
	private boolean wouldGetOutOfCheck(Piece piece,int toX,int toY,int fromX, int fromY) 
	{
		boolean getsOutOfCheck = false;
		board.makeFakeMove(fromX, fromY, toX, toY);
		if(turn == 0)
		{
			board.checkIfInCheck(0);
			if(!board.wKing.isInCheck())
				getsOutOfCheck = true;
		}
		else
		{
			board.checkIfInCheck(1);
			if(!board.bKing.isInCheck())
				getsOutOfCheck = true;
		}
		board.undoFakeMove(fromX, fromY, toX, toY);
		return getsOutOfCheck;
		
		
	}
	/**
	 * Resets the tile's background color to cyan or grey after the possible moves are shown
	 */
	private void resetTileBackgrounds() 
	{
		for(int i = 0; i < 8; i++)
		{
			for(int j = 0; j < 8; j++)
			{
				if((i + j)%2 == 0) 
					buttons[i][j].setBackground(Color.cyan);
				else 
					buttons[i][j].setBackground(Color.lightGray);
			}
		}
	}
	
	/**
	 * method to stop watching action listeners from the buttons and end the game
	 * @param winner the winning team
	 */
	public void endGame(int winner)
	{
		//remove action listeners and pop up that game is over
		if(winner == 0)
		{
			GameFrame.setPreviousPlayerOneScore(GameFrame.getPreviousPlayerOneScore()+1);
			createdBoard.score.setText("Score - Player 1: "+GameFrame.getPreviousPlayerOneScore()+"  	Player 2: "+GameFrame.getPreviousPlayerTwoScore());
			JOptionPane.showMessageDialog(null, "White has won!");
			
		}
		else
		{
			GameFrame.setPreviousPlayerTwoScore(GameFrame.getPreviousPlayerTwoScore()+1);
			createdBoard.score.setText("Score - Player 1: "+GameFrame.getPreviousPlayerOneScore()+"  	Player 2: "+GameFrame.getPreviousPlayerTwoScore());
			JOptionPane.showMessageDialog(null, "Black has won!");
			
		}
		
		Object[] options = {"Start a new game",
                "Stop playing"};
		int choice = JOptionPane.showOptionDialog(createdBoard.getMyFrame(),
				"Would you like to play again?",
						"Game Over",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[1]);
		if(choice == 0)
		{	
			startGame(); //play another game
		}
		else
			this.isRunning = false;  //quit
		
		
	}
	/**
	 * changes the turn of the game
	 */
	public void changeTurn()
	{
		if(this.turn ==0)
			turn = 1;
		else
			turn = 0;
	}
	/**
	 * checks for stale mate in the game
	 * @return true or false
	 */
	public boolean isInStalemate()
	{
		
		boolean hasAMove = false;
		if (this.turn == 1)
		{
			
			for(int i = 0; i < board.wPieces.size(); i++)
			{
				int oldX = board.wPieces.get(i).getX();
				int oldY = board.wPieces.get(i).getY();
				ArrayList<String> moves = board.showMoves(oldX, oldY);
				if(moves == null)
					continue;
				
				for(int j = 0; j < moves.size(); j++)
				{
					if(moves.get(j) == "")
						continue;
					if(doesPutKingInCheck(board.getMoveX(moves, j),board.getMoveY(moves, j),oldX,oldY))
					{
						continue;
					}
					hasAMove = true;
				}
			}	
		}
		else
		{	
			for(int i = 0; i < board.bPieces.size(); i++)
			{
				int oldX = board.bPieces.get(i).getX();
				int oldY = board.bPieces.get(i).getY();
				ArrayList<String> moves = board.showMoves(oldX, oldY);
				if(moves == null)
					continue;
				
				for(int j = 0; j < moves.size(); j++)
				{
					if(moves.get(j) == "")
						continue;
					if(doesPutKingInCheck(board.getMoveX(moves, j),board.getMoveY(moves, j),oldX,oldY))
					{
						continue;
					}
					hasAMove = true;
				}
			}
		}
		
		
		return !hasAMove;
		
		
	}
	/**
	 * Checks to see if the piece is on the white or black team
	 * @return true or false if its on the given team
	 */
	public boolean isPieceOnTeam(Piece curr, int team)
	{
		if(team == 0) //check white pieces
		{
			for(int i = 0; i < board.wPieces.size(); i++)
			{
				if(curr.equals(board.wPieces.get(i)))
					return true;
			}
		}
		else //check black pieces
		{
			for(int i = 0; i < board.bPieces.size(); i++)
			{
				if(curr.equals(board.bPieces.get(i)))
					return true;
			}
		}
		return false;
	}
	/**
	 * Checks to see if the player's want to restart or forfeit the game
	 * @param e the button pressed
	 */
	public void forfeitOrRestart(ActionEvent e) 
	{
		Object[] options = {"Forfeit", "Restart"};
		int choice = JOptionPane.showOptionDialog(createdBoard.getMyFrame(),
		"What would you like to do?",
				"Game Options",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1]);
		if(choice == 0)
		{
			if(this.turn == 0)
				GameFrame.setPreviousPlayerTwoScore(GameFrame.getPreviousPlayerTwoScore()+1);
			else
				GameFrame.setPreviousPlayerOneScore(GameFrame.getPreviousPlayerOneScore()+1);
			startGame();
			
		}
		else if(choice == 1)
		{
			createdBoard.getMyFrame().dispatchEvent(new WindowEvent(createdBoard.getMyFrame(), WindowEvent.WINDOW_CLOSING));
			startGame();
		}
		
		
		
	}
}
