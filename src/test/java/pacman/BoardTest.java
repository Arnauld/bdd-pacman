package pacman;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class BoardTest {

    private Board board;

    @Before
    public void setup () {
        board = new Board(5, 4);
    }

    @Test
    public void place_protagonist() {
        board.placeProtagonist(2, 2, Protagonist.Pacman);
        assertThat(board.getProgonistAt(2, 2)).isEqualTo(Protagonist.Pacman);

        for(int col=1; col<=board.getNbCols(); col++) {
            for(int row=1;row <=board.getNbRows(); row++) {
                if(col!=2 && row!=2)
                    assertThat(board.getProgonistAt(col, row)).isNull();
            }
        }
    }

    @Test
    public void move_protagonist_in_empty_cell() {
        board.placeProtagonist(2, 2, Protagonist.Pacman);
        board.move(Protagonist.Pacman, Direction.RIGHT);
        assertThat(board.getProgonistAt(2, 2)).isNull();
        assertThat(board.getProgonistAt(3, 2)).isEqualTo(Protagonist.Pacman);
    }

    @Test
    public void move_protagonist_in_a_wall() {
        board.placeProtagonist(2, 2, Protagonist.Pacman);
        board.markAsWall(3, 2);
        board.move(Protagonist.Pacman, Direction.RIGHT);
        assertThat(board.getProgonistAt(2, 2)).as("Protagonist should still be there").isEqualTo(Protagonist.Pacman);
        assertThat(board.getProgonistAt(3, 2)).isNull();
    }

}
