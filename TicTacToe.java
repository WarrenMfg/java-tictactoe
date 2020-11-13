
// import Scanner for user input
import java.util.Scanner;

/**
 * TicTacToe class
 */
public class TicTacToe {
  // public constant
  // public static scanner to be used here and in TestTicTacToe
  public static final Scanner STDIN = new Scanner(System.in);
  // private constants
  // X piece
  private final String X_CHAR = "❌";
  // O piece
  private final String O_CHAR = "⭕️";
  // open location piece
  private final String DASH_CHAR = "➖";
  // tie game feedback
  private final String TIE_GAME = "Tie game!";

  // private fields
  // overall winner (player1, player2, or TIE_GAME)
  private String winner = TIE_GAME;
  // keep track of number of plays (max is 9)
  private int playCount = 0;
  // tic tac toe board
  private String[][] board;
  // variables for default or custom player names
  private String player1;
  private String player2;

  /**
   * Makes a new board
   */
  private void makeBoard() {
    String[][] newBoard = { { DASH_CHAR, DASH_CHAR, DASH_CHAR }, { DASH_CHAR, DASH_CHAR, DASH_CHAR },
        { DASH_CHAR, DASH_CHAR, DASH_CHAR } };

    board = newBoard;
  } // end makeBoard method

  /**
   * Makes a new board using a test board
   */
  private void makeBoard(String[][] testBoard) {
    board = testBoard;
  } // end makeBoard method

  /**
   * Default constructor with default player names. Used for tests.
   */
  public TicTacToe() {
    this("Player 1", "Player 2");
  } // end default constructor

  /**
   * Constructor for custom player names
   *
   * @param player1 - Name of player1
   * @param player2 - Name of player2
   */
  public TicTacToe(String player1, String player2) {
    makeBoard();
    this.player1 = player1;
    this.player2 = player2;
  } // end constructor

  /**
   * Prints 2D array as a string
   */
  public void printBoard() {
    String stringifiedBoard = "\n" + player1 + " vs " + player2 + "\n\n";
    // iterate over board
    for (int i = 0; i < board.length; i++) {
      String[] row = board[i];
      stringifiedBoard += "| ";
      // iterate over row
      for (int j = 0; j < row.length; j++) {
        stringifiedBoard += row[j];
        if (j != row.length - 1) {
          stringifiedBoard += " | ";
        }
      }
      stringifiedBoard += " |\n";
    }
    // print game title and board
    System.out.println("Tic Tac Toe in Java!");
    System.out.println(stringifiedBoard);
  } // end printBoard method

  /**
   * Generic logic to prompt either player
   *
   * @param feedback - general and error feedback
   * @param player   - player1 or player2
   * @param emoji    - the current player's piece (X or O)
   */
  private void promptPlayer(String feedback, String player, String emoji) {
    // print game board
    printBoard();
    // print feedback
    System.out.println(feedback + " " + player + "'s turn...");
    // print and prompt user for location of X or O
    System.out.print("Please choose a row using a zero-based index (0, 1, or 2) >>> ");
    int row = TicTacToe.STDIN.nextInt();
    System.out.print("Please choose a column using a zero-based index (0, 1, or 2) >>> ");
    int col = TicTacToe.STDIN.nextInt();
    // check if user input is in range for the 3x3 board
    boolean areInRange = Math.min(row, col) >= 0 && Math.max(row, col) < board.length;

    // if not in range
    if (!areInRange) {
      // clear console for MacOS
      System.out.println("\033c");
      // try again
      promptPlayer("That location is out of bounds!", player, emoji);

      // else if the location is available
    } else if (board[row][col].equals(DASH_CHAR)) {
      // then place piece (X or O)
      board[row][col] = emoji;

      // otherwise
    } else {
      // clear console for MacOS
      System.out.println("\033c");
      // try again
      promptPlayer("That location is already occupied!", player, emoji);
    }
  } // end promptPlayer method

  /**
   * Logic for playGame and testDetermineWinner to determine if the game has a
   * winner. If winner is determined, assigns winner directly to player1 or
   * player2 variables.
   */
  private void determineWinner() {
    // rows
    // iterate over rows
    for (int i = 0; i < board.length; i++) {
      String[] row = board[i];
      // if current row contains an open location
      if (String.join("", row).contains(DASH_CHAR)) {
        // continue to next row
        continue;
      }
      // if all locations are equal (X or O)
      if (row[0].equals(row[1]) && row[1].equals(row[2])) {
        // then if X
        if (row[0].equals(X_CHAR)) {
          // winner is player1
          winner = player1;
          // otherwise
        } else {
          // winner is player2
          winner = player2;
        }
      }
    }

    // columns
    // iteration to be used as column
    for (int col = 0; col < board.length; col++) {
      // concatenate pieces to a string
      String colString = board[0][col] + board[1][col] + board[2][col];
      // if string contains an open location
      if (colString.contains(DASH_CHAR)) {
        // then continue
        continue;
      }

      // if all locations are equal (X or O)
      if (board[0][col].equals(board[1][col]) && board[1][col].equals(board[2][col])) {
        // then if X
        if (board[0][col].equals(X_CHAR)) {
          // winner is player1
          winner = player1;
          // otherwise
        } else {
          // winner is player2
          winner = player2;
        }
      }
    }

    // forward diagonal
    // concatenate pieces to a string
    String forwardDiagonal = board[2][0] + board[1][1] + board[0][2];
    // if string does not contain an open position
    if (!forwardDiagonal.contains(DASH_CHAR)) {
      // and if all pieces are equal
      if (board[2][0].equals(board[1][1]) && board[1][1].equals(board[0][2])) {
        // then if X
        if (board[2][0].equals(X_CHAR)) {
          // winner is player1
          winner = player1;
          // otherwise
        } else {
          // winner is player2
          winner = player2;
        }
      }
    }

    // backward diagonal
    // concatenate pieces to a string
    String backwardDiagonal = board[0][0] + board[1][1] + board[2][2];
    // if string does not contain an open position
    if (!backwardDiagonal.contains(DASH_CHAR)) {
      // and if all pieces are equal
      if (board[0][0].equals(board[1][1]) && board[1][1].equals(board[2][2])) {
        // then if X
        if (board[0][0].equals(X_CHAR)) {
          // winner is player1
          winner = player1;
          // otherwise
        } else {
          // winner is player2
          winner = player2;
        }
      }
    }
  } // end determineWinner method

  /**
   * Begins TicTacToe game
   */
  public void playGame() {
    // declare local current player variables
    // to toggle between player1 and player2
    String currentPlayer = "";
    String currentChar = "";

    // loop until a total of 9 plays have been conducted
    while (playCount < 9) {
      // logic to toggle between player1 and player2
      if (currentPlayer.equals(player1)) {
        currentPlayer = player2;
        currentChar = O_CHAR;
      } else {
        currentPlayer = player1;
        currentChar = X_CHAR;
      }

      // clear console for MacOS
      System.out.println("\033c");
      // invoke promptPlayer method
      promptPlayer("Go time!", currentPlayer, currentChar);
      // increment playCount
      playCount++;
      // determine if current play is a winning play
      determineWinner();

      // if a winner has been determined
      if (winner.equals(player1) || winner.equals(player2)) {
        // clear console for MacOS
        System.out.println("\033c");
        // print winning board
        printBoard();
        // print winner
        System.out.println("The winner is " + winner + "!\n");
        break;
      }
    }

    // if winner still equals TIE_GAME
    if (winner.equals(TIE_GAME)) {
      // clear console for MacOS
      System.out.println("\033c");
      // print tie game board
      printBoard();
      // print that it is a tie game
      System.out.println(TIE_GAME + "\n");
    }
  } // end playGame method

  /**
   * Tests logic of determineWinner method
   */
  public void testDetermineWinner(String testCase, String[][] testBoard, String expected) {
    // make a new test board
    makeBoard(testBoard);
    // determine winner using test board
    determineWinner();

    // if winner is as expected
    if (winner.equals(expected)) {
      // pass
      System.out.println("✅ " + testCase);
      // otherwise
    } else {
      // fail
      System.out.println("🚨 " + testCase);
    }
  } // end testDetermineWinner method
} // end TicTacToe class