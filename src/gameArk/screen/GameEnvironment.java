//208134288 Yishai Nissim
package gameArk.screen;

import gameArk.geomtry.Collidable;
import gameArk.geomtry.Line;
import gameArk.geomtry.Point;
import gameArk.geomtry.Rectangle;

import java.util.ArrayList;

/**
 * Represent of game objects.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class GameEnvironment {
    private java.util.List<Collidable> collidableList =
            new ArrayList<>();

    /**
     * Add new collidable to game.
     * @param c the collidable to add.
     */
    public void addCollidable(Collidable c) {
        if (c == null) {
            return;
        }
        collidableList.add(c);
    }

    /**
     * Remove collidable from the game.
     * @param c the collidable.
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }

    /**
     * Check if there is collision with one of the game objects.
     * @param trajectory the movement.
     * @return null if there is no collision and Collision info if there is
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        java.util.List<Point> interPoints = new ArrayList<Point>();
        java.util.List<Collidable> interCol = new ArrayList<Collidable>();
        findPoints(interPoints, interCol, trajectory);
        if (interPoints.isEmpty()) {
            return null;
        }
        return findTheCloestPoint(interPoints, interCol, trajectory);
    }

    /*
     * find the closet points in the array.
     */
    private CollisionInfo findTheCloestPoint(java.util.List<Point> interPoints,
                                             java.util.List<Collidable> interCol,
                                             Line trajectory) {
        Point closeInterPoint = interPoints.get(0);
        Collidable closeInterCol = interCol.get(0);
        int placeCol = 0;
        for (Point interPoint: interPoints) {
            if (interPoint.distance(trajectory.start())
                    < closeInterPoint.distance(trajectory.start())) {
                closeInterPoint = interPoint;
                closeInterCol = interCol.get(placeCol);
            }
            placeCol++;
        }
        return new CollisionInfo(closeInterPoint, closeInterCol);
    }

    /*
     * Fill the array with points.
     */
    private void findPoints(java.util.List<Point> interPoints,
                            java.util.List<Collidable> interCol,
                            Line trajectory) {
        java.util.List<Collidable> collidableList =
                new ArrayList<>(this.collidableList);
        for (Collidable col: collidableList) {
            Rectangle r = col.getCollisionRectangle();
            Point interPoint = trajectory.closestIntersectionToStartOfLine(r);
            if (interPoint != null) {
                interPoints.add(interPoint);
                interCol.add(col);
            }
        }
    }
}