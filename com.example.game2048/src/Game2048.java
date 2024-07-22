import java.util.Random;
import java.util.Scanner;

public class Game2048 {
    private final int SIZE = 4;
    private int[][] board;
    private int score;
    private boolean gameOver;

    public Game2048() {
        board = new int[SIZE][SIZE];
        score = 0;
        gameOver = false;
        initializeBoard();
        displayBoard();
        playGame();
    }

    private void initializeBoard() {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = 0;
            }
        }

        generateRandomTile();
        generateRandomTile();
    }

    private void displayBoard() {
        System.out.println("Score: " + score);
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }

    private void generateRandomTile() {

        Random random = new Random();
        int value = random.nextInt(2) + 1;
        int tileValue = value * 2;
        boolean tilePlaced = false;

        while (!tilePlaced) {
            int row = random.nextInt(SIZE);
            int col = random.nextInt(SIZE);
            if (board[row][col] == 0) {
                board[row][col] = tileValue;
                tilePlaced = true;
            }
        }
    }

    private void moveLeft() {
        for (int i = 0; i < SIZE; i++) {

            for (int j = 0; j < SIZE - 1; j++) {
                if (board[i][j] == 0) {
                    continue;
                }

                for (int k = j + 1; k < SIZE; k++) {
                    if (board[i][k] == 0) {
                        continue;
                    } else if (board[i][j] == board[i][k]) {
                        // Merge tiles
                        board[i][j] *= 2;
                        board[i][k] = 0;
                        score += board[i][j];
                        break;
                    } else {
                        break;
                    }
                }
            }

            int[] newRow = new int[SIZE];
            int index = 0;
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != 0) {
                    newRow[index++] = board[i][j];
                }
            }

            for (int j = 0; j < SIZE; j++) {
                board[i][j] = newRow[j];
            }
        }
    }

    private void moveRight() {

        mirrorBoard();
        moveLeft();
        mirrorBoard();
    }

    private void moveUp() {

        transposeBoard();
        moveLeft();
        transposeBoard();
    }

    private void moveDown() {

        transposeBoard();
        mirrorBoard();
        moveLeft();
        mirrorBoard();
        transposeBoard();
    }

    private void transposeBoard() {
        int[][] transposed = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                transposed[j][i] = board[i][j];
            }
        }
        board = transposed;
    }

    private void mirrorBoard() {
        int[][] mirrored = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                mirrored[i][SIZE - 1 - j] = board[i][j];
            }
        }
        board = mirrored;
    }

    private void playGame() {
        Scanner scanner = new Scanner(System.in);
        while (!gameOver) {
            System.out.println("Enter move (W/A/S/D): ");
            String move = scanner.nextLine().toUpperCase();
            switch (move) {
                case "W":
                    moveUp();
                    break;
                case "A":
                    moveLeft();
                    break;
                case "S":
                    moveDown();
                    break;
                case "D":
                    moveRight();
                    break;
                default:
                    System.out.println("Invalid move! Use W/A/S/D.");
                    continue;
            }
            generateRandomTile();
            displayBoard();
            gameOver = isGameOver();
        }
        System.out.println("Game Over! Your score is: " + score);
        scanner.close();
    }

    private boolean isGameOver() {

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
                if (j < SIZE - 1 && board[i][j] == board[i][j + 1]) {
                    return false;
                }
                if (i < SIZE - 1 && board[i][j] == board[i + 1][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        new Game2048();
    }
}
