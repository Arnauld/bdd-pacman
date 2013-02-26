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
        board.placeProtagonist(2, 2, CreatureType.Pacman);
        assertThat(board.getCreatureAt(2, 2)).isNotNull();
        assertThat(board.getCreatureAt(2, 2).getCreatureType()).isEqualTo(CreatureType.Pacman);

        for(int col=1; col<=board.getNbCols(); col++) {
            for(int row=1;row <=board.getNbRows(); row++) {
                if(col!=2 && row!=2)
                    assertThat(board.getCreatureAt(col, row)).isNull();
            }
        }
    }

    @Test
    public void move_protagonist_in_empty_cell() {
        board.placeProtagonist(2, 2, CreatureType.Pacman);
        board.move(CreatureType.Pacman, Direction.RIGHT);
        assertThat(board.getCreatureAt(2, 2)).isNull();
        assertThat(board.getCreatureAt(3, 2)).isNotNull();
        assertThat(board.getCreatureAt(3, 2).getCreatureType()).isEqualTo(CreatureType.Pacman);
    }

    @Test
    public void move_protagonist_in_a_wall() {
        board.placeProtagonist(2, 2, CreatureType.Pacman);
        board.markAsWall(3, 2);
        board.move(CreatureType.Pacman, Direction.RIGHT);
        assertThat(board.getCreatureAt(2, 2)).as("CreatureType should still be there").isNotNull();
        assertThat(board.getCreatureAt(2, 2).getCreatureType()).isEqualTo(CreatureType.Pacman);
        assertThat(board.getCreatureAt(3, 2)).isNull();
    }

}
