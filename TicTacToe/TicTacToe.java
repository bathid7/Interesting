
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TicTacToe {

	public static void main(String[] args)throws IOException {
		
		Controller game = new Controller();		
		int size;
		System.out.println("Tic-Tac-Toe!");
		while(true) 
		{
			System.out.println("Please enter the size of game board: ");
			BufferedReader data = new BufferedReader(new InputStreamReader(System.in));	    
			try 
		    {
		    	size = Integer.parseInt(data.readLine());
				} 
		    catch (IOException e) 
		    {
					System.out.println("Please enter a valid number");
				}	
				if(game.isBoardValid(size))
					break;
		}
		game.playGame();	
	}
}
