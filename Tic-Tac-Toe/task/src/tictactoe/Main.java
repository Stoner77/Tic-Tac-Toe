package tictactoe;
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    static Scanner scanner;

    public static void main(String[] args) {
        // write your code here
        scanner = new Scanner(System.in);
        Dice[] dices = new Dice[9];
        Arrays.fill(dices, Dice.FREE);
        Dice playing = Dice.FREE;
        Status gameStatus;

        do {
            playing = playing.opposite();
            print(dices);
            move(dices, playing);
            gameStatus = check(dices, playing);
        } while (gameStatus == Status.CONTINUE);

        print(dices);

        if (gameStatus == Status.WIN) {
            System.out.println(playing + " wins");
        } else {
            System.out.println("Draw");
        }
    }

    static void print(Dice[] dices){
        System.out.println("---------");
        System.out.println("| " + dices[0] + " " + dices[1] + " " + dices[2] + " |");
        System.out.println("| " + dices[3] + " " + dices[4] + " " + dices[5] + " |");
        System.out.println("| " + dices[6] + " " + dices[7] + " " + dices[8] + " |");
        System.out.println("---------");

    }

    private static void move(Dice[] dices, Dice dice) {
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
            if (dices[index] == Dice.FREE) {
                dices[index] = dice;
                break;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
            }
        } while (true);
    }
    private static Status check(Dice[] dices, Dice dice) {
        if (win(dices, dice)) {
            return Status.WIN;
        } else if (!gotEmpty(dices)) {
            return Status.DRAW;
        }
        return Status.CONTINUE;
    }
    public static boolean gotEmpty(Dice[] dices) {
        for (Dice stone : dices) {
            if (stone == Dice.FREE) {
                return true;
            }
        }
        return false;
    }

    public static boolean win(Dice[] dices, Dice dice) {
        boolean horizontal = (dice == dices[0] && dice == dices[1] && dice == dices[2])
                ||
                (dice == dices[3] && dice == dices[4] && dice == dices[5])
                ||
                (dice == dices[6] && dice == dices[7] && dice == dices[8]);
        boolean vertical = (dice == dices[0] && dice == dices[3] && dice == dices[6])
                ||
                (dice == dices[1] && dice == dices[4] && dice == dices[7])
                ||
                (dice == dices[2] && dice == dices[5] && dice == dices[8]);
        boolean diagonal = (dice == dices[0] && dice == dices[4] && dice == dices[8])
                ||
                (dice == dices[2] && dice == dices[4] && dice == dices[6]);
        return horizontal || vertical || diagonal;
    }


    enum Dice {
        X {
            @Override
            public String toString() {
                return "X";
            }

            @Override
            public Dice opposite() {
                return O;
            }
        },
        O {
            @Override
            public String toString() {
                return "O";
            }

            @Override
            public Dice opposite() {
                return X;
            }
        },
        FREE {
            @Override
            public String toString() {
                return " ";
            }

            @Override
            public Dice opposite() {
                return X;
            }
        };

        public abstract Dice opposite();

    }
    enum Status {
        WIN,
        DRAW,
        CONTINUE
    }

}
