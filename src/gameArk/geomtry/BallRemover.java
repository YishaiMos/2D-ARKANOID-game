//208134288 Yishai Nissim
package gameArk.geomtry;

import biuoop.GUI;
import gameArk.screen.GameLevel;
/**
 * A GameArk.Ball Remover represent.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter ballCount;

    /**
     * Constroctur for the ball remover.
     * @param game to be on.
     * @param ballCount to updet.
     */
    public BallRemover(GameLevel game, Counter ballCount) {
        if (game == null) {
            game = new GameLevel(new GUI("problam", 5, 5));
        }
        if (ballCount == null) {
            ballCount = new Counter();
        }
        this.game = game;
        this.ballCount = ballCount;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.ballCount.decrease(1);
    }

}
