//208134288 Yishai Nissim
package gameArk.geomtry;

import java.util.Random;

/**
 * Checking input, math calculation and other help functions.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class HelpCheckCalc {
    private static final int ZERO = 0, TOW = 2;
    private static final double EPSILON = 0.000001;
    /**
     * Check if the input is Integer.
     *
     * @param s the input to check
     * @return true is the input is O.K. and false if not
     */
    public static boolean isValidInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
    /**
     * Check if the input is Double.
     *
     * @param s the input to check
     * @return true is the input is O.K. and false if not
     */
    public static boolean isValidDub(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }
    /**
     * Random x or y of point in the borders.
     *
     * @param range of screen
     * @param radius of tha ball
     * @return the num for x or y
     */
    public static double randPlace(int range, int radius) {
        if (range <= ZERO || radius <= ZERO) {
            return range;
        }
        Random rand = new Random();
        double x = rand.nextDouble();
        //for the range of the radius
        x += (double) rand.nextInt(range - TOW * radius) + radius;
        return x;
    }

    /**
     * Check double numbers with threshold.
     *
     * @param x the fist number to check
     * @param y the second number to check
     * @return true if they equal and false otherwise
     */
    public static boolean isEqualDub(double x, double y) {
        return Math.abs(x - y) < EPSILON;

    }
}
