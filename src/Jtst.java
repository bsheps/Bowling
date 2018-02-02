import static org.junit.Assert.*;

import org.junit.Test;

public class Jtst {

	ScoreSheet game;
	@Test
	public void test1Throws() {
		
		for(int i =0; i <=10; ++i) {
			game = new ScoreSheet();
			game.firstThrow(i);
			assertEquals(i, game.getScore());
		}
		
		try {
			game.firstThrow(11);
			fail("Should have thrown exception");
		}
		catch(Exception e1) {
			assertTrue("Wrong exception was thrown", e1 instanceof IllegalArgumentException);
		}
		try {
			game.firstThrow(-1);
			fail("Should have thrown exception: firstThrow");
		}
		catch(Exception e1) {
			assertTrue("Wrong exception was thrown", e1 instanceof IllegalArgumentException);
		}
		
	}
	@Test
	public void test2Throws() {
		
		for(int i =0; i <=10; ++i) {	// test second throw from 10-0
			game = new ScoreSheet();
			game.firstThrow(i);
			assertEquals(i, game.getScore());
			game.secondThrow(10-i);
			assertEquals(10, game.getScore());
		}
		game = new ScoreSheet();
		game.firstThrow(10);
		try {					// can't knock down more than 10 pins per frame
			game.secondThrow(1);
			fail("should never get here");
		}
		catch(Exception e1) {
			assertTrue("Wrong exception was thrown", e1 instanceof IllegalArgumentException);
		}
		game = new ScoreSheet();
		try {					// can't knock down negative pins
			game.secondThrow(-1);
			fail("should never get here");
		}
		catch(Exception e1) {
			assertTrue("Wrong exception was thrown", e1 instanceof IllegalArgumentException);
		}
		
	}
	@Test
	public void test3Throws() {
		for(int i =10; i > 0; i--) {	// first frame is a strike/ spare
			game = new ScoreSheet();
			assertEquals(0,game.getFrameScore(1));
			assertEquals(0, game.getFrameScore(2));
			assertEquals(0, game.getScore());
			game.playFrame(i, 10-i);
			assertEquals(10,game.getFrameScore(1));
			assertEquals(0, game.getFrameScore(2));

			game.playFrame(i, 0);
			assertEquals(10 +i,game.getFrameScore(1));
			assertEquals(i, game.getFrameScore(2));
			assertEquals(10+2*i, game.getScore());
		}
		for(int i =9; i > 0; i--) {	// no strikes or spares
			game = new ScoreSheet();
			assertEquals(0,game.getFrameScore(1));
			assertEquals(0, game.getFrameScore(2));
			assertEquals(0, game.getScore());
			game.playFrame(i, 0);
			assertEquals(i,game.getFrameScore(1));
			assertEquals(0, game.getFrameScore(2));

			game.playFrame(i, 0);
			assertEquals(i,game.getFrameScore(1));
			assertEquals(i, game.getFrameScore(2));
			assertEquals(2*i, game.getScore());
		}
		
	}
	@Test
	public void testSpareCountsNextFrameScore() {
			game = new ScoreSheet();

			for(int j = 0; j < 9; ++j) {	// no strikes, only spares, testing all frames 1-9	
				game.playFrame(j, 10-j);	
				game.playFrame(j, 10-j);
				assertEquals(10 + j, game.getFrameScore(--game.frame));
			}
			game.playFrame(9, 1);
			assertEquals(10, game.getFrameScore(10));	// check 10th frame
			
		}
	@Test
	public void testStrikeMovesToNextFrame() {
		game = new ScoreSheet();
		for(int j = 0; j < 10; ++j) {
			game.playFrame(10, 5);
			assertEquals(10, game.getFrameScore(game.frame));	// check that second ball was not thrown
			assertEquals(j+1, game.frame);	// check that it moved to next frame
		}
	}

	@Test
	public void testStrikeCountsNextFrameScores() {
		game = new ScoreSheet();
		game.playFrame(10, 3);	// strike counts both throws in next frame
		game.playFrame(2, 6);
		assertEquals(18, game.getFrameScore(1));
		game = new ScoreSheet();
		game.playFrame(10, 4);	// 2 strikes in a row
		game.playFrame(10, 8);
		game.playFrame(7, 3);
		assertEquals(27, game.getFrameScore(1));
	}
	@Test
	public void testSpareOnLastFrame() {
		game = new ScoreSheet();
		game.frame = 9;	// last frame in the index
		game.playFrame(8, 2);
		assertEquals(10, game.getFrameScore(game.frame));	// last frame equals 10
		int score = game.getScore();
		game.playFrame(9, 0);	// try to play another frame, should not allow this, score should remain same
		assertEquals(score, game.getScore());	// score should not change
	}
	
	@Test
	public void testStrikeOnLastFrames() {
		game = new ScoreSheet();
		game.frame = 7;	// 8th frame in the array
		game.playFrame(10, 0);
		game.playFrame(10, 0);
		game.playFrame(10, 0);
		assertEquals(30, game.getFrameScore(8));
		assertEquals(20, game.getFrameScore(9));
		assertEquals(10, game.getFrameScore(10));
	}
	@Test
	public void testThrowOn11thFrame() {
		game = new ScoreSheet();
		game.frame = 10;	// 11th frame
		game.playFrame(9, 0);	// should just print end of game
	}
}