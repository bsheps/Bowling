public class ScoreSheet {

		private int[][] byFrame = new int[12][2];
		int frame = 0;

		public void playFrame(int firstBall, int secondBall) {
			if(frame > 9)
				System.out.println("Total score:"+ getScore() +"\nEnd of Game\nTo start a new game, open a new ScoreSheet");
			else {
				firstThrow(firstBall);
				if (byFrame[frame][0] != 10) 
					secondThrow(secondBall);		
				++frame;	
			}
		}
		public void firstThrow(int pinsDown){
			if(pinsDown > 10 || pinsDown < 0) throw new IllegalArgumentException("firstthrow invalid pinsDown");
			byFrame[frame][0]= pinsDown;	
		}
		
		public void secondThrow(int pinsDown){
			if(pinsDown > 10 - byFrame[frame][0] || pinsDown <0 ) throw new IllegalArgumentException("secondthrow invalid pinsDown");
			byFrame[frame][1]= pinsDown;	
		}
		
		public int getScore(){
			// gets score at any point in game (including the end of game)
			int total=0;	// initialize total
			for(int i =0; i< 10; ++i){	// loop through all 10 frames
				if(byFrame[i][0] == 10){	
					// special case of a strike
					if(byFrame[i+1][0] == 10)
						total+= 20 + byFrame[i +2][0];// 2 strikes in a row

					else
						total += 10+ byFrame[i+1][0] + byFrame[i+1][1];	
				}
				else if(byFrame[i][0] + byFrame[i][1] == 10)	
					total += 10 + byFrame[i+1][0];// special case of a spare

				else
					total+= byFrame[i][0]+ byFrame[i][1];// everything else	
			}
			return total;
		}
		
		public int getFrameScore(int frameNumber) { // put in frame number [1, 10] and it will calculate the score for that frame
			if(frameNumber < 0 && frameNumber >10) throw new IllegalArgumentException("getFrameScore() invalid frameNumber");
			
			int getFrame = frameNumber - 1;	// adjustment since array starts at 0, but frames start at 1

			if(frameNumber == 10) // last frame, dont count the 11th frame (it doesn't exist)
				return byFrame[9][0] + byFrame[9][1];
			
			else if(byFrame[getFrame][0] == 10)	// special case of a strike
				if(byFrame[getFrame+1][0] == 10) // 2 strikes in a row
					return 20 + byFrame[getFrame +2][0];	// add the third frame since 2 strikes
				else // 1 strike, followed by non strike
					return 10 + (byFrame[getFrame+1][0] + byFrame[getFrame+1][1]);		// add next 2 balls
			
			else if(byFrame[getFrame][0] + byFrame[getFrame][1] == 10)
				return 10 + (byFrame[getFrame+1][0]);// special case of a spare, add next ball thrown
			
			else
				return byFrame[getFrame][0]+ byFrame[getFrame][1];
		}	
}