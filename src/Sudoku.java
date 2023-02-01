import java.util.Scanner;

public class Sudoku {
    static int[][] grid = new int[9][9];

    static boolean checkRow(int row, int num) {
        for (int col = 0; col < 9; col++)
            if (grid[row][col] == num)
                return false;
        return true;
    }

    static boolean checkCol(int col, int num) {
        for (int row = 0; row < 9; row++)
            if (grid[row][col] == num)
                return false;
        return true;
    }

    static boolean checkBox(int row, int col, int num) {
        int r = row - row % 3;
        int c = col - col % 3;
        for (int i = r; i < r + 3; i++)
            for (int j = c; j < c + 3; j++)
                if (grid[i][j] == num)
                    return false;
        return true;
    }

    static boolean isSafe(int row, int col, int num) {
        return checkRow(row, num) && checkCol(col, num) && checkBox(row, col, num);
    }

    static boolean solveSudoku() {
        int row = -1;
        int col = -1;
        boolean isEmpty = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    row = i;
                    col = j;
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }
        if (isEmpty) {
            return true;
        }
        for (int num = 1; num <= 9; num++) {
            if (isSafe(row, col, num)) {
                grid[row][col] = num;
                if (solveSudoku()) {
                    return true;
                } else {
                    grid[row][col] = 0;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the grid (9x9)");
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                grid[i][j] = sc.nextInt();
            }
        }
        if (solveSudoku()) {
            System.out.println("Solved");
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    System.out.print(grid[i][j] + " ");
                }
                System.out.println();
            }
        } else {
            System.out.println("No solution");
        }
    }
}
