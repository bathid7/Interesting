
public class Board {

	public char board[][];
	public int board_Size;

	public Board(int board_Size) 
	{
		super();
		this.board = new char[board_Size][board_Size];
		this.board_Size = board_Size;
	}
	
	public void displayBoard() 
	{	
		System.out.print("  ");
		
		for(int i=1;i<=board_Size;i++)
			System.out.print(i+" ");
		
		System.out.println();
		System.out.print(" ");
		
		for(int i=1;i<=board_Size;i++)
			System.out.print("+-");
		
		System.out.println("+");
		
		for(int i=1;i<=board_Size;i++) 
		{
			System.out.print(i+"|");
			
			for(int j=1;j<=board_Size;j++) 
			{
				System.out.print(board[i-1][j-1]+"|");
			}
			
			System.out.println();
			System.out.print(" ");
			
			for(int k=1;k<=board_Size;k++)
				System.out.print("+-");
			
			System.out.println("+");
		}		
	}
	
	public boolean isCellEmpty(int x,int y) 
	{
		if(board[x][y]!='X' && board[x][y]!='O') 
		{
			return true;
		}
		return false;
	}
}
