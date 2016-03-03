
public class Computer implements Player {

	public String game_mode;
	public Level level;
	
	public Computer(String game_mode) 
	{
		this.game_mode = game_mode;
		String levelClass;
		
		if(game_mode.equalsIgnoreCase("H"))
			levelClass = "Hard";
		else if(game_mode.equalsIgnoreCase("M"))
			levelClass = "Medium";
		else
			levelClass = "Easy";
		
		try 
		{
			level = (Level) Class.forName(levelClass).newInstance();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	@Override
	public void getNextMove(Board board) 
	{
		while(true) 
		{	
			int move[] = level.getNextMove(board);
			if(isMoveValid(move, board)) 
			{
				board.board[move[0]][move[1]]='O';
				break;
			}
		}		
	}

	@Override
	public String toString() 
	{
		return "COMPUTER";
	}
	
	@Override
	public boolean isMoveValid(int[] move,Board board) 
	{
		if(!(board.board[move[0]][move[1]]=='X' || board.board[move[0]][move[1]]=='O')) 
		{
			return true;
		}		
		return false;
	}

}
