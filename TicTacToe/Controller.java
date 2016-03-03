
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
	
	private int bSize;
	private String game_mode;
	private int number_of_games;	
	
	public boolean isBoardValid(String bSize) 
	{
		int length = bSize.length();
		if(length%2==0) {
			return false;
		}
		
		int s = (length-1)/2;
		
		if(bSize.substring(0, s).equals(bSize.substring(length-s))) 
		{
			this.bSize = Integer.parseInt(bSize.substring(0, s));
			return true;
		}
		return false;
	}

	public boolean isGameModeValid(String type) 
	{	
		type = type.toUpperCase();
		
		if(type.equals("E") || type.equals("M") || type.equals("H")) 
		{
			game_mode = type;
			return true;
		}
		return false;
	}


	public Game newGame() 
	{	
		if(number_of_games%2==1) 
		{
			return new Game(new User(),new Computer(game_mode),new Board(bSize));
		}		
		return new Game(new Computer(game_mode),new User(), new Board(bSize));
	}

	public void playGame() 
	{
		String input=null;
		do 
		{
			number_of_games++;			
			System.out.println("*******************************************");
			System.out.println("****************** NEW GAME ***************");
			System.out.println("*******************************************");

			String type="";
			while(true) {
				System.out.println("Please select the game mode 'E-Easy' | 'M-Medium' | 'H-Hard' : ");
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
			    
				try {
					type = bufferRead.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}	   
				
				if(isGameModeValid(type))
					break;
			}
			
			
			Game game = newGame();			
			game.getBoard().displayBoard();
			
			Player player = game.getFirstPlayer();
			player.getNextMove(game.getBoard());
			
			game.getBoard().displayBoard();
			while(!isGameWon(game.getBoard()) && !isBoardFull(game.getBoard())) {
				player = game.getNextPlayer();
				player.getNextMove(game.getBoard());
				game.getBoard().displayBoard();
			}	
			
			if(isGameWon(game.getBoard())){
				System.out.println(player.toString()+" won");
			}else {
				System.out.println("Its a Tie!");
			}
			
			//ask user if he wishes to continue
			System.out.println("Do you wish to continue (Y|y to continue) : ");
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    
			try {
				input = bufferRead.readLine().trim();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}while(input.equals("Y") || input.equals("y"));
		
		System.out.println("Game Completed!");
	}
	
	private boolean isBoardFull(Board board) {
		//if game is not won by any player and there are no empty spaces then its a tie		
		int number_of_spaces=0;
		for(int i=0;i<bSize;i++) {
			for(int j=0;j<bSize;j++) {
				if(board.board[i][j]!='X' && board.board[i][j]!='O') {
					number_of_spaces++;
					break;
				}						
			}
		}
		if(number_of_spaces==0) {
			return true;			
		}
		
		return false;
	}

	private boolean isGameWon(Board board) {
		
		//check if any of the straight rows have same elements 
		boolean game_finished = true;
		for(int i=0;i<board.bSize;i++) {
			game_finished=true;
			
			for(int j=0;j<board.bSize;j++) {
				if(board.board[i][j]!='X' && board.board[i][j]!='O') {
					game_finished=false;
					break;
				}
				if(board.board[i][j]!=board.board[i][0]) {
					game_finished = false;
					break;
				}
			}
			
			if(game_finished)
				break;
		}
		
		//check if any of the column has same elements
		if(!game_finished) {
			for(int i=0;i<board.bSize;i++) {
				game_finished=true;
				for(int j=0;j<board.bSize;j++) {
					if(board.board[j][i]!='X' && board.board[j][i]!='O') {
						game_finished=false;
						break;
					}
					if(board.board[j][i]!=board.board[0][i]) {
						game_finished=false;
						break;
					}
				}
				if(game_finished)
					break;
			}
		}
		
		//check if any diagonal has same elements
		if(!game_finished) {
			game_finished=true;
			for(int i=0;i<bSize;i++) {
				if(board.board[i][i]!='X' && board.board[i][i]!='O') {
					game_finished=false;
					break;
				}
				if(board.board[i][i]!=board.board[0][0]) {
					game_finished=false;
					break;
				}
			}
		}
		
		if(!game_finished) {
			game_finished=true;
			for(int i=0;i<bSize;i++) {
				if(board.board[i][bSize-1-i]!='X' && board.board[i][bSize-1-i]!='O') {
					game_finished=false;
					break;
				}
				if(board.board[i][bSize-1-i]!=board.board[0][bSize-1]) {
					game_finished=false;
					break;
				}
			}
		}
		
				
		return game_finished;
	}
}
