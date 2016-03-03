

public class Game {

	public Player player1;
	public Player player2;
	public Board board;
	public int turns=0;
	
	public Game(Player player1, Player player2, Board board) {
		super();
		this.player1 = player1;
		this.player2 = player2;
		this.board = board;
	}


	public Board getBoard() {
		return board;
	}
	
	public Player getFirstPlayer() {
		turns++;
		return player1;
	}
	
	public Player getNextPlayer() {
		turns++;
		if(turns%2==0) {
			return player2;
		}
		else {
			return player1;
		}		
	}
	
	public Player getCurrentPlayer() {
		if(turns%2==1) {
			return player2;
		}
		else {
			return player1;
		}		
	}
}
