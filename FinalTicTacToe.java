import java.util.Scanner;

class FinalTicTacToe {
    private static final int BOARD_SIZE = 3;
    private final char[][] board;
    private char currentPlayer;
    private final String playerXName;
    private final String playerOName;

    public FinalTicTacToe(String playerXName, String playerOName) {
        board = new char[BOARD_SIZE][BOARD_SIZE];
        currentPlayer = 'X';
        this.playerXName = playerXName;
        this.playerOName = playerOName;
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                board[row][col] = ' ';
            }
        }
    }

    public void printBoard() {
        System.out.println("Tic-Tac-Toe");
        System.out.println("   A B C");
        for (int row = 0; row < BOARD_SIZE; row++) {
            System.out.print((row + 1) + " ");
            for (int col = 0; col < BOARD_SIZE; col++) {
                System.out.print("|" + board[row][col]);
            }
            System.out.println("|");
        }
    }

    public boolean isBoardFull() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                if (board[row][col] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean makeMove(int row, int col) {
        if (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == ' ') {
            board[row][col] = currentPlayer;
            if (checkWin()) {
                printBoard();
                System.out.println("Player " + currentPlayer + " (" + (currentPlayer == 'X' ? playerXName : playerOName) + ") wins!");
                return true;
            } else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                return true;
            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            return true;
        }
        return false;
    }

    public boolean checkWin() {
        return checkRows() || checkColumns() || checkDiagonals();
    }

    private boolean checkRows() {
        for (int row = 0; row < BOARD_SIZE; row++) {
            if (board[row][0] != ' ' && board[row][0] == board[row][1] && board[row][1] == board[row][2]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        for (int col = 0; col < BOARD_SIZE; col++) {
            if (board[0][col] != ' ' && board[0][col] == board[1][col] && board[1][col] == board[2][col]) {
                return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals() {
        return (board[0][0] != ' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) ||
               (board[0][2] != ' ' && board[0][2] == board[1][1] && board[1][1] == board[2][0]);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic-Tac-Toe!");
        String playAgain;
        do {
            System.out.print("Enter Player X's name: ");
            String playerXName = scanner.nextLine();
            System.out.print("Enter Player O's name: ");
            String playerOName = scanner.nextLine();

            FinalTicTacToe game = new FinalTicTacToe(playerXName, playerOName);

            while (true) {
                game.printBoard();
                System.out.println(playerXName + " (X) vs. " + playerOName + " (O)");
                System.out.println("Current player: " + game.currentPlayer);
                System.out.print("Enter your move (e.g., A1, B2, C3): ");
                String move = scanner.next().toUpperCase();

                if (move.length() == 2 && move.matches("[ABC][123]")) {
                    int row = move.charAt(1) - '1';
                    int col = move.charAt(0) - 'A';

                    if (game.makeMove(row, col)) {
                        if (game.checkWin()) {
                            game.printBoard();
                            System.out.println("Player " + game.currentPlayer + " (" + (game.currentPlayer == 'X' ? playerXName : playerOName) + ") wins!");
                            break;
                        } else if (game.isBoardFull()) {
                            game.printBoard();
                            System.out.println("It's a draw!");
                            break;
                        }
                    } else {
                        System.out.println("Invalid move. Please choose an empty cell.");
                    }
                } else {
                    System.out.println("Invalid input format. Please use A1, B2, C3, etc.");
                }
            }
            System.out.print("Do you want to play again? (Yes/No): ");
            playAgain = scanner.next().toLowerCase();
            scanner.nextLine();
        } while (playAgain.equals("yes"));
        System.out.println("Thank You For Playing !!");
    }
}
