//208134288 Yishai Nissim
package gameArk.geomtry;
import biuoop.GUI;
import gameArk.screen.GameLevel;

/**
 * A GameArk.Block Remover represent.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructor for block remover.
     * @param game to be on.
     * @param removedBlocks to update.
     */

    public BlockRemover(GameLevel game, Counter removedBlocks) {
        if (game == null) {
            game = new GameLevel(new GUI("problam", 5, 5));
        }
        this.game = game;
        if (removedBlocks == null) {
            removedBlocks = new Counter();
        }
        this.remainingBlocks = removedBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit == null) {
            return;
        }
        this.remainingBlocks.decrease(1);
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
    }
}
