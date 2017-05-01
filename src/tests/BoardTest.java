package tests;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import board.Board;

/**
 * The test class to test the Board functionality
 * @author mjschau2
 *
 */
public class BoardTest 
{

	Board testBoard = new Board();
	/**
	 * initializes the board and makes sure it sets up properly
	 */
	@Test
	public void intializeBoard() 
	{
		assert(testBoard.boardLayout[0][3] instanceof Queen);
		assertEquals(testBoard.whitePieces, 18);
		assert(testBoard.boardLayout[1][2] instanceof Pawn);
		assertEquals(testBoard.boardLayout[4][5], null);
		
	}
	/**
	 * tests removing a piece from the board and that it decrements the number of pieces on a board
	 */
	@Test
    public void testRemovePiece() 
	{
		//removePiece(x,y)
        testBoard.removePiece(1,0);
        assertEquals(testBoard.boardLayout[1][0],null);
        assertEquals(testBoard.whitePieces, 17);
        testBoard.removePiece(4,5);
        assertEquals(testBoard.boardLayout[4][5],null);
        assertEquals(testBoard.whitePieces, 17);
        testBoard.removePiece(1,3);
        assertEquals(testBoard.boardLayout[1][3],null);
        assertEquals(testBoard.whitePieces, 16);
        testBoard.removePiece(6,2);
        assertEquals(testBoard.boardLayout[6][2],null);
        assertEquals(testBoard.blackPieces, 17);

    }
	/**
	 * Tests moving a piece from one spot to another empty space
	 */
	@Test
    public void testMove() 
	{
        //makeMove(fromX,fromY,x,y)
		Piece temp = testBoard.getPiece(0, 1);
		testBoard.makeMove(0,1,2,2);
        assertEquals(testBoard.boardLayout[0][1],null);
        assertEquals(testBoard.boardLayout[2][2], temp);
        Piece temp2 = testBoard.getPiece(0, 2);
		testBoard.makeMove(0,2,1,2);
        assertEquals(testBoard.boardLayout[0][2],null);
        assertEquals(testBoard.boardLayout[1][2], temp2);
       
    }
	/**
	 * tests moving a piece onto where an enemy is to attack it
	 */
	@Test
    public void testAttack() 
	{
		Piece temp = testBoard.getPiece(1, 4);
		testBoard.makeMove(1,4,7,2);
        assertEquals(testBoard.boardLayout[1][4],null);
        assertEquals(testBoard.boardLayout[7][2], temp);
        assertEquals(testBoard.blackPieces, 17);

    }
	/**
	 * tests undoing a move
	 */
	@Test
    public void testUndo() 
	{
		Piece temp = testBoard.getPiece(1,4);
		Piece temp2 = testBoard.getPiece(7, 2);
		testBoard.makeMove(1,4,7,2);
		testBoard.undoMove(1,4,7,2);
        assertEquals(testBoard.boardLayout[1][4], temp);
        assertEquals(testBoard.boardLayout[7][2], temp2);
        

    }
	/**
	 * tests showing all the possible moves for pieces on the board when they are around other pieces
	 */
	@Test
    public void testShowMoves() 
	{
       //showMoves(x,y)
		
		assertEquals(((Pawn) (testBoard.getPiece(1,6))).isFirstMove(), true);
        assertEquals(Arrays.asList("26", "36","",""), ((ArrayList<String>)testBoard.showMoves(1,6))); //pawn on first move
        
        ((Pawn) (testBoard.getPiece(1,6))).setFirstMove(false);
        assertEquals(((Pawn) (testBoard.getPiece(1,6))).isFirstMove(), false);
        assertEquals(Arrays.asList("26","",""), ((ArrayList<String>)testBoard.showMoves(1,6))); //pawn not on its first move
        
        testBoard.makeMove(6, 6, 2, 5);
        assertEquals(((Pawn) (testBoard.getPiece(1,6))).isFirstMove(), false);
        assertEquals(Arrays.asList("26","","25"), ((ArrayList<String>)testBoard.showMoves(1,6))); //pawn being able to attack
        
        testBoard.makeMove(2, 5, 2, 6);
        assertEquals(((Pawn) (testBoard.getPiece(1,6))).isFirstMove(), false);
        assertEquals(null, ((ArrayList<String>)testBoard.showMoves(1,6))); //pawn not able to move because of enemy
        
        assertEquals(null, ((ArrayList<String>)testBoard.showMoves(0,0))); //rook blocked in
        
        testBoard.removePiece(1, 0);
        assertEquals(Arrays.asList("10","20","30","40","","","","","","","","","",""), ((ArrayList<String>)testBoard.showMoves(0,0))); //rook being able to move across the board into enemy pawn
        
        testBoard.makeMove(0, 5, 4, 5);
        assertEquals(Arrays.asList("56","67","36","27","54","63","","34","23","",""), ((ArrayList<String>)testBoard.showMoves(4,5))); //bishop
        
        testBoard.makeMove(0, 3, 4, 4);
        assertEquals(Arrays.asList("55","66","77","","","","53","62","","33","22","","","54","64","","34","24","","","","","","43","42","",""), ((ArrayList<String>)testBoard.showMoves(4,4))); //Queen
        
        testBoard.makeMove(0, 4, 4, 4);
        assertEquals(Arrays.asList("53","54","55","","","34","33","43"), ((ArrayList<String>)testBoard.showMoves(4,4))); //King
        
        assertEquals(Arrays.asList("27","25",""), ((ArrayList<String>)testBoard.showMoves(0,6))); //knight
        
        assertEquals(null, ((ArrayList<String>)testBoard.showMoves(7,3))); //knight not being able to move at all
        
        assertEquals(Arrays.asList("","46","26","24"), ((ArrayList<String>)testBoard.showMoves(3,5))); //Pawship
        
        assertEquals(Arrays.asList("41","","","","","",""), ((ArrayList<String>)testBoard.showMoves(4,0))); //black Rider
        
        assertEquals(Arrays.asList("36","","","","","",""), ((ArrayList<String>)testBoard.showMoves(3,7))); //white Rider
        
        
        
        
    }

}
