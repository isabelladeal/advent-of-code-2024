import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day6 {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            int result = part1("test/day_6.txt");
            long end = System.currentTimeMillis();
            System.out.println("Part 1 test: " + result + " in " + (end - start) + "ms");

            start = System.currentTimeMillis();
            result = part1("input/day_6.txt");
            end = System.currentTimeMillis();
            System.out.println("Part 1 input: " + result + " in " + (end - start) + "ms");

            start = System.currentTimeMillis();
            result = part2("test/day_6.txt");
            end = System.currentTimeMillis();
            System.out.println("Part 2 test: " + result + " in " + (end - start) + "ms");

            start = System.currentTimeMillis();
            result = part2("input/day_6.txt");
            end = System.currentTimeMillis();
            System.out.println("Part 2 input: " + result + " in " + (end - start) + "ms");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static int part1(String path) throws IOException {
        // Read input
        List<String> input = Files.readAllLines(Paths.get(path));

        // Create char grid and determine the guard's starting position
        int width = input.get(0).length();
        int height = input.size();

        char[][] grid = new char[height][width];

        int guardX = 0;
        int guardY = 0;

        // The guard's direction, 0 = right, 1 = down, 2 = left, 3 = up
        int direction = 3;

        for (int y = 0; y < height; y++) {
            String line = input.get(y);

            for (int x = 0; x < width; x++) {
                char c = line.charAt(x);

                if (c == '.' || c == '#') {
                    grid[y][x] = c;
                } else {
                    guardX = x;
                    guardY = y;

                    grid[y][x] = '.';
                }
            }
        }

        // Traverse the grid until the guard exits it
        int positions = 0;

        while (guardX < width && guardY < height && guardX >= 0 && guardY >= 0) {
            // Check for a wall in front of the guard
            boolean wall = false;

            if (direction == 0 && guardX + 1 < width) {
                if (grid[guardY][guardX + 1] == '#') {
                    wall = true;
                }
            } else if (direction == 1 && guardY + 1 < width) {
                if (grid[guardY + 1][guardX] == '#') {
                    wall = true;
                }
            } else if (direction == 2 && guardX - 1 >= 0) {
                if (grid[guardY][guardX - 1] == '#') {
                    wall = true;
                }
            } else if (direction == 3 && guardY - 1 >= 0) {
                if (grid[guardY - 1][guardX] == '#') {
                    wall = true;
                }
            }

            if (wall) {
                // If there is a wall in front of the guard, turn 90 degrees clockwise
                direction += 1;

                if (direction > 3) {
                    direction = 0;
                }
            } else {
                // Mark new positions as traveled and add them to the count
                if (grid[guardY][guardX] != 'X') {
                    grid[guardY][guardX] = 'X';
                    positions++;
                }

                // Take a step forward in the current direction
                if (direction == 0) {
                    guardX++;
                } else if (direction == 1) {
                    guardY++;
                } else if (direction == 2) {
                    guardX--;
                } else {
                    guardY--;
                }
            }
        }

        return positions;
    }

    static int part2(String path) throws IOException {
        // Read input
        List<String> input = Files.readAllLines(Paths.get(path));

        // Create char grid and determine the guard's starting position
        int width = input.get(0).length();
        int height = input.size();

        char[][] grid = new char[height][width];

        int startX = 0;
        int startY = 0;

        // The guard's start direction, 0 = right, 1 = down, 2 = left, 3 = up
        int startDirection = 3;

        for (int y = 0; y < height; y++) {
            String line = input.get(y);

            for (int x = 0; x < width; x++) {
                char c = line.charAt(x);

                if (c == '.' || c == '#') {
                    grid[y][x] = c;
                } else {
                    startX = x;
                    startY = y;
                    grid[y][x] = '.';
                }
            }
        }

        // Check for creatable loops
        int loopCount = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Don't check positions that are walls or the starting position
                if (grid[y][x] == '#' || x == startX && y == startY) {
                    continue;
                }

                // Create a new grid copy to work with
                char[][] gridCopy = new char[height][width];

                for (int i = 0; i < height; i++) {
                    for (int j = 0; j < width; j++) {
                        gridCopy[i][j] = grid[i][j];
                    }
                }

                // Set the guard start coordinates
                int guardX = startX;
                int guardY = startY;
                int direction = startDirection;

                // Place the new wall
                gridCopy[y][x] = '#';

                // Traverse the grid until the guard loops or exits it
                while (guardX < width && guardY < height && guardX >= 0 && guardY >= 0) {
                    // Check if we're treading the same ground in the same direction
                    if (gridCopy[guardY][guardX] == Integer.toString(direction).charAt(0)) {
                        loopCount++;
                        break;
                    }

                    // Check for a wall in front of the guard
                    boolean wall = false;

                    if (direction == 0 && guardX + 1 < width) {
                        if (gridCopy[guardY][guardX + 1] == '#') {
                            wall = true;
                        }
                    } else if (direction == 1 && guardY + 1 < width) {
                        if (gridCopy[guardY + 1][guardX] == '#') {
                            wall = true;
                        }
                    } else if (direction == 2 && guardX - 1 >= 0) {
                        if (gridCopy[guardY][guardX - 1] == '#') {
                            wall = true;
                        }
                    } else if (direction == 3 && guardY - 1 >= 0) {
                        if (gridCopy[guardY - 1][guardX] == '#') {
                            wall = true;
                        }
                    }

                    if (wall) {
                        // If there is a wall in front of the guard, turn 90 degrees clockwise
                        direction += 1;

                        if (direction > 3) {
                            direction = 0;
                        }
                    } else {
                        // Mark the current position as traveled with the direction it was left in
                        gridCopy[guardY][guardX] = Integer.toString(direction).charAt(0);

                        // Take a step forward in the current direction
                        if (direction == 0) {
                            guardX++;
                        } else if (direction == 1) {
                            guardY++;
                        } else if (direction == 2) {
                            guardX--;
                        } else {
                            guardY--;
                        }
                    }
                }
            }
        }

        return loopCount;
    }
}
