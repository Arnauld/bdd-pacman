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

    public void markWithPacGum(int col, int row) {
        cellAt(col, row).markWithPacGum();
    }

    public void placeProtagonist(int col, int row, Protagonist protagonist) {
        cellAt(col, row).placeProtagonist(protagonist);
    }

    public Protagonist getProgonistAt(int col, int row) {
        return cellAt(col, row).getProtagonist();
    }

    public boolean hasPacGum(int col, int row) {
        return cellAt(col, row).hasPacGum();
    }

    public void move(Protagonist protagonist, Direction direction) {
        Coord coord = lookupCoordOf(protagonist);
        if(coord == null) {
            throw new IllegalStateException("Protagonist '" + protagonist + "' not found");
        }
        Coord nextCoord = coord.apply(direction);
        Cell nextCell = cellAt(nextCoord.col, nextCoord.row);
        if(nextCell.isWall())
            return;

        nextCell.placeProtagonist(protagonist);
        Cell cell = cellAt(coord.col, coord.row);
        cell.placeProtagonist(null);
    }

    private Coord lookupCoordOf(Protagonist protagonist) {
        for(int col=1; col<=nbCols; col++) {
            for(int row=1; row <= nbRows; row++) {
                if(cellAt(col, row).getProtagonist() == protagonist)
                    return new Coord(col, row);
            }
        }
        return null;
    }

    public static class Cell {
        private boolean wall;
        private boolean hasFood;
        private boolean hasPacGum;
        private Protagonist protagonist;

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

        public void markWithPacGum() {
            this.hasPacGum = true;
        }

        public boolean hasPacGum() {
            return hasPacGum;
        }

        public void placeProtagonist(Protagonist protagonist) {
            this.protagonist = protagonist;
        }

        public Protagonist getProtagonist() {
            return protagonist;
        }
    }
}
