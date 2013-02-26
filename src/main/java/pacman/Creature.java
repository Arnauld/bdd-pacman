package pacman;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
public class Creature {
    private final CreatureType creatureType;
    private Coord coord;

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

    public void moveTo(Coord nextCoord) {
        this.coord = nextCoord;
    }
}
