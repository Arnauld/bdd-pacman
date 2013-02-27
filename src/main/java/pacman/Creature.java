package pacman;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Creature {
    private final CreatureType creatureType;
    private Coord coord;
    private CreatureState state;

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

    public void moveTo(Coord nextCoord) {
        this.coord = nextCoord;
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
}
