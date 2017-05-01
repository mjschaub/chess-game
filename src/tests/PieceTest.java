package tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;

import org.junit.Test;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Pawship;
import pieces.Queen;
import pieces.Rider;
import pieces.Rook;


/**
 * The tests to check the piece functionality
 */
public class PieceTest 
{

	King testKing = new King(0,0,0);
	Queen testQueen = new Queen(1,0,1);
	Bishop testBishop = new Bishop(1,0,2);
	Rook testRook = new Rook(0,0,3);
	Knight testKnight = new Knight(1,0,4);
	Pawn testPawn = new Pawn(0,0,5);
	Rider testRider = new Rider(1,2,3);
	Pawship testPawship = new Pawship(0,5,6);
	
	/**
	 * intializes the pieces and makes sure the objects are created and that they can't move off the board
	 */
	@Test
	public void intializePieces() 
	{
		
		assertNotEquals(testKing, null);
		assertNotEquals(testQueen, null);
		assertNotEquals(testRook, null);
		assertNotEquals(testPawn, null);
		assertNotEquals(testBishop, null);
		assertNotEquals(testKnight, null);
		assertNotEquals(testPawship, null);
		assertNotEquals(testRider, null);
		
		//same canMove implementation in each piece
		assertEquals(testPawn.canMove(-1,0),false);
		assertEquals(testPawn.canMove(0,-1),false);
		assertEquals(testPawn.canMove(9,0),false);
		assertEquals(testPawn.canMove(0,10),false);
		assertEquals(testPawn.canMove(4,6),true);
		
		
	}
	/**
	 * tests the pawn is initialized properly and has the correct possible moves
	 */
	@Test
    public void testPawn() 
	{
		assertEquals(testPawn.getX(),0);
		assertEquals(testPawn.getY(),5);
		assertEquals(testPawn.getColor(),0);
		assertEquals(testPawn.isFirstMove(),true);
		assertEquals(testPawn.showPossibleMoves(),Arrays.asList("15","25"));
		testPawn.setFirstMove(false);
		assertFalse(testPawn.isFirstMove());
		assertEquals(testPawn.showPossibleMoves(),Arrays.asList("15","16","14"));
		

    }
	/**
	 * tests the king is initialized properly and has the correct possible moves
	 */
	@Test
    public void testKing() 
	{
		assertEquals(testKing.getX(),0);
		assertEquals(testKing.getY(),0);
		assertEquals(testKing.getColor(),0);
		assertFalse(testKing.isInCheck());
		assertEquals(testKing.showPossibleMoves(),Arrays.asList("10","11","01"));
		testKing.setX(3);
		testKing.setY(3);
		assertEquals(testKing.showPossibleMoves(),Arrays.asList("42","43","44","34","24","23","22","32"));
		
		

    }
	/**
	 * tests the queen is initialized properly and has the correct possible moves
	 */
	@Test
    public void testQueen() 
	{
		assertEquals(testQueen.getX(),0);
		assertEquals(testQueen.getY(),1);
		assertEquals(testQueen.getColor(),1);
		assertEquals(testQueen.showPossibleMoves(),Arrays.asList("12","23","34","45","56","67","10","11","21","31","41","51","61","71","02","03","04","05","06","07","00"));
		testQueen.setX(3);
		testQueen.setY(3);
		assertEquals(testQueen.showPossibleMoves(),Arrays.asList("44","55","66","77","24","15","06","42","51","60","22","11","00","43","53","63","73","23","13","03","34","35","36","37","32","31","30"));
		

    }
	/**
	 * tests the rook is initialized properly and has the correct possible moves
	 */
	@Test
    public void testRook() 
	{
		assertEquals(testRook.getX(),0);
		assertEquals(testRook.getY(),3);
		assertEquals(testRook.getColor(),0);
		assertEquals(testRook.showPossibleMoves(),Arrays.asList("13","23","33","43","53","63","73","04","05","06","07","02","01","00"));
		testRook.setX(3);
		testRook.setY(3);
		assertEquals(testRook.showPossibleMoves(),Arrays.asList("43","53","63","73","23","13","03","34","35","36","37","32","31","30"));
		

    }
	/**
	 * tests the knight is initialized properly and has the correct possible moves
	 */
	@Test
    public void testKnight() 
	{
		assertEquals(testKnight.getX(),0);
		assertEquals(testKnight.getY(),4);
		assertEquals(testKnight.getColor(),1);
		assertEquals(testKnight.showPossibleMoves(),Arrays.asList("25","23","16","12"));
		testKnight.setX(3);
		testKnight.setY(3);
		assertEquals(testKnight.showPossibleMoves(),Arrays.asList("14","25","12","21","54","52","45","41"));
		

    }
	/**
	 * tests the bishop is initialized properly and has the correct possible moves
	 */
	@Test
    public void testBishop() 
	{
		assertEquals(testBishop.getX(),0);
		assertEquals(testBishop.getY(),2);
		assertEquals(testBishop.getColor(),1);
		assertEquals(testBishop.showPossibleMoves(),Arrays.asList("13","24","35","46","57","11","20"));
		testBishop.setX(3);
		testBishop.setY(3);
		assertEquals(testBishop.showPossibleMoves(),Arrays.asList("44","55","66","77","24","15","06","42","51","60","22","11","00"));
		

    }
	/**
	 * tests the Rider is initialized properly and has the correct possible moves
	 */
	@Test
    public void testRider() 
	{
		assertEquals(testRider.getX(),2);
		assertEquals(testRider.getY(),3);
		assertEquals(testRider.getColor(),1);
		assertEquals(testRider.showPossibleMoves(),Arrays.asList("24","25","26","27"));
		testRider.setX(3);
		testRider.setY(3);
		assertEquals(testRider.showPossibleMoves(),Arrays.asList("34","35","36","37"));
		
    }
	/**
	 * tests the Pawship is initialized properly and has the correct possible moves
	 */
	@Test
    public void testPawship() 
	{
		assertEquals(testPawship.getX(),5);
		assertEquals(testPawship.getY(),6);
		assertEquals(testPawship.getColor(),0);
		assertEquals(testPawship.showPossibleMoves(),Arrays.asList("65","67","47","45"));
		testPawship.setX(4);
		testPawship.setY(3);
		assertEquals(testPawship.showPossibleMoves(),Arrays.asList("52","54","34","32"));
		
    }

}
