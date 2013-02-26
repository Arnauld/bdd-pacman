package pacman;

/**
* @author <a href="http://twitter.com/aloyer">@aloyer</a>
*/
public class Coord {
    public final int col, row;

    public Coord(int col, int row) {
        this.col = col;
        this.row = row;
    }

    public Coord apply(Direction direction) {
        switch (direction) {
            case UP:
                return new Coord(col, row-1);
            case DOWN:
                return new Coord(col, row+1);
            case LEFT:
                return new Coord(col-1, row);
            case RIGHT:
                return new Coord(col+1, row);
            default:
                throw new IllegalArgumentException("Direction '" + direction + "'not supported");
        }
    }

    public boolean sameAs(int col, int row) {
        return this.col == col && this.row == row;
    }
}
