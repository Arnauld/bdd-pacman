package pacman;

import org.junit.Test;
import java.util.EnumSet;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class CreatureTest {

    @Test
    public void move_and_direction_change() {
        Creature creature = new Creature(CreatureType.Pacman);
        creature.changeSpeedTo(30);
        creature.changeDirection(Direction.RIGHT);
        creature.teleportTo(new Coord(0, 0));

        EnumSet<Direction> others = EnumSet.allOf(Direction.class);
        others.remove(Direction.RIGHT);
        Direction[] sel = others.toArray(new Direction[others.size()]);

        for (int i = 1; i <= 60; i++) {
            if(i<10)
                System.out.print("0" + i + " ");
            else
                System.out.print(i + " ");
            creature.changeDirection(sel[i % sel.length]);
            creature.tick();
        }

    }
}
