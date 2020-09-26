package tictactoe;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        char[][] cells = new char[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                cells[i][j] = ' ';
            }
        }

        short xCount;
        short oCount;
        short spareCount;
        char wins;
        boolean xWins = false;
        boolean oWins = false;
        boolean impossible = false;
        char xo = 'O';

        String moveXY;
        int xCoordinate;
        int yCoordinate;
        boolean invalid;

        showField(cells);

        do {
            xo = (xo == 'O') ? 'X' : 'O';

            do {
                invalid = false;

                System.out.print("Enter the coordinates: ");
                moveXY = scanner.nextLine();

                if (moveXY.matches("\\d \\d")) {
                    xCoordinate = Integer.parseInt(moveXY, 0, 1, 10) - 1;
                    yCoordinate = Integer.parseInt(moveXY, 2, 3, 10) - 1;
                    if (xCoordinate >= 0 && xCoordinate < 3 && yCoordinate >= 0 && yCoordinate < 3) {
                        if (cells[xCoordinate][yCoordinate] == '_' || cells[xCoordinate][yCoordinate] == ' ') {
                            cells[xCoordinate][yCoordinate] = xo;
                        } else {
                            invalid = true;
                            System.out.println("This cell is occupied! Choose another one!");
                        }
                    } else {
                        invalid = true;
                        System.out.println("Coordinates should be from 1 to 3!");
                    }

                } else {
                    invalid = true;
                    System.out.println("You should enter numbers!");
                }
            } while (invalid);

            xCount = 0;
            oCount = 0;
            spareCount = 0;

            for (int y = 2; y >= 0; y--) {
                for (int x = 0; x < 3; x++) {

                    switch (cells[x][y]) {
                        case 'X':
                            xCount++;
                            break;
                        case 'O':
                            oCount++;
                            break;
                        case ' ':
                        case '_':
                            spareCount++;
                            break;
                        default:
                            impossible = true;
                    }
                }
            }

            if (Math.abs(xCount - oCount) > 1) {
                impossible = true;
            }


            if (!impossible) {
                byte j = 0;
                while (j < 3) {
                    if (cells[j][0] == cells[j][1] && cells[j][1] == cells[j][2] && cells[j][0] != ' ' && cells[j][0] != '_') {
                        wins = cells[j][0];
                        if (wins == 'X') {
                            xWins = true;
                        } else {
                            oWins = true;
                        }
                    }
                    if (cells[0][j] == cells[1][j] && cells[1][j] == cells[2][j] && cells[0][j] != ' ' && cells[0][j] != '_') {
                        wins = cells[0][j];
                        if (wins == 'X') {
                            xWins = true;
                        } else {
                            oWins = true;
                        }
                    }
                    j++;
                }

                if ((cells[0][0] == cells[1][1] && cells[0][0] == cells[2][2] && cells[0][0] != ' ' && cells[0][0] != '_') ||
                        (cells[0][2] == cells[1][1] && cells[0][2] == cells[2][0] && cells[0][2] != ' ' && cells[0][2] != '_')) {
                    wins = cells[1][1];
                    if (wins == 'X') {
                        xWins = true;
                    } else {
                        oWins = true;
                    }
                }

                if (xWins && oWins) {
                    impossible = true;
                }
            }

            showField(cells);


            if (impossible) {
                System.out.println("Impossible");
                running = false;
            } else if (xWins) {
                System.out.println("X wins");
                running = false;
            } else if (oWins) {
                System.out.println("O wins");
                running = false;
            } else if (spareCount == 0) {
                System.out.println("Draw");
                running = false;
            }

        } while (running);

    }

    public static void showField(char[][] cells) {
        System.out.println("---------");
        System.out.println("| " + cells[0][2] + " " + cells[1][2] + " " + cells[2][2] + " |");
        System.out.println("| " + cells[0][1] + " " + cells[1][1] + " " + cells[2][1] + " |");
        System.out.println("| " + cells[0][0] + " " + cells[1][0] + " " + cells[2][0] + " |");
        System.out.println("---------");
    }
}
