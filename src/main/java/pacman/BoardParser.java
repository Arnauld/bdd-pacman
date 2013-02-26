package pacman;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class BoardParser {

    // └┘┐┌│─□
    public static final char BR = '┘';
    public static final char BL = '└';
    public static final char TR = '┐';
    public static final char TL = '┌';
    public static final char VT = '│';
    public static final char HZ = '─';
    public static final char ALONE = '▢';
    public static final char WALL = '#';
    //
    public static final char FOOD = '.';
    //
    public static final char PACGUM = 'o';
    //
    public static final char PACMAN = '<';
    public static final char BLINKY = 'B'; // Shadow / Red
    public static final char PINKY = 'P';  // Pink / Speedy
    public static final char INKY = 'I'; // Bashful / Cyan
    public static final char CLYDE = 'C'; // Pokey / Orange

    public Board parse(String boardAsString) {
        String[] rows = boardAsString.split("[\r\n]+");
        int nbRows = rows.length;
        int nbCols = 0;
        for (String row : rows) {
            int rowLen = BricABrac.trimEnding(row).length();
            if (nbCols == 0) {
                nbCols = rowLen;
            }
            else {
                nbCols = Math.max(nbCols, rowLen);
            }
        }

        Board board = new Board(nbCols, nbRows);
        for (int r=0; r<rows.length; r++) {
            String row = rows[r];
            for(int c=0;c<row.length();c++) {
                char v = row.charAt(c);
                if(isWall(v))
                    board.markAsWall(c+1, r+1);
                if(isFood(v))
                    board.markWithFood(c + 1, r + 1);
                if(isPacGum(v))
                    board.markWithPacGum(c + 1, r + 1);
                if(isProtagonist(v))
                    board.placeProtagonist(c + 1, r + 1, getProtagonist(v));
            }
        }

        return board;
    }

    public CreatureType getProtagonist(char c) {
        switch (c) {
            case BLINKY:
                return CreatureType.Blinky;
            case PINKY:
                return CreatureType.Pinky;
            case INKY:
                return CreatureType.Inky;
            case CLYDE:
                return CreatureType.Clyde;
            case PACMAN:
                return CreatureType.Pacman;

        }
        throw new IllegalArgumentException("Not a valid protagonist: '" + c + "'");
    }

    public boolean isProtagonist(char c) {
        switch (c) {
            case BLINKY:
            case PINKY:
            case INKY:
            case CLYDE:
            case PACMAN:
                return true;
        }
        return false;
    }

    public boolean isPacGum(char c) {
        return c == PACGUM;
    }

    public boolean isFood(char v) {
        return v == FOOD;
    }

    public static boolean isWall(char c) {
        switch (c) {
            case WALL:
            case ALONE:
            case BR:
            case BL:
            case TR:
            case TL:
            case VT:
            case HZ:
                return true;
        }
        return false;
    }
}
