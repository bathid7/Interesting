public class Medium implements Level {

	@Override
	public int[] getNextMove(Board board) {

		int[] move = nextMove(board,'O','X');

		if(move[0]!=-1 && move[1]!=-1)
			return move;
		
		move=nextMove(board,'X','O');
		if(move[0]!=-1 && move[1]!=-1)
			return move;
		//check if we have any move that is adjacent to already existing elements
		
		int size = board.board_Size;

		int counter;

		// check number of empty spaces
		int number_of_spaces = 0;
		for (int i = 0; i < board.board_Size; i++) {
			for (int j = 0; j < board.board_Size; j++) {
				if (!(board.board[i][j] == 'X' || board.board[i][j] == 'O'))
					number_of_spaces++;
			}
		}

		counter = number_of_spaces;

		while (counter > 0) {
			counter--;
			move[0] = (int) (Math.random() * size);
			move[1] = (int) (Math.random() * size);

			// validate computer move
			if (isAdjWinMove(move, board))
				return move;
		}

		while (true) {
			move[0] = (int) (Math.random() * size);
			move[1] = (int) (Math.random() * size);

			// validate computer move
			if (validateMove(move, board))
				break;
		}

		return move;
	}

	private int[] nextMove(Board board,char player,char opponent) {
		// checking if in any row computer can win in next move
		boolean has_next_win_move = true;
		int move[] = new int[2];
		for (int i = 0; i < board.board_Size; i++) {
			has_next_win_move = true;
			int number_of_spaces = 0;
			int moveX = i;
			int moveY = 0;
			for (int j = 0; j < board.board_Size; j++) {
				if (board.board[i][j] == opponent || number_of_spaces > 1) {
					has_next_win_move = false;
					break;
				}
				if (!(board.board[i][j] == player)) {
					moveY = j;
					if (++number_of_spaces > 1) {
						has_next_win_move = false;
						break;
					}

				}
			}
			if (has_next_win_move) {
				move[0] = moveX;
				move[1] = moveY;
				break;
			}
		}

		// checking if in any column computer can win in next move
		if (!has_next_win_move) {
			has_next_win_move = true;
			for (int i = 0; i < board.board_Size; i++) {
				has_next_win_move = true;
				int number_of_spaces = 0;
				int moveX = 0;
				int moveY = i;
				for (int j = 0; j < board.board_Size; j++) {
					if (board.board[j][i] == opponent || number_of_spaces > 1) {
						has_next_win_move = false;
						break;
					}
					if (!(board.board[j][i] == player)) {
						moveX = j;
						if (++number_of_spaces > 1) {
							has_next_win_move = false;
							break;
						}
					}
				}
				if (has_next_win_move) {
					move[0] = moveX;
					move[1] = moveY;
					break;
				}
			}
		}

		// checking if in diagonal1 computer can win in next move
		if (!has_next_win_move) {
			has_next_win_move = true;
			int number_of_spaces = 0;
			int moveX = 0;
			int moveY = 0;
			for (int i = 0; i < board.board_Size; i++) {
				if (board.board[i][i] == opponent || number_of_spaces > 1) {
					has_next_win_move = false;
					break;
				}
				if (!(board.board[i][i] == player)) {
					moveX = i;
					moveY = i;
					if (++number_of_spaces > 1) {
						has_next_win_move = false;
						break;
					}
				}
			}
			if (has_next_win_move) {
				move[0] = moveX;
				move[1] = moveY;
			}
		}

		// checking if in diagonal2 computer can win in next move
		if (!has_next_win_move) {
			has_next_win_move = true;
			int number_of_spaces = 0;
			int moveX = 0;
			int moveY = 0;
			for (int i = 0; i < board.board_Size; i++) {
				if (board.board[i][board.board_Size - 1 - i] == opponent
						|| number_of_spaces > 1) {
					has_next_win_move = false;
					break;
				}
				if (!(board.board[i][board.board_Size - 1 - i] == player)) {
					moveX = i;
					moveY = board.board_Size - 1 - i;
					if (++number_of_spaces > 1) {
						has_next_win_move = false;
						break;
					}
				}
			}

			if (has_next_win_move) {
				move[0] = moveX;
				move[1] = moveY;
			}
		}

		// returning random move if no next win move
		if (!has_next_win_move) {
			move[0] = -1;
			move[1] = -1;
		}
		return move;		
	}

	public boolean isAdjWinMove(int[] move, Board board) {

		if (!(board.board[move[0]][move[1]] == 'X' || board.board[move[0]][move[1]] == 'O')) {
			for (int i = move[0] - 1; i <= move[0] + 1; i++) {
				for (int j = move[1] - 1; j <= move[1] + 1; j++) {
					if (i >= 0 && j >= 0 && i <= board.board_Size - 1
							&& j <= board.board_Size - 1 && i != j
							&& isWinMove(i, j, board))
						return true;
				}
			}
		}

		return false;
	}

	public boolean validateMove(int[] move, Board board) {

		if (!(board.board[move[0]][move[1]] == 'X' || board.board[move[0]][move[1]] == 'O')) {
			return true;
		}

		return false;
	}

	public boolean isWinMove(int moveX, int moveY, Board board) {
		if (board.board[moveX][moveY] == 'O')
			return true;

		return false;
	}

}
