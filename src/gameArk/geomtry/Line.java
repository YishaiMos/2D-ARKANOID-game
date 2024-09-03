//208134288 Yishai Nissim
package gameArk.geomtry;

/**
 * A Geomtry.Shapes.basic.GameArk.Line represent.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class Line {
    private static final int DEFUALT1 = 0, DEFUALT2 = 1;
    private static final double TOW = 2.0;
    private Point start;
    private Point end;

     /**
      * Make a new line, from Points object.
      *
      * <p>using a new points, what avoid from changing by the user. put the
      * end point by the bigger y, if they are equals by the bigger x.
      * @param start the point of start of the line
      * @param end the point of the end of line
      */
    public Line(Point start, Point end) {
        //if there is no points or illegal points put default points
        if (start.equals(end) || start == null || end == null) {
            this.start = new Point(DEFUALT1, DEFUALT1);
            this.end = new Point(DEFUALT2, DEFUALT2);
            return;
        }
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

     /**
      * Make a new line from regular index of points.
      *
      * @param x1 the x index for the start
      * @param y1 the y index for the start
      * @param x2 the x index for the end
      * @param y2 the y index for the end
      */
    public Line(double x1, double y1, double x2, double y2) {
        this(new Point(x1, y1), new Point(x2, y2));
    }

     /**
      * Check the long of the line.
      *
      * @return the length of the Geomtry.Shapes.basic.GameArk.Line, if there are problem with line return
      * PROBLEM.
      */
    public double length() {
        return start.distance(end);
    }

     /**
      * Find the middle point of the line.
      *
      * @return Geomtry.Shapes.basic.GameArk.Point of the middle, if there is problem with the line return
      * null
      */
    public Point middle() {
        double x = (start.getX() + end.getX()) / TOW;
        double y = (start.getY() + end.getY()) / TOW;
        Point mid = new Point(x, y);
        return mid;
    }

     /**
      * Sent the start point.
      *
      * <p>return new point in the term that the user will not change the line
      * @return the start point, if there is problem with the point return null
      */
    public Point start() {
        Point start = new Point(this.start.getX(), this.start.getY());
        return start;
    }
     /**
      * Sent the end point.
      *
      * <p>return new point in the term that the user will not change the line
      * @return the end point, if there is problem with the point return null
      */
    public Point end() {
        return new Point(this.end.getX(), this.end.getY());
    }

    /**
     * Check if the other line intersecting with the line.
     *
     * @param other the line to check
     * @return true if intersecting and false if not
     */
    public boolean isIntersecting(Line other) {
        if (other == null) {
            return false;
        }
        Point inter = intersectionWithAll(other);
        if (inter == null) { //the lines with the same gradient
            return isBetweenMe(other.start) || other.isBetweenMe(this.start)
                    || isBetweenMe(other.end) || other.isBetweenMe(this.end);
        }
        return isBetweenMe(inter) && other.isBetweenMe(inter);
    }
    /**
     * The point of intersection of two lines.
     *
     * <p>if the lines unified return null
     * @param other the line to check
     * @return the point if there is intersection and null if is not.
     */
    public Point intersectionWith(Line other) {
        if (other == null) {
            return null;
        }
        if (isIntersecting(other)) {
            return intersectionWithAll(other);
        }
        return null;
    }
    /**
     * If the line is same to other line.
     *
     * @param other line to check
     * @return true if there are same and false if is not
     */
    public boolean equals(Line other) {
        if (other == null) {
            return false;
        }
        return (this.start.equals(other.start) && this.end.equals(other.end))
                || (this.end.equals(other.start)
                && this.start.equals(other.end));
    }
    /*
     * Find the point of intersection of the big line.
     *
     * <p>without the limits of the start and end.
     * @param other the other line to check
     * @return the point of intersection
     */
    private Point intersectionWithAll(Line other) {
        if (other == null) {
            return null;
        }
        Line l1 = this.orderLine();
        other = other.orderLine();
        if (l1.isStraight() || other.isStraight()) { //handle with straight
            return l1.handleStraight(other);
        }
        double m1 = l1.gradient(), m2 = other.gradient();
        if (m1 == m2) {
            //if there is just one share point due to the way I built the points
            //same lines cant have equals start and end.
            if (l1.start.equals(other.end)) {
                return l1.start;
            } else if (l1.end.equals(other.start)) {
                return l1.end;
            }
            return null;
        }
        //use a math to find the cross
        double b1 = cross(m1, l1.start), b2 = cross(m2, other.start);
        double x = (b2 - b1) / (m1 - m2);
        return new Point(x, m1 * x + b1);
    }

    /**
     * Check if point is on the line.
     *
     * @param p1 the point to check
     * @return true if is and false if not
     */
    public boolean isBetweenMe(Point p1) {
        if (p1 == null) {
            return false;
        }
        return HelpCheckCalc.isEqualDub(p1.distance(this.start)
                + p1.distance(this.end), length());
    }
    /**
     * The gradient of the line.
     *
     * @return the gradient, if there is no lone return PROBLEM
     */
    private double gradient() {
        double dx = this.end.getX() - this.start.getX(),
                dy = this.end.getY() - this.start.getY();
        return dy / dx;
    }

    /**
     * The cross of y - axis.
     *
     * @param m the gradient of the line
     * @param p point in the line
     * @return the cross of y - axis
     */
    private double cross(double m, Point p) {
        return p.getY() - m * p.getX();
    }
    /**
     * Check if the line is parallel to the y - axis.
     *
     * @return true if parallel and false otherwise.
     */
    public boolean isStraight() {
        return HelpCheckCalc.isEqualDub(this.start.getX(), this.end.getX());
    }

    /**
     * Find the cross point if one or tow of the lines are parallel to y- axis.
     *
     * @param other line to find the cross with
     * @return the point of cross and null if there is no cross or ther are same
     */
    private Point handleStraight(Line other) {
        if (other.isStraight() && this.isStraight()) {
            return null;
        }
        //if the other is straight work in his class
        if (other.isStraight()) {
            return other.handleStraight(this);
        }
        //use math to find the intersection
        double m = other.gradient();
        double b = cross(m, other.start);
        double x = this.start.getX();
        return new Point(x, m * x + b);
    }

    /*
     * Order the line to handle with him.
     */
    private Line orderLine() {
        if (this.start.getY() < this.end.getY()
                || (this.start.getY() == this.end.getY()
                && this.start.getX() < this.end.getX())) {
            return this;
        } else {
            return new Line(this.end.getX(), this.end.getY(),
                    this.start.getX(), this.start.getY());
        }
    }

    /**
     * Find the close intersection to the start of Geomtry.Shapes.basic.GameArk.Line.
     * @param rect to check intersection with.
     * @return null if there is no intersection and the point if there is
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        java.util.List<Point> points = rect.intersectionPoints(this);
        if (points.isEmpty()) {
            return null;
        }
        Point p1 = points.remove(0);
        if (points.isEmpty()) {
            return p1;
        }
        Point p2 = points.get(0);
        if (p1.distance(this.start) > p2.distance(this.start)) {
            return p2;
        }
        return p1;
    }

}
