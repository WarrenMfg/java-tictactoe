/**
 * TestTicTacToe class
 */
public class TestTicTacToe {

  /**
   * Point-of-entry - main method
   */
  public static void main(String[] args) {
    // clear console for MacOS
    System.out.println("\033c");

    // print title
    System.out.println("Tic Tac Toe in Java!\n");

    // print and prompt for play mode or test mode
    System.out.print("Play mode or test mode? (Enter 'play' or 'test') >>> ");
    String mode = TicTacToe.STDIN.nextLine();

    // if 'play' mode, prompt for player names and playgame
    if (mode.equals("play")) {
      // print and prompt for player names
      System.out.print("Player 1's name >>> ");
      String player1Name = TicTacToe.STDIN.nextLine();
      System.out.print("Player 2's name >>> ");
      String player2Name = TicTacToe.STDIN.nextLine();

      // instantiate an instance of TicTacToe with player names
      TicTacToe game = new TicTacToe(player1Name, player2Name);
      // play game
      game.playGame();

      // otherwise, if 'test' mode
    } else if (mode.equals("test")) {
      // instantiate an instance of TicTacToe with default names
      TicTacToe game = new TicTacToe();

      // test determineWinner logic for:
      // row
      String[][] row = { { "❌", "❌", "⭕️" }, { "❌", "❌", "➖" }, { "⭕️", "⭕️", "⭕️" } };
      game.testDetermineWinner("1. it should determine a winner for a row", row, "Player 2");

      // column
      String[][] col = { { "⭕️", "❌", "❌" }, { "❌", "⭕️", "❌" }, { "⭕️", "⭕️", "❌" } };
      game.testDetermineWinner("2. it should determine a winner for a column", col, "Player 1");

      // forward diagonal
      String[][] forwardDiagonal = { { "➖", "❌", "⭕️" }, { "❌", "⭕️", "❌" }, { "⭕️", "⭕️", "❌" } };
      game.testDetermineWinner("3. it should determine a winner for a forward diagonal", forwardDiagonal, "Player 2");

      // backard diagonal
      String[][] backwardDiagonal = { { "❌", "⭕️", "⭕️" }, { "⭕️", "❌", "⭕️" }, { "➖", "❌", "❌" } };
      game.testDetermineWinner("4. it should determine a winner for a backward diagonal", backwardDiagonal, "Player 1");

      // end with new line
      System.out.println("");

      // otherwise, neither 'play' nor 'test' mode
    } else {
      System.out.println("No mode with that name... 🤷");
    }
  } // end main method
} // end TestTicTacToe class
