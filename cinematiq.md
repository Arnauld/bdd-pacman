1. Feature: Be able to read a board from a text


    Feature: Be able to read a board from text

	  Scenario: An empty board

	    Given the following board
	    #  01234567890123456789
	    """
	    ....................
	    ....................
	    ....................
	    ....................
	    """
	    When one loads it
	    Then the board size must be 20x4



2. Run tests
3. Copy the suggested step
4. replace in `the_following_board` 

    @Given("^the following board$")
    public void the_following_board(String boardAsString) throws Throwable {
-        // Express the Regexp above with the code you wish you had
-        throw new PendingException();
+        assertThat(boardAsString).isEqualTo("Dude!");
    }


5. Relaunch, see failure => process with `BricABrac.stripMargin()`

-----8<----------------- BEG: remove strip margin --------------------------------

6. Implements `stripMargin` in TDD


// BricABracTest
    @Test
    public void stripMargin_no_trailing_whitespaces_single_line() {
        assertThat(BricABrac.stripMargin("|ehoh")).isEqualTo("ehoh");
    }

// BricABrac
    public static String stripMargin(String input) {
        return input;
    }
=> Failure

.. return input.replaceAll("^\\|", "");
=> OK

assertThat(BricABrac.stripMargin("    |ehoh")).isEqualTo("ehoh");
=> Failure

.. return input.replaceAll("^\\s*\\|", "");
=> OK

stripMargin_multilines_trailing_whitespaces
        assertThat(BricABrac.stripMargin("    |ehoh\n    |yuk")).isEqualTo("ehoh");
=> Failure

.. return input.replaceAll("(^|([\r\n]))\\s*\\|", "$2");
=> OK

assertThat(BricABrac.stripMargin("    |ehoh\n    |yuk   |yuk")).isEqualTo("ehoh\nyuk   |yuk");
=> OK immediately

-----8<----------------- END: remove strip margin --------------------------------

7.

    @Given("^the following board$")
    public void the_following_board(String boardAsString) throws Throwable {
        this.boardAsString = BricABrac.stripMargin(boardAsString);
    }

=> OK

8.
    @When("^one loads it$")
    public void one_loads_it() throws Throwable {
        assertThat(boardAsString).isNotNull();
        board = new BoardParser().parse(boardAsString);
    }

    public class BoardParser {
      public Board parse(String boardAsString) {
        return null;
      }
    }

=> OK

9.

    @Then("^the board size must be (\\d+)x(\\d+)$")
    public void the_board_size_must_be_x(int nbCols, int nbRows) throws Throwable {
-        // Express the Regexp above with the code you wish you had
-        throw new PendingException();
+        assertThat(board).isNotNull();
+        assertThat(board.getNbCols()).isEqualTo(nbCols);
+        assertThat(board.getNbRows()).isEqualTo(nbRows);
    }

    public class Board {
	    private int nbCols;
	    private int nbRows;

	    public int getNbCols() {
	        return nbCols;
	    }

	    public int getNbRows() {
	        return nbRows;
	    }
	}
=> KO : board null

10. BoardParser en TDD

	public class BoardParser {
	    public Board parse(String boardAsString) {
	        String[] rows = boardAsString.split("[\r\n]+");
	        int nbRows = rows.length;
	        int nbCols = rows[0].length();
	        for(String row : rows) {
	            if(row.length() != nbCols)
	                throw new ParseException("All rows must have the same size");
	        }
	        return new Board(nbCols, nbRows);
	    }

	    public static class ParseException extends RuntimeException {
	        public ParseException(String message) {
	            super(message);
	        }
	    }
	}
=> OK

11.
    And the cell at column 1 and row 1 is a wall
=>
    KO, create step


    @And("^the cell at column (\\d+) and row (\\d+) is a wall$")
    public void the_cell_at_column_and_row_is_a_wall(int col, int row) throws Throwable {
        assertThat(board).isNotNull();
        assertThat(board.isWall(col, row)).isTrue();
    }

=> Implements behavior and methods through TDD...

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
	        return cells[col][row];
	    }

	    public boolean isWall(int col, int row) {
	        return cellAt(col, row).isWall();
	    }

	    public static class Cell {
	        private boolean wall;

	        public void markAsWall() {
	            this.wall = true;
	        }

	        public boolean isWall() {
	            return wall;
	        }
	    }
	}

AND

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
	            }
	        }

	        return board;
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

=> KO ...

Warning: index starts at 0, but col and row start at 1 :)

=> BoardParser

        for (int r=0; r<rows.length; r++) {
            String row = rows[r];
            for(int c=0;c<row.length();c++) {
                char v = row.charAt(c);
                if(isWall(v))
-                   board.markAsWall(c, r);
+                   board.markAsWall(c+1, r+1);
            }
        }

=> Board

    private Cell cellAt(int col, int row) {
-       return cells[col][row];
+       return cells[col-1][row-1];
    }

=> OK

12.

    And the cell at column 1 and row 1 is a wall
    And the cell at column 20 and row 1 is a wall
    And the cell at column 20 and row 4 is a wall

=> OK

13.

    But the cell at column 2 and row 2 is a food

=> KO

   PacmanSteps
   +	@But("^the cell at column (\\d+) and row (\\d+) is a food$")
   +	public void the_cell_at_column_and_row_is_a_food(int col, int row) throws Throwable {
   +	    assertThat(board).isNotNull();
   +    	assertThat(board.hasFood(col, row)).as("Cell at (col: " + col + ", row: " + row + ") must have a food").isTrue();
   +	}


   Board
   +    public boolean hasFood(int col, int row) {
   +    	return cellAt(col, row).hasFood();
   + 	}

   Board#Cell
   +    public void dropAFood() {
   +        this.hasFood = true;
   +    }
   +
   +    public boolean hasFood() {
   +        return hasFood;
   +    }

   BoardParser
        Board board = new Board(nbCols, nbRows);
        for (int r=0; r<rows.length; r++) {
            String row = rows[r];
            for(int c=0;c<row.length();c++) {
                char v = row.charAt(c);
                if(isWall(v))
                    board.markAsWall(c+1, r+1);
   +            if(isFood(v))
   +	            board.markWithFood(c+1, r+1);
                    
            }
        }

   Board
   +    public boolean markWithFood(int col, int row) {
   +    	return cellAt(col, row).dropAFood();
   + 	}

   => rename for consistancy
   Board#Cell
   -    public void dropAFood() {
   +    public void markWithFood() {
   



 14. Add a new sample


  Scenario: An broken corner board

    Given the following board
    """
       #################
       #...............#
    ####...............#
    #..................#
    ####################
    """
    When one loads it
    Then the board size must be 20x4
    And the cell at column 1 and row 1 is a not wall
    And the cell at column 4 and row 1 is a wall
    And the cell at column 4 and row 2 is a wall
    But the cell at column 5 and row 2 is a food

    14-1.: Add 'not a wall'
    14-2.: => KO
    	Invalid size + Fix error message + relaunch KO
    14-3.: Fix Sample
           => OK
