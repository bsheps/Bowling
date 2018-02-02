import java.util.Random;

public class Driver {	
	
	public static void main(String[] args){
		ScoreSheet s1 = new ScoreSheet();
		Random rand = new Random();
		for(int i = 1; i <= 10; ++i){
			
			int firstBall = rand.nextInt(11);	// random [inclusive, exclusive) aka [0, 11)
			int secondBall = rand.nextInt(11-firstBall);	
			
			s1.playFrame(firstBall, secondBall);
			//so we can see what's going on in the game
			System.out.println("Frame : " + i + ", FirstBall: "+firstBall+", SecondBall: " + secondBall+ "\nTotal so far: " + s1.getScore() + "\n");
		}
		s1.playFrame(7, 2); // try one last hand, should print end of game
	}
}