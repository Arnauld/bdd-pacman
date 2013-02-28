package pacman;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Creature {
    private final CreatureType creatureType;
    private Coord coord;
    private CreatureState state;
    //
    private int speed = 1;
    private Direction direction, nextDirection;
    private float alpha = 0.0f;

    public Creature(CreatureType creatureType) {
        this.creatureType = creatureType;
    }

    public void teleportTo(Coord coord) {
        this.coord = coord;
    }

    public CreatureType getCreatureType() {
        return creatureType;
    }

    public Coord getCoord() {
        return coord;
    }

    public boolean isLocatedAt(int col, int row) {
        return coord.sameAs(col, row);
    }

    public boolean isLocatedAt(Coord coord) {
        return isLocatedAt(coord.col, coord.row);
    }

    public void changeDirection(Direction direction) {
        if(this.direction == null)
            this.direction = direction;
        else
            this.nextDirection = direction;
    }

    public void eatAPacGum() {
        state = CreatureState.Pacgum;
    }

    public boolean isUnderPacGum() {
        return state == CreatureState.Pacgum;
    }

    public void die() {
        state = CreatureState.Dead;
    }

    public boolean isDead() {
        return state == CreatureState.Dead;
    }

    public void changeSpeedTo(int speed) {
        this.speed = speed;
    }

    public void tick() {
        // ┘└ ┐┌ │─ □ ┬ ┤ ┴ ├ ┼
        //
        //   ┌─────────┬─────────┐
        //   │    :    │    :    │
        //   │    :    │    :    │
        //   │....+....│....+....│
        //   │    :    │    :    │
        //   │    :    │    :    │
        //   ├─────────┼─────────┤
        //        <---> `speed/2` ticks

        float before = alpha;
        alpha += 2.0f / speed;

        // changement de signe: on a passé 0
        if(before*alpha < 0 || alpha==0.0) {
            if(nextDirection != null) {
                direction = nextDirection;
                alpha = 0.0f;
                nextDirection = null;
            }
        }
        if(alpha >= 1.0) {
            coord = coord.apply(direction);
            alpha = -1.0f;
        }
    }
}
