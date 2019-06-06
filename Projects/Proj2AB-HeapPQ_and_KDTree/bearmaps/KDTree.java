package bearmaps;

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
    private int comparatorChooserIndex = 0;

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
        insertHelper(input, root, comparatorChooser());
    }

    /** Helper method to switch between using the XxComparator and YyComparator. */
    private Comparator<Point> comparatorChooser() {
        if (comparatorChooserIndex == 0) { return XxCC; }
        return YyCC;
    }

    /** Insert helper method for adding Points to the KDTree. */
    private Node insertHelper(Point input, Node curr, Comparator<Point> cc) {
        if (curr == null) {
            size += 1;
            comparatorChooserIndex = 0;
            return new Node(input);
        }
        if (cc.compare(curr.getPoint(), input) > 0) {
            comparatorChooserIndex ^= 1;
            curr.left = insertHelper(input, curr.left, comparatorChooser());
        } else if (cc.compare(curr.getPoint(), input) <= 0) {
            comparatorChooserIndex ^= 1;
            curr.right = insertHelper(input, curr.right, comparatorChooser());
        }
        return curr;
    }

    /** Nearest method that figures out which Point is closest to the input. */
    @Override
    public Point nearest(double x, double y) {
        Point inputPoint = new Point(x, y);
        Point bestPoint = root.getPoint();
        double best = Point.distance(bestPoint, inputPoint);
        comparatorChooserIndex = 0;
        Comparator<Point> levelComparator = XxCC;
        return nearestHelper(inputPoint, root.getPoint(), root, best, levelComparator);
    }

    /** Nearest helper method.
     *  Recursively scales the KDTree looking for the closest Point.
     *  Prunes unnecessary grids and checks ones where the closest lateral
     *  distance is less than the current best distance. */
    private Point nearestHelper(Point input, Point bestPoint, Node curr, double best, Comparator<Point> cc) {
        if (curr == null) {
            return bestPoint;
        }

        Comparator<Point> levelComparator;
        double pruneDist;
        if (cc.equals(XxCC)) { pruneDist = Math.pow(curr.getX() - input.getX(), 2); }
        else { pruneDist = Math.pow(curr.getY() - input.getY(), 2); }

        if (cc.compare(curr.getPoint(), input) > 0) {
            if (Point.distance(curr.getPoint(), input) < best) {
                best = Point.distance(curr.getPoint(), input);
                bestPoint = curr.getPoint();
            }
            comparatorChooserIndex ^= 1;
            levelComparator = comparatorChooser();
            bestPoint = nearestHelper(input, bestPoint, curr.left, best, levelComparator);

            if (pruneDist < Point.distance(bestPoint, input)) {
                bestPoint = nearestHelper(input, bestPoint, curr.right, Point.distance(bestPoint, input), levelComparator);
            }
        } else if (cc.compare(curr.getPoint(), input) <= 0) {

            if (Point.distance(curr.getPoint(), input) < best) {
                best = Point.distance(curr.getPoint(), input);
                bestPoint = curr.getPoint();
            }
            comparatorChooserIndex ^= 1;
            levelComparator = comparatorChooser();
            bestPoint = nearestHelper(input, bestPoint, curr.right, best, levelComparator);

            if (pruneDist < Point.distance(bestPoint, input)) {
                bestPoint = nearestHelper(input, bestPoint, curr.left, Point.distance(bestPoint, input), levelComparator);
            }
        }
        return bestPoint;
    }


}
