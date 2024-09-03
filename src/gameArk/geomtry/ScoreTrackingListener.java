//208134288 Yishai Nissim
package gameArk.geomtry;
/**
 * Score Tracking.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructer.
     * @param scoreCounter to add.
     */

    public ScoreTrackingListener(Counter scoreCounter) {
        if (scoreCounter == null) {
            currentScore = new Counter();
        }
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
        beingHit.removeHitListener(this);
    }
}
