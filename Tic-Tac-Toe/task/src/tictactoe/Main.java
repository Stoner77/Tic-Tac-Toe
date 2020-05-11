package tictactoe;
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    static char[] board;
    static Scanner scanner;

    public static void main(String[] args) {
        // write your code here
        scanner = new Scanner(System.in);
        System.out.print("Enter the cells: ");
        String symbol = scanner.nextLine();
        board = symbol.toCharArray();
        print();
        move();
        print();



//        String result = checkWinner();
//        System.out.println(result);
    }

    static void print(){
        System.out.println("---------");
        System.out.println("| " + board[0] + " " + board[1] + " " + board[2] + " |");
        System.out.println("| " + board[3] + " " + board[4] + " " + board[5] + " |");
        System.out.println("| " + board[6] + " " + board[7] + " " + board[8] + " |");
        System.out.println("---------");

    }

    private static void move() {
        do {
            System.out.println("Enter the coordinates: ");
            String line = scanner.nextLine();
            int column;
            int row;
            try {
                String[] coordinates = line.split("\\s");
                column = Integer.parseInt(coordinates[0]);
                row = Integer.parseInt(coordinates[1]);
                if (column > 3 || column < 1 || row > 3 || row < 1) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("You should enter numbers!");
                continue;
            }
            int index = 8 + column - 3 * row;
            if (board[index] == '_') {
                board[index] = 'X';
                break;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
            }
        } while (true);
    }





    static String checkWinner() {
        int count = 0;
        int winX = 0;
        int winO = 0;
        String line = null;
        boolean end = false;


        line = board[0] + "" + board[1] + "" + board[2];
        if (line.equals("XXX")) {
            winX++;
        }
        else if (line.equals("OOO")) {
            winO++;
        }

        line = board[3] + "" + board[4] + "" + board[5];
        if (line.equals("XXX")) {
            winX++;
        }
        else if (line.equals("OOO")) {
            winO++;
        }

        line = board[6] + "" + board[7] + "" + board[8];
        if (line.equals("XXX")) {
            winX++;
        }
        else if (line.equals("OOO")) {
            winO++;
        }

        line = board[0] + "" + board[3] + "" + board[6];
        if (line.equals("XXX")) {
            winX++;
        }
        else if (line.equals("OOO")) {
            winO++;
        }

        line = board[1] + "" + board[4] + "" + board[7];
        if (line.equals("XXX")) {
            winX++;
        }
        else if (line.equals("OOO")) {
            winO++;
        }

        line = board[2] + "" + board[5] + "" + board[8];
        if (line.equals("XXX")) {
            winX++;
        }
        else if (line.equals("OOO")) {
            winO++;
        }


        line = board[0] + "" + board[4] + "" + board[8];
        if (line.equals("XXX")) {
            winX++;
        }
        else if (line.equals("OOO")) {
            winO++;
        }


        line = board[2] + "" + board[4] + "" + board[6];
        if (line.equals("XXX")) {
            winX++;
        }
        else if (line.equals("OOO")) {
            winO++;
        }

        if (winX == 1 && winO == 0) {
            end = true;
            return "X wins";
        } else if (winO == 1 && winX == 0) {
            end = true;
            return "O wins";
        } else if (((winX > 1) || (winO > 1)) || (winX == 1) && (winO == 1)) {
            end = true;
            return "Impossible";
        }


        int countX = 0;
        int countO = 0;
        for (int i = 0; i < 9; i++) {
            if (board[i] == 'X') {
                countX++;

            } else if (board[i] == 'O') {
                countO++;

            }
        }
        if ((countX + 2 <= countO) || (countO + 2 <= countX) ) {
            end = true;
            return "Impossible";
        }


        for (int a = 0; a < 9; a++) {

            if ((!end) && Arrays.toString(board).contains("_")) {
                return "Game not finished";
            } else if (a == 8) return "Draw";
        }


        return null;
    }
}