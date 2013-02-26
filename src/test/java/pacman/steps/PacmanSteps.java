package pacman.steps;

import static org.fest.assertions.Assertions.assertThat;

import cucumber.api.java.en.And;
import cucumber.api.java.en.But;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pacman.Board;
import pacman.BoardBeautifier;
import pacman.BoardParser;
import pacman.BricABrac;
import pacman.Direction;
import pacman.CreatureType;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class PacmanSteps {

    private Board board;
    private String boardAsString;
    private String computedBoardAsString;

    @Given("^the following board$")
    public void the_following_board(String boardAsString) throws Throwable {
        this.boardAsString = BricABrac.stripMargin(boardAsString);
    }

    @When("^one loads it$")
    public void one_loads_it() throws Throwable {
        assertThat(boardAsString).describedAs("Invalid state: no board actually defined").isNotNull();
        board = new BoardParser().parse(boardAsString);
    }

    @Then("^the board size must be (\\d+)x(\\d+)$")
    public void the_board_size_must_be_x(int nbCols, int nbRows) throws Throwable {
        assertThat(board).isNotNull();
        assertThat(board.getNbCols()).as("Number of columns").isEqualTo(nbCols);
        assertThat(board.getNbRows()).as("Number of rows").isEqualTo(nbRows);
    }

    @When("^one beautifies it$")
    public void one_beautifies_it() throws Throwable {
        assertThat(boardAsString).describedAs("Invalid state: no board actually defined").isNotNull();
        computedBoardAsString = new BoardBeautifier().process(boardAsString);
    }

    @Then("^the resulting board must be:$")
    public void the_resulting_board_must_be(String expectedBoardAsString) throws Throwable {
        assertThat(computedBoardAsString).describedAs("Invalid state: no board computed").isNotNull();
        assertThat(trimEndOfRows(computedBoardAsString)).isEqualTo(expectedBoardAsString);
    }

    private static String trimEndOfRows(String pyString) {
        StringBuilder b = new StringBuilder(pyString.length());
        for (String row : pyString.split("\n")) {
            if (b.length() > 0) {
                b.append('\n');
            }
            b.append(BricABrac.trimEnding(row));
        }
        return b.toString();
    }

    @And("^the cell at column (\\d+) and row (\\d+) is a wall$")
    public void the_cell_at_column_and_row_is_a_wall(int col, int row) throws Throwable {
        assertThat(board).isNotNull();
        assertThat(board.isWall(col, row)).as(
                "Cell at (col: " + col + ", row: " + row + ") must be a wall").isTrue();
    }

    @But("^the cell at column (\\d+) and row (\\d+) is a food$")
    public void the_cell_at_column_and_row_is_a_food(int col, int row) throws Throwable {
        assertThat(board).isNotNull();
        assertThat(board.hasFood(col, row)).as(
                "Cell at (col: " + col + ", row: " + row + ") must have a food").isTrue();
    }

    @And("^the cell at column (\\d+) and row (\\d+) is a not wall$")
    public void the_cell_at_column_and_row_is_a_not_wall(int col, int row) throws Throwable {
        assertThat(board).isNotNull();
        assertThat(board.isWall(col, row)).as(
                "Cell at (col: " + col + ", row: " + row + ") must not be a wall").isFalse();
    }

    @But("^the cell at column (\\d+) and row (\\d+) is a pacgum$")
    public void the_cell_at_column_and_row_is_a_pacgum(int col, int row) throws Throwable {
        assertThat(board).isNotNull();
        assertThat(board.hasPacGum(col, row)).as(
                "Cell at (col: " + col + ", row: " + row + ") must be a pacgum").isTrue();
    }

    @Then("^([a-zA-Z]+) is(?: still)? located at column (\\d+) and row (\\d+)$")
    public void protagonist_is_located_at_column_and_row(CreatureType creatureType, int col, int row) throws Throwable {
        assertThat(board).isNotNull();
        assertThat(board.getCreatureAt(col, row)).as(
                "Cell at (col: " + col + ", row: " + row + ") must be " + creatureType).isNotNull();
        assertThat(board.getCreatureAt(col, row).getCreatureType()).isEqualTo(creatureType);
    }

    @Given("^the following working board$")
    public void the_following_working_board(String boardAsString) throws Throwable {
        this.boardAsString = BricABrac.stripMargin(boardAsString);
        this.board = new BoardParser().parse(boardAsString);
    }

    @When("^([a-zA-Z]+) moves ([a-zA-Z]+)$")
    public void protagonist_move_(CreatureType creatureType, Direction direction) throws Throwable {
        assertThat(board).isNotNull();
        board.move(creatureType, direction);
    }
}
