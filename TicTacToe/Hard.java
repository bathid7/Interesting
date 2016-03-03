
public class Hard implements Level {

	@Override
	public int[] getNextMove(Board board) {
		
		//check if computer has a win move if yes go with it
		int[] move = nextMove(board,'O','X');
		
		if(move[0]!=-1 && move[1]!=-1)
			return move;
		
		move=nextMove(board,'X','O');
		if(move[0]!=-1 && move[1]!=-1)
			return move;
		
		
		//check if the center is empty
		int board_size=board.board_Size;
		int centerX,centerY;
		if(board_size%2==1)
			centerX = centerY = (int)Math.floor(board_size/2);
		else
			centerX=centerY=board_size/2 -1;
		
		if(board.isCellEmpty(centerX,centerY)) {
			move[0]=centerX;
			move[1]=centerY;			
		}
		if(move[0]!=-1 && move[1]!=-1)
			return move;
		
		
		//check if opposite corner is empty
		if(board.isCellEmpty(0, 0) && board.board[board_size-1][board_size-1]=='X') {
			move[0] = move[1] = 0;
		}
		else if(board.isCellEmpty(board_size-1,board_size-1) && board.board[0][0]=='X') {
			move[0] = move[1] = board_size-1;
		}
		else if(board.isCellEmpty(0,board_size-1) && board.board[board_size-1][0]=='X') {
			move[0] = 0;
			move[1] = board_size-1;
		}else if(board.isCellEmpty(board_size-1,0) && board.board[0][board_size-1]=='X') {
			move[0] = board_size-1;
			move[1] = 0;			
		}
		if(move[0]!=-1 && move[1]!=-1)
			return move;		
				
		//check any empty corner
		if(board.isCellEmpty(0, 0)) 
			move[0]=move[1]=0;
		else if(board.isCellEmpty(0, board_size-1)) {
			move[0]=0;
			move[1]=board_size-1;
		}
		else if(board.isCellEmpty(board_size-1, 0)) {
			move[0]=board_size-1;
			move[1]=0;
		}
		else if(board.isCellEmpty(board_size-1,board_size-1))
			move[0]=move[1]=board_size-1;
		if(move[0]!=-1 && move[1]!=-1)
			return move;
		
		//check any empty side and move computer in middle of edge
		int empty_row=-1;
		for(int i=0;i<board_size;i++) {
			boolean isEmpty = true;
			for(int j=0;j<board_size;j++) {
				if(!board.isCellEmpty(i, j)) {
					isEmpty = false;
					break;
				}
			}
			if(isEmpty) {
				empty_row=i;
				break;
			}
		}
		
		if(empty_row!=-1) {
			move[0]=empty_row;
			move[1]=(int)Math.floor(board_size/2);
		}
		if(move[0]!=-1 && move[1]!=-1)
			return move;
		
		int empty_column=-1;
		for(int i=0;i<board_size;i++) {
			boolean isEmpty = true;
			for(int j=0;j<board_size;j++) {
				if(!board.isCellEmpty(j, i)) {
					isEmpty = false;
					break;
				}
			}
			if(isEmpty) {
				empty_column=i;
				break;
			}
		}
		
		if(empty_column!=-1) {
			move[0]=(int)Math.floor(board_size/2);
			move[1]=empty_column;
		}
		if(move[0]!=-1 && move[1]!=-1)
			return move;
		
		boolean found=false;
		for(int i=0;i<board_size;i++) {
			for(int j=0;j<board_size;j++) {
				if(board.isCellEmpty(i, j)) {
					found=true;
					move[0]=i;
					move[1]=j;
					break;
				}
			}
			if(found)
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
}
