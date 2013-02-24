package pacman;

import static pacman.BoardParser.ALONE;
import static pacman.BoardParser.BL;
import static pacman.BoardParser.BR;
import static pacman.BoardParser.HZ;
import static pacman.BoardParser.TL;
import static pacman.BoardParser.TR;
import static pacman.BoardParser.VT;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class BoardBeautifier {

    private boolean verbose;

    public BoardBeautifier() {
        this(false);
    }

    public BoardBeautifier(boolean verbose) {
        this.verbose = verbose;
    }

    // Simple cases:
    // ------------
    //
    //
    //    #
    //  #[#]#  => ╬
    //    #
    //
    //
    //  #[#]   => ╗ // ╔ // ╚ // ╝
    //    #
    //
    //    #
    //   [#]   => ║
    //    #
    //
    //
    //  #[#]#  => ═
    //
    //
    // Complex cases:
    // -------------
    //
    // .?
    //    # #       ║ ║
    //   [#]#  =>  [╚]╝
    //
    //
    // .?
    //    # #       ║ ║
    //   [#]#      [║]║
    //    # #  =>   ╚ ╝
    //
    //
    // .?
    //
    //  #[#]#  =>  ═[╗]╔═
    //    # #        ╚ ╝
    //
    // .?
    //  # # #      ═ ═ ══
    //  #[#]#  =>  ═[╗]╔═
    //    # #        ╚ ╝
    //

    public String process(String computedBoardAsString) {
        String[] rows = computedBoardAsString.split("[\r\n]+");
        int nbRows = rows.length;
        int nbCols = rows[0].length();

        StringBuilder builder = new StringBuilder();
        for (int row = 0; row < nbRows; row++) {
            if (row > 0) {
                builder.append("\n");
            }
            for (int col = 0; col < nbCols; col++) {
                builder.append(compute(rows, col, row));
            }
        }
        return builder.toString();
    }

    private char compute(String[] rows, final int col, final int row) {
        if (!hasWall(rows, col, row)) {
            if (col < rows[row].length()) {
                return rows[row].charAt(col);
            }
            else {
                return ' ';
            }
        }

        int flags = 0;
        if (hasWall(rows, col - 1, row)) {
            flags |= 1 << 0;
        }
        if (hasWall(rows, col + 1, row)) {
            flags |= 1 << 1;
        }
        if (hasWall(rows, col, row - 1)) {
            flags |= 1 << 2;
        }
        if (hasWall(rows, col, row + 1)) {
            flags |= 1 << 3;
        }

        int flagsd = 0;
        if (hasWall(rows, col - 1, row - 1)) {
            flagsd |= 1 << 0;
        }
        if (hasWall(rows, col + 1, row - 1)) {
            flagsd |= 1 << 1;
        }
        if (hasWall(rows, col - 1, row + 1)) {
            flagsd |= 1 << 2;
        }
        if (hasWall(rows, col + 1, row + 1)) {
            flagsd |= 1 << 3;
        }

        if (verbose) {
            dumpCase(rows, col, row, flags, flagsd);
        }

        switch (flags) {
            case 0:
                return ALONE;
            case 1:
                return HZ;
            case 2:
                // [#] #
                //     #
                return HZ;
            case 3:
                // # [#] #  #
                // #        #
                return HZ;
            case 4:
                return VT;
            case 5:
                return BR;
            case 6:
                return BL;
            case 7:
                //  #  #  #  #  #  #
                //  #     #  #     #
                //  #  #  # [#] #  #
                switch (flagsd) {
                    case 1:
                        return BL;
                    case 2:
                        return BR;
                }
                return HZ;
            case 8:
                // [#]
                //  #  #
                return VT;
            case 9:
                return TR;
            case 10:
                return TL;
            case 11:
                // #  # [#] #  #  #
                // #     #  #     #
                switch (flagsd) {
                    case 4:
                        return TL;
                    case 8:
                        return TR;
                }

                return HZ;
            case 12:
                //  #  #  #  #
                // [#]       #
                //  #        #
                return VT;
            case 13:
                //  #  #
                //  # [#]
                //  #  #
                //  #  #
                switch (flagsd) {
                    case 1:
                        // #  #  #  #  #  #
                        // #  #  #  #  # [#]
                        // #     #  #     #
                        return TR;
                    case 4:
                        // #     #  #     #
                        // #  #  #  #  # [#]
                        // #  #  #  #  #  #
                        return BR;
                }
                return VT;
            case 14:
                //  #  #
                // [#] #
                //  #  #
                //  #  #
                switch (flagsd) {
                    case 2:
                        //  #  #  #  #  #  #
                        // [#] #  #  #  #  #
                        //  #     #  #     #
                        return TL;
                    case 8:
                        //  #     #  #     #
                        // [#] #  #  #  #  #
                        // #  #  #  #  #  #
                        return BL;
                }
                return VT;
            case 15:
                switch (flagsd) {
                    case 7:
                        //  #  #  #
                        //  # [#] #
                        //  #  #
                        return TL;
                    case 11:
                        //  #  #  #
                        //  # [#] #
                        //     #  #
                        return TR;
                    case 13:
                        //  #  #
                        //  # [#] #
                        //  #  #  #
                        return BL;
                    case 14:
                        //     #  #
                        //  # [#] #
                        //  #  #  #
                        return BR;
                }
            default:
                return '?';
        }
    }

    private void dumpCase(String[] rows, int col, int row, int flags, int flagsd) {
        int nbRows = rows.length;
        int nbCols = rows[0].length();
        StringBuilder builder = new StringBuilder();
        for (int r = 0; r < nbRows; r++) {
            if (r > 0) {
                builder.append("\n");
            }
            for (int c = 0; c < nbCols; c++) {
                if (r == row && c == col) {
                    builder.append("[");
                }
                else {
                    builder.append(" ");
                }
                if (c < rows[r].length()) {
                    builder.append(rows[r].charAt(c));
                }
                else {
                    builder.append(' ');
                }
                if (r == row && c == col) {
                    builder.append("]");
                }
                else {
                    builder.append(" ");
                }
            }
        }
        System.out.println("----------------");
        System.out.println(builder.toString());
        System.out.println("Flag....: " + flags + ", " + Integer.toBinaryString(flags));
        System.out.println("Flagd...: " + flagsd + ", " + Integer.toBinaryString(flagsd));
    }

    private static boolean hasWall(String[] rows, int col, int row) {
        if (row < 0 || col < 0 || row >= rows.length || col >= rows[row].length()) {
            return false;
        }
        return BoardParser.isWall(rows[row].charAt(col));
    }
}
