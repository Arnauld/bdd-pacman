package pacman;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Board {
    private final int nbCols;
    private final int nbRows;
    private final Cell[][] cells;
    private Map<CreatureType, Creature> creatures;

    public Board(int nbCols, int nbRows) {
        this.nbCols = nbCols;
        this.nbRows = nbRows;
        this.cells = new Cell[nbCols][nbRows];
        for (int col = 0; col < nbCols; col++) {
            for (int row = 0; row < nbRows; row++) {
                cells[col][row] = new Cell();
            }
        }
        this.creatures = new HashMap<CreatureType, Creature>();
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

    public void placeProtagonist(int col, int row, CreatureType creatureType) {
        creature(creatureType).teleportTo(new Coord(col, row));
    }

    private Creature creature(CreatureType creatureType) {
        Creature creature = creatures.get(creatureType);
        if(creature == null) {
            creature = new Creature(creatureType);
            creatures.put(creatureType, creature);
        }
        return creature;
    }

    public Creature getCreatureAt(int col, int row) {
        for(Creature creature : creatures.values()) {
            if(creature.isLocatedAt(col, row))
                return creature;
        }
        return null;
    }

    public boolean hasPacGum(int col, int row) {
        return cellAt(col, row).hasPacGum();
    }

    public void move(CreatureType creatureType, Direction direction) {
        Creature creature = creature(creatureType);

        Coord coord = creature.getCoord();
        if(coord == null) {
            throw new IllegalStateException("CreatureType '" + creatureType + "' not placed!");
        }
        Coord nextCoord = coord.apply(direction);
        Cell nextCell = cellAt(nextCoord.col, nextCoord.row);
        if(nextCell.isWall())
            return;
        creature.moveTo(nextCoord);
    }

    public static class Cell {
        private boolean wall;
        private boolean hasFood;
        private boolean hasPacGum;

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
    }
}
