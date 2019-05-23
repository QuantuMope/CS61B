package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.HugLifeUtils;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * An implementation of a carnivorous predator that feeds on Plips.
 *
 * @author Andrew Choi
 * Date: 05/22/2019
 */

public class Clorus extends Creature {

    /** Color values. r = red, g = green, b = blue. */
    private int r;
    private int g;
    private int b;

    /**
     * Creates Clorus with energy equal to e.
     */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /**
     * Creates Clorus with energy equal to 1.
     */
    public Clorus() { this(1); }


    public Color color() {
        return color(r, g, b);
    }

    /**
     * Attacks and eats nearby Plips. Obtains their energy.
     */
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * Cloruses lose 0.03 energy when moving.
     */
    public void move() {
        energy -= 0.03;
        if (energy <= 0) { energy = 0; }
    }

    /**
     * Cloruses lose 0.01 energy when staying.
     */
    public void stay() {
        energy -= 0.01;
        if (energy <= 0) { energy = 0; }
    }

    public Clorus replicate() {
        Clorus p = new Clorus(energy/2);
        energy /= 2;
        return p;
    }

    /**
     * Action Rules:
     * 1. If there are no empty squares, the Clorus will STAY
     * (even if there are Plips nearby they could attack since plip
     * squares do not count as empty squares).
     * 2. Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * 3. Otherwise, if the Clorus has energy greater than or equal to one, it will \
     * REPLICATE to a random empty square.
     * 4. Otherwise, the Clorus will MOVE to a random empty square.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        if (energy == 0) {
            return new Action(Action.ActionType.DIE);
        }
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipsNearby = new ArrayDeque<>();
        for (Direction area : neighbors.keySet()) {
            Occupant occupant = neighbors.get(area);
            if (occupant.name().equals("empty")) {
                emptyNeighbors.add(area);
            }
            if (occupant.name().equals("plip")) {
                plipsNearby.add(area);
            }
        }
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        if (!plipsNearby.isEmpty()) {
            Direction attackDirection = HugLifeUtils.randomEntry(plipsNearby);
            return new Action(Action.ActionType.ATTACK, attackDirection);
        }

        // Rule 3
        if (energy >= 1.0) {
            Direction repDirection = HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, repDirection);
        }

        // Rule 4
        Direction moveDirection = HugLifeUtils.randomEntry(emptyNeighbors);
        return new Action(Action.ActionType.MOVE, moveDirection);
    }

}
