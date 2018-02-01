import static org.junit.Assert.*;

import org.junit.Test;

public class TestScoreSheet {

	final int MIN_PINS_PER_FRAME = 0;
	final int MAX_PINS_PER_FRAME = 10;

	ScoreSheet s1, s2;

	@Test
	public void testOneThrow() {

		for (int i = MIN_PINS_PER_FRAME - 1; i <= MAX_PINS_PER_FRAME + 1; i++) {

			s1 = new ScoreSheet();

			try {
				s1.firstThrow(i);
				if (i < MIN_PINS_PER_FRAME || i > MAX_PINS_PER_FRAME) {
					assertFalse("IllegalArgumentException should have been thrown.", true);
				}
			} catch (Exception e1) {
				if (i >= MIN_PINS_PER_FRAME && i <= MAX_PINS_PER_FRAME) {
					assertTrue("Exception should not have been thrown", false);
				}
				assertTrue("Wrong exception was thrown", e1 instanceof IllegalArgumentException);
			}

			assertTrue(i == s1.getFrameScore(1));

		}
	}

	@Test
	public void testTwoThrows() {
		
		
		
	}

	@Test
	public void testThreeThrows() {

	}

	@Test
	public void testSpareCountsNextFrameScore() {

	}

	@Test
	public void testStrikeMovesToNextFrame() {

	}

	@Test
	public void testStrikeCountsNextFrameScores() {

	}

	@Test
	public void testSpareOnLastFrame() {

	}

	@Test
	public void testStrikeOnLastFrames() {

	}

	@Test
	public void testThrowOn11thFrame() {

	}
}
