package ru.kubsu.app;

import android.content.Context;
import android.widget.Chronometer;

import ru.kubsu.view.sudokugrid.GameGrid;

public class GameEngine {

    private static GameEngine instance;
    private Context context;

    private GameGrid grid = null;


    private int selectedPosX = -1, selectedPosY = -1;

    private GameEngine() {
    }

    public static GameEngine getInstance() {
        if (instance == null)
            instance = new GameEngine();
        return instance;
    }

    public void createGrid(Context context, int level) {
        int[][] Sudoku = SudokuGenerator.getInstance().generateGrid();
        Sudoku = SudokuGenerator.getInstance().removeElements(Sudoku, level);
        grid = new GameGrid(context);
        grid.setGrid(Sudoku);
        this.context = context;
    }

    public GameGrid getGrid() {
        return grid;
    }

    public void setSelectedPosition(int x, int y) {
        selectedPosX = x;
        selectedPosY = y;
    }

    public void setNumber(int number) {
        if (selectedPosX != -1 && selectedPosY != -1) {
            grid.setItem(selectedPosX, selectedPosY, number);
        }


    }

    public boolean checkGrid() {
        return grid.checkGame();

    }

}
