import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Day4 {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            int result = part1("test/day_4.txt");
            long end = System.currentTimeMillis();
            System.out.println("Part 1 test: " + result + " in " + (end - start) + "ms");

            start = System.currentTimeMillis();
            result = part1("input/day_4.txt");
            end = System.currentTimeMillis();
            System.out.println("Part 1 input: " + result + " in " + (end - start) + "ms");

            start = System.currentTimeMillis();
            result = part2("test/day_4.txt");
            end = System.currentTimeMillis();
            System.out.println("Part 2 test: " + result + " in " + (end - start) + "ms");

            start = System.currentTimeMillis();
            result = part2("input/day_4.txt");
            end = System.currentTimeMillis();
            System.out.println("Part 2 input: " + result + " in " + (end - start) + "ms");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static int part1(String path) throws IOException {
        // Read input
        List<String> input = Files.readAllLines(Paths.get(path));

        // Count the appearances of XMAS
        int width = input.get(0).length();
        int height = input.size();

        int count = 0;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (input.get(y).charAt(x) == 'X') {
                    // Check right
                    String word = "";
                    for (int i = 0, x2 = x; i < 4 && x2 < width; i++, x2++) {
                        word += input.get(y).charAt(x2);
                    }
                    if (word.equals("XMAS")) {
                        count++;
                    }

                    // Check down-right
                    word = "";
                    for (int i = 0, x2 = x, y2 = y; i < 4 && x2 < width && y2 < height; i++, x2++, y2++) {
                        word += input.get(y2).charAt(x2);
                    }
                    if (word.equals("XMAS")) {
                        count++;
                    }

                    // Check down
                    word = "";
                    for (int i = 0, y2 = y; i < 4 && y2 < height; i++, y2++) {
                        word += input.get(y2).charAt(x);
                    }
                    if (word.equals("XMAS")) {
                        count++;
                    }

                    // Check down-left
                    word = "";
                    for (int i = 0, x2 = x, y2 = y; i < 4 && x2 >= 0 && y2 < height; i++, x2--, y2++) {
                        word += input.get(y2).charAt(x2);
                    }
                    if (word.equals("XMAS")) {
                        count++;
                    }

                    // Check left
                    word = "";
                    for (int i = 0, x2 = x; i < 4 && x2 >= 0; i++, x2--) {
                        word += input.get(y).charAt(x2);
                    }
                    if (word.equals("XMAS")) {
                        count++;
                    }

                    // Check up-left
                    word = "";
                    for (int i = 0, x2 = x, y2 = y; i < 4 && x2 >= 0 && y2 >= 0; i++, x2--, y2--) {
                        word += input.get(y2).charAt(x2);
                    }
                    if (word.equals("XMAS")) {
                        count++;
                    }

                    // Check up
                    word = "";
                    for (int i = 0, y2 = y; i < 4 && y2 >= 0; i++, y2--) {
                        word += input.get(y2).charAt(x);
                    }
                    if (word.equals("XMAS")) {
                        count++;
                    }

                    // Check up-right
                    word = "";
                    for (int i = 0, x2 = x, y2 = y; i < 4 && x2 < width && y2 >= 0; i++, x2++, y2--) {
                        word += input.get(y2).charAt(x2);
                    }
                    if (word.equals("XMAS")) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    static int part2(String path) throws IOException {
        // Read input
        List<String> input = Files.readAllLines(Paths.get(path));

        // Count appearances of X-MAS
        int width = input.get(0).length();
        int height = input.size();

        int count = 0;

        for (int x = 1; x < width - 1; x++) {
            for (int y = 1; y < height - 1; y++) {
                if (input.get(y).charAt(x) == 'A') {
                    // Get X coordinates
                    int top = y - 1;
                    int left = x - 1;
                    int bottom = y + 1;
                    int right = x + 1;

                    // Validate left-to-right
                    char topLeft = input.get(top).charAt(left);
                    char bottomRight = input.get(bottom).charAt(right);

                    boolean isMas1 = topLeft == 'M' && bottomRight == 'S';
                    boolean isSam1 = topLeft == 'S' && bottomRight == 'M';

                    boolean leftRightValid = isMas1 || isSam1;

                    // Validate right-to-left
                    char topRight = input.get(top).charAt(right);
                    char bottomLeft = input.get(bottom).charAt(left);

                    boolean isMas2 = topRight == 'M' && bottomLeft == 'S';
                    boolean isSam2 = topRight == 'S' && bottomLeft == 'M';

                    boolean rightLeftValid = isMas2 || isSam2;

                    // Validate X-MAS
                    if (leftRightValid && rightLeftValid) {
                        count++;
                    }
                }
            }
        }

        return count;
    }
}
