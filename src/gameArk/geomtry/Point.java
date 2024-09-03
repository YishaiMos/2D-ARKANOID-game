//208134288 Yishai Nissim
package gameArk.geomtry;

/**
 * A Geomtry.Shapes.basic.GameArk.Point represent.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class Point {
    private double x;
    private double y;
    public static final int PROBLEM = -1;

    /**
     * Make a new point.
     * @param x of the point
     * @param y of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Distance between two points.
     * @param other point to check
     * @return the distance if there is no point return PROBLEM
     */
    public double distance(Point other) {
        if (other == null) {
            return PROBLEM;
        }
        return Math.sqrt(((this.x - other.x) * (this.x - other.x))
                + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * If other point is same.
     * @param other point to check
     * @return true if there are same and false if not
     */
    public boolean equals(Point other) {
        if (other == null) {
            return false;
        }
        return  HelpCheckCalc.isEqualDub(this.x, other.getX())
                && HelpCheckCalc.isEqualDub(this.y, other.getY());
    }

    /**
     * The x of the point.
     * @return the x
     */
    public double getX() {
        return this.x;
    }

    /**
     * The y of the point.
     * @return the y
     */
    public double getY() {
        return this.y;
    }
}
