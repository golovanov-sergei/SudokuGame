package sudoku.computationlogic;

import sudoku.domain.SudokuGame;

public class SudokuUtilities {
    public static void copySudokuArrayValues(int[][] oldArray, int[][] newArray) {
        for (int xIndex = 0; xIndex < SudokuGame.GRID_SIZE; xIndex++) {
            for (int yIndex = 0; yIndex < SudokuGame.GRID_SIZE; yIndex++) {
                newArray[xIndex][yIndex] = oldArray[xIndex][yIndex];
            }
        }
    }
    public static int[][] copyToNewArray(int[][] oldArray){
        int[][] newArray = new int[SudokuGame.GRID_SIZE][SudokuGame.GRID_SIZE];
        for (int xIndex = 0; xIndex < SudokuGame.GRID_SIZE; xIndex++) {
            for (int yIndex = 0; yIndex < SudokuGame.GRID_SIZE; yIndex++) {
                newArray[xIndex][yIndex] = oldArray[xIndex][yIndex];
            }
        }
        return newArray;
    }
}
