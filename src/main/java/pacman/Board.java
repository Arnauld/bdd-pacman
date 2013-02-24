package pacman;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Board {
    private final int nbCols;
    private final int nbRows;
    private final Cell[][] cells;

    public Board(int nbCols, int nbRows) {
        this.nbCols = nbCols;
        this.nbRows = nbRows;
        this.cells = new Cell[nbCols][nbRows];
        for (int col = 0; col < nbCols; col++) {
            for (int row = 0; row < nbRows; row++) {
                cells[col][row] = new Cell();
            }
        }
    }

    public int getNbCols() {
        return nbCols;
    }

    public int getNbRows() {
        return nbRows;
    }

    public void markAsWall(int col, int row) {
        cellAt(col, row).markAsWall();
    }

    private Cell cellAt(int col, int row) {
        return cells[col-1][row-1];
    }

    public boolean isWall(int col, int row) {
        return cellAt(col, row).isWall();
    }

    public boolean hasFood(int col, int row) {
        return cellAt(col, row).hasFood();
    }

    public void markWithFood(int col, int row) {
        cellAt(col, row).markWithFood();
    }

    public static class Cell {
        private boolean wall;
        private boolean hasFood;

        public void markAsWall() {
            this.wall = true;
        }

        public boolean isWall() {
            return wall;
        }

        public void markWithFood() {
            this.hasFood = true;
        }

        public boolean hasFood() {
            return hasFood;
        }
    }
}
