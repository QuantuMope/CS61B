package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    private static final long SEED = 2873123;
    private static final Random RANDOM = new Random(SEED);
    private static int size = 0;
    private static boolean sizeSet = false;

    public static void backgroundNothing(TETile[][] world) {
        // initialize tiles
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }

    /**
     * Method that sets the size for the Hexagons.
     * Must be called with a valid input to be able to use addHexagon.
     * @param newSize
     */
    public static void setHexagonSize(int newSize) {
        if (newSize < 1) { throw new IllegalArgumentException("Choose a size greater than 1."); }
        size = newSize;
        sizeSet = true;
    }

    /**
     * Checks to see that input to addHexagon is valid.
     * NOTE - not perfect, doesn't check to see if hexagons are drawn off stage.
     * @return T/F depending on whether input is valid.
     */
    private static boolean validInput(int x, int y) {
        return (x >= 0 && x <= WIDTH && y >= 0 && y <= HEIGHT);
    }

    /** Picks a RANDOM tile with a 1/7 chance of each type of Tile shown below. */
    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(7);
        switch (tileNum) {
            case 0: return Tileset.SAND;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.WALL;
            case 4: return Tileset.MOUNTAIN;
            case 5: return Tileset.TREE;
            case 6: return Tileset.WATER;
            default: return Tileset.SAND;
        }
    }

    /**
     * Private Hexagon drawer that addHexagon uses.
     * Performs the iterative drawing.
     * Coordinates are based on top left corner of hexagon.
     */
    private static void hexCalc(int x, int y, TETile[][] world) {
        TETile design = randomTile();
        int currX = x;
        int currY = y;
        int counter = 0;
        while (counter != size) {
            for (int i = 0; i < (size + 2*counter); i += 1) {
                world[currX+i][currY] = design;
                world[currX+i][currY - size*2 + 1 + (counter*2)] = design;
            }
            currX -= 1;
            currY -= 1;
            counter += 1;
        }
    }

    public static void addHexagon(int x, int y, TETile[][] world) {
        if (sizeSet && validInput(x, y)) {
            hexCalc(x, y, world);
        }
    }

    /**
     * Creates a tesselation of 19 hexagons in the given world.
     * NOTE - hardcoded for size 3 hexagons.
     * @param world
     */
    public static void tesselate(TETile[][] world) {
        if (!tesselateFit()) { throw new IndexOutOfBoundsException("Tesselation does not fit in grid."); }

        // Top
        addHexagon(23, 49, world);
        // Straight down middle.
        for (int i = 0; i < 4; i += 1) {
            addHexagon(23, 49 - 6*(i+1), world);
        }
        // Inner layer.
        for (int i = 0; i < 4; i += 1) {
            addHexagon(23 - size*2+1, (49 - size) - 6*i, world);
            addHexagon(23 + size*2-1, (49 - size) - 6*i, world);
        }
        // Outer layer.
        for (int i = 0; i < 3; i += 1) {
            addHexagon(23 - (size*4) + 2, (49 - size*2) - 6*i, world);
            addHexagon(23 + (size*2-1)*2, (49 - size*2) - 6*i, world);
        }
    }

    private static boolean tesselateFit() {
        return (tesselateWidth() <= WIDTH && tesselateHeight() <= HEIGHT);
    }

    // Calculates the width and height of the 19 hexagon tesselation.
    private static int tesselateWidth() { return 5 * size + 6 * (size - 1); }
    private static int tesselateHeight() { return 10 * size; }

    public static void main(String[] args) {

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        TETile[][] world = new TETile[WIDTH][HEIGHT];
        backgroundNothing(world); // Creates black background.
        setHexagonSize(3);        // Sets hexagon size to 3. *Note only size 3 is supported for tesselate.
        tesselate(world);
        ter.renderFrame(world);
    }


}



