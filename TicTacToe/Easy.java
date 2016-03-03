
public class Easy implements Level {

	@Override
	public int[] getNextMove(Board board) {
		int size = board.board_Size;
		
		int move[]=new int[2];
	
		move[0]=(int)(Math.random()*size);
		move[1]=(int)(Math.random()*size);
		
		return move;
	}	
}
