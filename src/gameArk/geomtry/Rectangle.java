//208134288 Yishai Nissim
package gameArk.geomtry;

import java.util.ArrayList;

/**
 * An rectangle.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class Rectangle {
    private static final int YES = 2, NO = 0, INTERCT = 1, SAME = 10;
    public static final int  LEFT = 0, DOWN = 1, UP = 2, RIGHT = 3;
    private static final double DEFAULT = 1;
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructor of rectangle.
     * @param upperLeft of rectangle.
     * @param width of rectangle.
     * @param height of rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        if (upperLeft == null) {
            upperLeft = new Point(DEFAULT, DEFAULT);
        }
        this.upperLeft = new Point(upperLeft.getX(), upperLeft.getY());
        if (width < 0 || height < 0) {
            width = DEFAULT;
            height = DEFAULT;
        }
        this.width = width;
        this.height =  height;
    }

    /**
     *  Constructor of rectangle from x and y.
     * @param x of upperLeft
     * @param y of upperLeft
     * @param width of rectangle
     * @param height of rectangle
     */
    public Rectangle(double x, double y, double width, double height) {
        this(new Point(x, y), width, height);
    }

    /**
     * Getter for width.
     * @return the width.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Getter for height.
     * @return the height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Getter for upperLeft.
     * @return the upperLeft.
     */
    public Point getUpperLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY());
    }

    /**
     * Find all the intersection points with the line.
     *
     *<p>In case of same line with one of the sides return the top and the
     * bottom of it
     * @param line to check with.
     * @return List of points of intersection.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> inter = new ArrayList<Point>();
        if (line == null) {
            return inter;
        }
        int numOfInter = numOfInter(line);
        if (numOfInter > YES) {
            return handleStraight(line);
        }
        if (numOfInter == 0) {
            return inter;
        }
        checkAddAll(inter, line);
        return inter;
    }
    private void checkAddAll(java.util.List<Point> inter, Line line) {
        Line[] lines = cornerLine();
        checkAdd(line, lines[LEFT], inter);
        checkAdd(line, lines[DOWN], inter);
        checkAdd(line, lines[UP], inter);
        checkAdd(line, lines[RIGHT], inter);
    }
    private void checkAdd(Line line1, Line line2, java.util.List<Point> inter) {
        if (line1.intersectionWith(line2) != null) {
            inter.add(line1.intersectionWith(line2));
        }
    }

    /*
     * in case of being the same line with one of the side will return num
     * above 2.
     */
    private int numOfInter(Line line) {
        int numOfIntrect = 0;
        Line[] l = cornerLine();
        numOfIntrect += this.isInterLine(line, l[0]);
        numOfIntrect += this.isInterLine(line, l[1]);
        numOfIntrect += this.isInterLine(line, l[2]);
        numOfIntrect += this.isInterLine(line, l[3]);
        return numOfIntrect;
    }
    private int isInterLine(Line line, Line l2) {
        if (line.isIntersecting(l2)) {
            if (line.intersectionWith(l2) != null) {
                return INTERCT;
            }
            return SAME;
        }
        return NO;
    }

    /**
     * Make array of the corners points.
     * @return the array.
     */
    public Point[] corners() {
        Point[] arr = new Point[4];
        arr[0] = this.upperLeft;
        arr[1] = new Point(this.upperLeft.getX(),
                this.upperLeft.getY() + this.height);
        arr[2] = new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY() + this.height);
        arr[3] = new Point(this.upperLeft.getX() + this.width,
                this.upperLeft.getY());
        return arr;
    }

    /**
     * Make array of border lines.
     * @return the array.
     */
    public Line[] cornerLine() {
        Point[] p = corners();
        Line[] l = new Line[4];
        l[LEFT] = new Line(p[0], p[1]);
        l[DOWN] = new Line(p[0], p[3]);
        l[UP] = new Line(p[1], p[2]);
        l[RIGHT] = new Line(p[2], p[3]);
        return l;
    }
    private java.util.List<Point> handleStraight(Line l) {
        Line[] lines = cornerLine();
        java.util.List<Point> inter = new ArrayList<Point>();
        for (int i = 0; i < lines.length; i++) {
            Line l1 = lines[i];
            if (l.isIntersecting(l1) && l.intersectionWith(l1) == null) {
                inter.add(l1.start());
                inter.add(l1.end());
            }
        }
        if (inter.isEmpty()) {
            checkAddAll(inter, l);
            if (inter.get(0).equals(inter.get(1))) {
                inter.remove(0);
            }
        }
        return inter;
    }
}
