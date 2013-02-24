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
    public static final char FOOD = '.';

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
            }
        }

        return board;
    }

    public static boolean isFood(char v) {
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
