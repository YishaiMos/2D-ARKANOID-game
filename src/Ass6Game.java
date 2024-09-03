//208134288 Yishai Nissim

import biuoop.GUI;
import gameArk.screen.AnimationRunner;
import gameArk.screen.GameFlow;
import gameArk.screen.WideEasy;
import gameArk.screen.LevelInformation;
import gameArk.screen.Green3;
import gameArk.screen.DirectHit;
import gameArk.geomtry.HelpCheckCalc;

import java.util.ArrayList;
import java.util.List;

/**
 * A main for the game.
 *
 * @author Yishai Nissim
 * @version 1.1
 * @since 2023 -06-15
 */
public class Ass6Game {
    private static final int WIDTH = 800, HEIGHT = 600;
    /**
     * Run the game.
     * @param args from user.
     */
      public static void main(String[] args) {
          GUI gui = new GUI("Arkanoid", WIDTH, HEIGHT);
          AnimationRunner runner = new AnimationRunner(gui, 60);
          ArrayList<LevelInformation> list = new ArrayList<>();
          handleList(list, args);
          GameFlow gameFlow = new GameFlow(gui, gui.getKeyboardSensor(),
                  runner);
          gameFlow.runLevels(list);
    }
    private static void handleList(ArrayList<LevelInformation> list,
                              String[] levels) {
        List<Integer> numbers = getNumLevals(levels);
        for (Integer n: numbers) {
            switch (n) {
                case 1 -> list.add(new DirectHit());
                case 2 -> list.add(new WideEasy());
                case 3 -> list.add(new Green3());
                default -> {
                }
            }
        }
        if (list.isEmpty()) {
            list.add(new DirectHit());
            list.add(new WideEasy());
            list.add(new Green3());
        }
    }
    private static List<Integer> getNumLevals(String[] levels) {
          ArrayList<Integer> numbers = new ArrayList<>();
          for (String s: levels) {
              if (HelpCheckCalc.isValidInt(s)) {
                  numbers.add(Integer.parseInt(s));
              }
          }
          return numbers;
    }
}
