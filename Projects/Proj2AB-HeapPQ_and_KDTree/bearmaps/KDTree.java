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
    private List<Point> points;
    private Node root = null;
    private Comparator<Point> XxCC = new XxComparator();
    private Comparator<Point> YyCC = new YyComparator();
    private int comparatorChooserIndex = 0;

    public KDTree(List<Point> points) {
        size = 0;
        for (Point point : points) {
            insert(point);
        }
    }

    private class Node {
        private Point point;
        private Node left;
        private Node right;

        private Node(Point input) {
            this.point = input;
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

    private void insert(Point input) {
        if (size == 0) {
            size += 1;
            root = new Node(input);
            return;
        }
        insertHelper(input, root, comparatorChooser());
    }

    private Comparator<Point> comparatorChooser() {
        if (comparatorChooserIndex == 0) { return XxCC; }
        return YyCC;
    }

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

    @Override
    public Point nearest(double x, double y) {
        Point inputPoint = new Point(x, y);
        Point bestPoint = root.getPoint();
        double best = Point.distance(bestPoint, inputPoint);
        return nearestHelper(inputPoint, root.getPoint(), root, best, comparatorChooser());
    }

    private Point nearestHelper(Point input, Point bestPoint, Node curr, double best, Comparator<Point> cc) {
        if (curr == null) {
            return bestPoint;
        }
        if (cc.compare(curr.getPoint(), input) > 0) {
            comparatorChooserIndex ^= 1;
            if (Point.distance(curr.getPoint(), input) < best) {
                best = Point.distance(curr.getPoint(), input);
                bestPoint = curr.getPoint();
            }
            bestPoint = nearestHelper(input, bestPoint, curr.left, best, comparatorChooser());
        } else if (cc.compare(curr.getPoint(), input) <= 0) {
            comparatorChooserIndex ^= 1;
            if (Point.distance(curr.getPoint(), input) < best) {
                best = Point.distance(curr.getPoint(), input);
                bestPoint = curr.getPoint();
            }
            bestPoint = nearestHelper(input, bestPoint, curr.right, best, comparatorChooser());
        }
        return bestPoint;
    }


}
