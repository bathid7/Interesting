import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class User implements Player{

	@Override
	public String toString() {
		return "YOU";
	}

	@Override
	public void getNextMove(Board board) {
		String cell="";
		while(true) {
			System.out.println("Now its your turn.");
			System.out.println("Please select a row from 1 to "+board.board_Size +", column from 1 to "+board.board_Size +" in 'row column' format");
			BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		    try {
				cell = bufferRead.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}	   
		    
		    String[] t = cell.split("\\s+");
		    if(t.length !=2)
		    	continue;
		    
		    int move[]=new int[2];
		    move[0]=Integer.parseInt(t[0]);
		    move[1]=Integer.parseInt(t[1]);
		    
			if(isMoveValid(move, board)) {
				board.board[Integer.parseInt(t[0])-1][Integer.parseInt(t[1])-1]='X';
				break;
			}				
		}		
	}

	@Override
	public boolean isMoveValid(int[] move,Board board) {
		if(move[0]<=board.board_Size && move[0]>0 && move[1]<=board.board_Size && move[1]>0) {
			if(!(board.board[move[0]-1][move[1]-1]=='X' || board.board[move[0]-1][move[1]-1]=='O')) {
				return true;
			}
		}			
		return false;
	}
}
