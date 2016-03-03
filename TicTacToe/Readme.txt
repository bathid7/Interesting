The following is the code flow of our program,

1) The actual execution of the game starts in "TicTacToe.java" file. It contains the main class. In this file the user will be asked to select the board size. Once the user selects valid board size, the control goes to "playGame()" method in "Controller.java" file.

2) In the "Controller.java" file, the play method asks the user to select the desired level he wants to play. It then calls the "displayBoard()" method in "Board.java" file, that displays the board. Then, it calls the "getNextMove()" method in "Player.java" file that asks the user to select a move.

3) The user and computer moves are managed in "User.java" and "Computer.java" files.

4) The winner and tie is calculated in "isGameWon()" and "isBoardFull()" methods in "Controller.java" file.

 