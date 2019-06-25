package bearmaps.proj2ab;

import java.util.List;
import java.util.Comparator;
import java.lang.*;
/**
 * A KDTree approach to solving the nearest point problem.
 * Much more efficient than the NaivePointSet.
 * @author Andrew Choi
 * Date: 06/04/2019
 */

public class KDTree implements PointSet {

    private int size;
    private Node root = null;
    private Comparator<Point> XxCC = new XxComparator();
    private Comparator<Point> YyCC = new YyComparator();

    /** Creates a KDTree with all the points of the List inserted properly. */
    public KDTree(List<Point> points) {
        size = 0;
        for (Point point : points) {
            insert(point);
        }
    }

    /** A Node class that makes up the KD Tree. Binary Tree form.
     *  Each node Contains a Point.*/
    private class Node {
        private Point point;
        private Node left;
        private Node right;

        private Node(Point input) {
            point = input;
        }
        private Point getPoint() { return point; }
        private double getX() { return point.getX(); }
        private double getY() { return point.getY(); }
    }


    /** Comparator classes for comparing X & Y coordinates. */
    private class XxComparator implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            return ((Double) p1.getX()).compareTo(p2.getX());
        }
    }

    private class YyComparator implements Comparator<Point> {
        public int compare(Point p1, Point p2) {
            return ((Double) p1.getY()).compareTo(p2.getY());
        }
    }

    /** Insert method for adding Points to the KDTree. */
    private void insert(Point input) {
        if (size == 0) {
            size += 1;
            root = new Node(input);
            return;
        }
        insertHelper(input, root, XxCC);
    }

    /** Helper method to switch between using the XxComparator and YyComparator. */
    private Comparator<Point> comparatorChooser(Comparator cc) {
        if (cc.equals(YyCC)) { return XxCC; }
        return YyCC;
    }

    /** Insert helper method for adding Points to the KDTree. */
    private Node insertHelper(Point input, Node curr, Comparator<Point> cc) {
        if (curr == null) {
            size += 1;
            return new Node(input);
        }
        if (cc.compare(curr.getPoint(), input) > 0) {
            curr.left = insertHelper(input, curr.left, comparatorChooser(cc));
        } else if (cc.compare(curr.getPoint(), input) <= 0) {
            curr.right = insertHelper(input, curr.right, comparatorChooser(cc));
        }
        return curr;
    }

    /** Nearest method that figures out which Point is closest to the input. */
    @Override
    public Point nearest(double x, double y) {
        Point inputPoint = new Point(x, y);
        Point bestPoint = root.getPoint();
        double best = Point.distance(bestPoint, inputPoint);
        return nearestHelper(inputPoint, root.getPoint(), root, best, XxCC);
    }

    /** Nearest helper method.
     *  Recursively scales the KDTree looking for the closest Point.
     *  Prunes unnecessary grids and checks ones where the closest lateral
     *  distance is less than the current best distance. */
    private Point nearestHelper(Point input, Point bestPoint, Node curr, double best, Comparator<Point> cc) {
        double pruneDist;
        Node goodSide = curr;
        Node badSide = curr;

        if (curr == null) {
            return bestPoint;
        }
        if (Point.distance(curr.getPoint(), input) < best) {
            best = Point.distance(curr.getPoint(), input);
            bestPoint = curr.getPoint();
        }

        if (cc.equals(XxCC)) { pruneDist = Math.pow(curr.getX() - input.getX(), 2); }
        else { pruneDist = Math.pow(curr.getY() - input.getY(), 2); }

        if (cc.compare(curr.getPoint(), input) > 0) {
            goodSide = curr.left;
            badSide = curr.right;
        } else if (cc.compare(curr.getPoint(), input) <= 0) {
            goodSide = curr.right;
            badSide = curr.left;
        }

        bestPoint = nearestHelper(input, bestPoint, goodSide, best, comparatorChooser(cc));

        if (pruneDist < Point.distance(bestPoint, input)) {
            bestPoint = nearestHelper(input, bestPoint, badSide, Point.distance(bestPoint, input), comparatorChooser(cc));
        }
        return bestPoint;
    }


}
