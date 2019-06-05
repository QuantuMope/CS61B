package bearmaps;

import java.util.List;

/**
 * A naive linear-time solution to solving the nearest point problem.
 * @author Andrew Choi
 * Date: 06/04/2019
 */

public class NaivePointSet implements PointSet {

    private List<Point> points;

    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    @Override
    public Point nearest(double x, double y) {
        Point inputPoint = new Point(x, y);
        Point bestPoint = points.get(0);
        double best = Point.distance(points.get(0), inputPoint);
        for (Point point : points) {
            if (Point.distance(point, inputPoint) < best) {
                best = Point.distance(point, inputPoint);
                bestPoint = point;
            }
        }
        return bestPoint;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2);
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0);
        double testX = ret.getX(); // should be 3.3
        double testY = ret.getY(); // should be 4.4
    }



}
