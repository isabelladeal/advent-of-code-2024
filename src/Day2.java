import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class Day2 {
    enum Direction {
        INCREASING,
        DECREASING,
        NEITHER;
    }

    public static void main(String[] args) {
        try {
            // Create a 2D integer array from file
            List<String> input = Files.readAllLines(Paths.get("input/day_two_input.txt"));
            int size = input.size();
            int safe = 0;

            for (int i = 0; i < size; i++) {
                String[] strings = input.get(i).split(" ");
                int[] levels = new int[strings.length];

                for (int j = 0; j < strings.length; j++) {
                    levels[j] = Integer.parseInt(strings[j]);
                }

                System.out.println(Arrays.toString(levels));

                // Determine the direction of the levels
                Direction direction;

                if (levels[0] < levels[1]) {
                    direction = Direction.INCREASING;
                } else if (levels[0] > levels[1]) {
                    direction = Direction.DECREASING;
                } else {
                    break;
                }

                boolean increasing;

                if (levels[0] < levels[1]) {
                    increasing = true;
                } else if (levels[0] > levels[1]) {
                    increasing = false;
                } else {
                    break;
                }

                System.out.println(direction);

                boolean isSafe = true;

                for (int j = 1; j < levels.length; j++) {
                    if (increasing && levels[j] <= levels[j - 1]) {
                        isSafe = false;
                        System.out.println("marker 1");
                        break;
                    }
                    if (!increasing && levels[j] >= levels[j - 1]) {
                        isSafe = false;
                        System.out.println("marker 2");
                        break;
                    }
                    if (Math.abs(levels[j] - levels[j - 1]) > 3) {
                        isSafe = false;
                        System.out.println("marker 3");
                        break;
                    }

                    // if (direction == Direction.INCREASING && levels[j - 1] > levels[j]
                    // || direction == Direction.DECREASING && levels[j - 1] < levels[j]
                    // || levels[j - 1] == levels[j]
                    // || Math.abs(levels[j] - levels[j - 1]) > 3) {
                    // isSafe = false;
                    // System.out.println("Invalid levels at indices " + (j - 1) + " -> " + j);
                    // break;
                    // }

                    // if (verifyLevels(direction, levels[j - 1], levels[j]) == false) {
                    // isSafe = false;
                    // break;
                    // }
                }

                if (isSafe) {
                    safe += 1;
                }

                System.out.println("Safe: " + isSafe);
            }

            System.out.println("" + safe + " reports are safe.");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static boolean verifyLevels(Direction direction, int level1, int level2) {
        if (level1 == level2) {
            return false;
        } else if (direction == Direction.INCREASING && level2 < level1) {
            return false;
        } else if (direction == Direction.DECREASING && level2 > level2) {
            return false;
        } else if (Math.abs(level1 - level2) > 3) {
            return false;
        }

        return true;
    }
}
