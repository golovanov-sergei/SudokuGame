package sudoku.computationlogic;


import sudoku.domain.Coordinates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static sudoku.domain.SudokuGame.GRID_SIZE;

public class GameGenerator {
    public static int[][] getNewGameGrid() {
        return unsolveGame(getSolvedGame());
    }

    private static int[][] unsolveGame(int[][] solvedGame) {
        Random random = new Random(System.currentTimeMillis());
        boolean solvable = false;
        int[][] solvableArray = new int[GRID_SIZE][GRID_SIZE];
        while (solvable==false){
            SudokuUtilities.copySudokuArrayValues(solvedGame,solvableArray);
            int index =0 ;
            while (index<40){
                int xCoordinate = random.nextInt(GRID_SIZE);
                int yCoordinate = random.nextInt(GRID_SIZE);
                if (solvableArray[xCoordinate][yCoordinate]!=0){
                    solvableArray[xCoordinate][yCoordinate]=0;
                    index++;
                }
            }

            int toBeSolved[][] = new int[GRID_SIZE][GRID_SIZE];
            SudokuUtilities.copySudokuArrayValues(solvableArray,toBeSolved);
            solvable = SudokuSolver.puzzleIsSolvable(toBeSolved);
        }
        return solvableArray;

    }

    private static int[][] getSolvedGame() {
        Random random = new Random(System.currentTimeMillis());
        int[][] newGrid = new int[GRID_SIZE][GRID_SIZE];
        for (int value = 1; value <= GRID_SIZE; value++) {
            int allocations = 0;
            int interrupt = 0;

            List<Coordinates> allocTracker = new ArrayList<>();

            int attempts = 0;

            while (allocations < GRID_SIZE) {
                if (interrupt > 200) {
                    allocTracker.forEach(coord -> {
                        newGrid[coord.getX()][coord.getY()] = 0;
                    });
                    interrupt = 0;
                    allocations = 0;
                    allocTracker.clear();
                    attempts++;
                    if (attempts > 500) {
                        ClearArray(newGrid);
                        attempts = 0;
                        value = 1;
                    }
                }
                int xCoordinate = random.nextInt(GRID_SIZE);
                int yCoordinate = random.nextInt(GRID_SIZE);

                if (newGrid[xCoordinate][yCoordinate]==0){
                    newGrid[xCoordinate][yCoordinate] = value;
                    if (GameLogic.sudokuIsInvalid(newGrid)){
                        newGrid[xCoordinate][yCoordinate]=0;
                        interrupt++;
                    }else {
                        allocTracker.add(new Coordinates(xCoordinate,yCoordinate));
                        allocations++;
                    }
                }
            }
        }

        return newGrid;
    }

    private static void ClearArray(int[][] newGrid) {
        for (int xIndex = 0; xIndex < GRID_SIZE; xIndex++) {
            for (int yIndex = 0; yIndex < GRID_SIZE; yIndex++) {
                newGrid[xIndex][yIndex] = 0;
            }
        }
    }
}
