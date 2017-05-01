package board;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author mjschau2
 *	The creation of the GUI
 */
public class GameFrame extends JPanel
{

	
	private static final long serialVersionUID = 1L;
	private JFrame myFrame;
	private JPanel myPanel;
	private JButton tiles[][];
	private GameLoop currGame;
	public  JLabel score;
	private static int previousPlayerOneScore = 0;
	private static int previousPlayerTwoScore = 0;

	/**
	 * constructor for the GameFrame or GUI
	 */
	public GameFrame(final Board board)
	{
		super(new GridLayout(8,8));
		currGame = new GameLoop();
		setMyFrame(new JFrame("Chess game"));
		myPanel = new JPanel();
		tiles = new JButton[8][8];
		getMyFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getMyFrame().setResizable(false);
		getMyFrame().setLayout(new BorderLayout());
		getMyFrame().setPreferredSize(new Dimension(600, 700));
		getMyFrame().setFocusable(true);
		getMyFrame().pack();
		
		JButton newBForfeit = new JButton("Game Options");
		newBForfeit.setPreferredSize(new Dimension(60,60));
		newBForfeit.setBackground(Color.white);
		newBForfeit.addActionListener(new ActionListener() {         
			public void actionPerformed(ActionEvent e) {
					System.out.println("game should be forfeited");
					currGame.forfeitOrRestart(e);
			}
		});
		getMyFrame().add(newBForfeit,BorderLayout.BEFORE_FIRST_LINE);
		getMyFrame().setVisible(true);
		
	
		for(int x = 0; x < 8; x++)
		{
			for(int y = 0; y < 8; y++)
			{
				JButton newB = new JButton();
				newB.addActionListener(new ActionListener() {         
					public void actionPerformed(ActionEvent e) {
							System.out.println("piece was clicked");
							currGame.mouseClicked(e);

					}
				});
				newB.setPreferredSize(new Dimension(68,68));
				if((x + y)%2 == 0) 
					newB.setBackground(Color.cyan);
				else 
					newB.setBackground(Color.lightGray);
				if(board.getPiece(x,y) != null)
				{
					newB.setIcon(new ImageIcon(getClass().getResource("image/"+board.getPiece(x, y).draw())));
				}
				tiles[x][y] = newB;
				myPanel.add(tiles[x][y]);
					
			}
		}
		getMyFrame().add(myPanel,BorderLayout.CENTER);
		getMyFrame().setVisible(true);
		
		
		score = new JLabel("Score - Player 1: "+getPreviousPlayerOneScore()+"  	Player 2: "+getPreviousPlayerTwoScore());
		score.setFont(new Font("Serif", Font.PLAIN, 20));
		getMyFrame().add(score,BorderLayout.PAGE_END);
		getMyFrame().setVisible(true);
		
	}
	/**
	 * gets the button tiles
	 * @return the jbuttons
	 */
	public JButton[][] getTiles()
	{
		return this.tiles;
	}
	/**
	 * gets the jframe object
	 * @return the myFrame
	 */
	public JFrame getMyFrame() 
	{
		return myFrame;
	}
	/**
	 * sets the classes jframe object
	 * @param myFrame the myFrame to set
	 */
	public void setMyFrame(JFrame myFrame) 
	{
		this.myFrame = myFrame;
	}
	/**
	 * gets the first player's score
	 * @return the previousPlayerOneScore
	 */
	public static int getPreviousPlayerOneScore() 
	{
		return previousPlayerOneScore;
	}
	/**
	 * sets the first player's score
	 * @param previousPlayerOneScore the previousPlayerOneScore to set
	 */
	public static void setPreviousPlayerOneScore(int previousPlayerOne) 
	{
		previousPlayerOneScore = previousPlayerOne;
	}
	/**
	 * gets the second player's score
	 * @return the previousPlayerTwoScore
	 */
	public static int getPreviousPlayerTwoScore() 
	{
		return previousPlayerTwoScore;
	}
	/**
	 * sets the second players score
	 * @param previousPlayerTwoScore the previousPlayerTwoScore to set
	 */
	public static void setPreviousPlayerTwoScore(int previousPlayerTwo) 
	{
		previousPlayerTwoScore = previousPlayerTwo;
	}
	
	
}
