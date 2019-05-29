package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

/**
 * An implementation of a carnivorous predator that feeds on Plips.
 *
 * @author Andrew Choi
 * Date: 05/22/2019
 */

public class testClorus {

    @Test
    public void testBasics() {
        Clorus p = new Clorus(2);
        assertEquals(2, p.energy(), 0.01);
        p.move();
        assertEquals(1.97, p.energy(), 0.01);
        p.move();
        assertEquals(1.94, p.energy(), 0.01);
        p.stay();
        assertEquals(1.93, p.energy(), 0.01);
        p.stay();
        assertEquals(1.92, p.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus p1 = new Clorus(2);
        Clorus p2 = p1.replicate();
        assertEquals(1, p1.energy(), 0.01);
        assertEquals(1, p2.energy(), 0.01);
        Clorus p3 = p1.replicate();
        assertEquals(0.5, p1.energy(), 0.01);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus p = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // Will stay despite a nearby Plip due to being surrounded.
        p = new Clorus(1.2);
        Plip victim = new Plip(0.7);
        HashMap<Direction, Occupant> topPlipSurr = new HashMap<Direction, Occupant>();
        topPlipSurr.put(Direction.TOP, victim);
        topPlipSurr.put(Direction.BOTTOM, new Impassible());
        topPlipSurr.put(Direction.LEFT, new Impassible());
        topPlipSurr.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topPlipSurr);
        expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        // Will attack Plip.
        p = new Clorus(1.2);
        victim = new Plip(0.7);
        HashMap<Direction, Occupant> topPlip = new HashMap<Direction, Occupant>();
        topPlip.put(Direction.TOP, victim);
        topPlip.put(Direction.BOTTOM, new Empty());
        topPlip.put(Direction.LEFT, new Impassible());
        topPlip.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);
        // Verifies proper energy transfer.
        p.attack(victim);
        assertEquals(1.9, p.energy(), 0.01);


        // Energy >= 1; replicate towards an empty space.
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Impassible());
        allEmpty.put(Direction.BOTTOM, new Impassible());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(allEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.LEFT);

        assertEquals(expected, actual);


        // Energy < 1; stay.
        p = new Clorus(.99);
        actual = p.chooseAction(allEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.LEFT);

        assertEquals(expected, actual);
    }
}
