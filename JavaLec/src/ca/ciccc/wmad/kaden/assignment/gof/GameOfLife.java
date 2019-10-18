package ca.ciccc.wmad.kaden.assignment.gof;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GameOfLife implements Runnable {

    private static final int INIT_ROW = 10, INIT_COL = 10, INIT_INTERVAL = 500;
    private final double INIT_PROBABILITY = 0.2;

    private static int numOfRepeat = 0;

    private boolean[][] cells;
    private int row, col;
    private int interval;

    private String hrString = "";
    private boolean isInitialized;

    public GameOfLife() {
        this(INIT_ROW, INIT_COL, INIT_INTERVAL);
    }

    public GameOfLife(int row, int col) {
        this(row, col, INIT_INTERVAL);
    }

    public GameOfLife(int row, int col, int interval) {
        this.row = (row >= 3 && row <= 100) ? row : INIT_ROW;
        this.col = (col >= 3 && col <= 1000) ? col : INIT_COL;
        this.interval = (interval >= 10 && interval <= 10000) ? interval : INIT_INTERVAL;

        cells = new boolean[row][col];
        for (int i = 0; i < col; ++i) {
            hrString += "=";
        }
    }

    public void setAliveCells(ArrayList<Point> aliveCells) {
        for (Point aliveCell : aliveCells) {
            if (aliveCell.y < row && aliveCell.x < col) {
                cells[aliveCell.y][aliveCell.x] = true;
            }
        }
        isInitialized = true;
    }

    public void setAliveCellsRandomly(double probability) {
        Random random = new Random(System.currentTimeMillis());
        probability = (probability >= 0 && probability <= 1) ? probability : INIT_PROBABILITY;

        for (int i = 0; i < this.row; ++i) {
            for (int j = 0; j < this.col; ++j) {
                cells[i][j] = random.nextDouble() < probability;
            }
        }

        isInitialized = true;
    }

    public boolean[][] getNextGeneration() {
        boolean[][] newCells = new boolean[row][col];
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                int aliveNeighbors = checkAliveNeighbors(i, j);
                if (cells[i][j]) {
                    if ((aliveNeighbors < 2) || (aliveNeighbors > 3)) {
                        newCells[i][j] = false;
                    } else {
                        newCells[i][j] = cells[i][j];
                    }
                } else {
                    if (aliveNeighbors == 3) {
                        newCells[i][j] = true;
                    } else {
                        newCells[i][j] = cells[i][j];
                    }
                }
            }
        }
        return newCells;
    }

    private int checkAliveNeighbors(int row, int col) {
        int aliveNeighbors = 0;
        for (int i = -1; i <= 1; ++i) {
            // Avoid Out of Index
            if ((row + i < 0) || (row + i >= this.row)) {
                continue;
            }
            for (int j = -1; j <= 1; ++j) {
                // Avoid Out of Index
                if ((col + j < 0) || (col + j >= this.col)) {
                    continue;
                }
                // Don't check alive itself
                if ((i == 0) && (j == 0)) {
                    continue;
                }
                if (cells[row + i][col + j]) {
                    aliveNeighbors++;
                }
            }
        }

        return aliveNeighbors;
    }

    // Start: for Console print out
    private void printWholeCells() {
        clearConsole();
        System.out.println(hrString);
        System.out.println("Index: " + numOfRepeat++);
        System.out.println(hrString);
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                System.out.print((cells[i][j]) ? "*" : " ");
            }
            System.out.println();
        }
        System.out.println(hrString);
    }

    private void clearConsole() {
        for (int i = 0; i < 100; ++i) {
            System.out.println();
        }
    }
    // End: for Console print out

    @Override
    public void run() {
        if (!isInitialized) {
            setAliveCellsRandomly(INIT_PROBABILITY);
        }

        do {
            printWholeCells();
            new Thread(() -> {
                this.cells = getNextGeneration();
            }).start();
            try {
                Thread.sleep(this.interval);
            } catch (InterruptedException e) {
                break;
            }
        } while (!Thread.currentThread().isInterrupted());

    }

    public static void main(String[] args) {
        int row = 30, col = 50, interval = 500;
        double probability = 0.25;

        if (args.length != 0) {
            try {
                if (args.length < 4) {
                    throw new Exception();
                }

                row = Integer.parseInt(args[0]);
                col = Integer.parseInt(args[1]);
                interval = Integer.parseInt(args[2]);
                probability = Double.parseDouble(args[3]);
            } catch (Exception e) {
                System.out.println("Usage: java -jar GameOfLife.jar [Row(3~100)] [Column(3~1000)] [Interval(10~10000)] [Probability(0~1)]");
                System.out.println("       If any value exceeds the range, it will be set by default");
                System.out.println("Example: java -jar GameOfLife.jar 65 270 30 0.2");
                return;
            }
        }

        GameOfLife gameOfLife = new GameOfLife(row, col, interval);
        gameOfLife.setAliveCellsRandomly(probability);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press [Enter] to start, and Press [Enter] again to stop");
        scanner.nextLine();

        Thread runGame = new Thread(gameOfLife);
        runGame.start();

        scanner.nextLine();
        runGame.interrupt();
        scanner.close();
    }
}
