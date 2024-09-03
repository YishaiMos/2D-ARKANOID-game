//208134288 Yishai Nissim
package gameArk.geomtry;
/**
 * A Counter represent.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class Counter {
    private int count = 0;

    /**
     * Increase the number.
     * @param number to add.
     */
    public void increase(int number) {
        this.count += number;
    }

    /**
     * Decrease the number.
     * @param number to remove.
     */
    public void decrease(int number) {
        this.count -= number;
    }

    /**
     * Getter for the value.
     * @return the current value.
     */
    public int getValue() {
        return this.count;
    }
}
