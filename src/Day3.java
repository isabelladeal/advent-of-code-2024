import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            int result = part1("test/day_3_1.txt");
            long end = System.currentTimeMillis();
            System.out.println("Part 1 test: " + result + " in " + (end - start) + "ms");

            start = System.currentTimeMillis();
            result = part1("input/day_3.txt");
            end = System.currentTimeMillis();
            System.out.println("Part 1 input: " + result + " in " + (end - start) + "ms");

            start = System.currentTimeMillis();
            result = part2("test/day_3_2.txt");
            end = System.currentTimeMillis();
            System.out.println("Part 2 test: " + result + " in " + (end - start) + "ms");

            start = System.currentTimeMillis();
            result = part2("input/day_3.txt");
            end = System.currentTimeMillis();
            System.out.println("Part 2 input: " + result + " in " + (end - start) + "ms");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static int part1(String path) throws IOException {
        // Read input
        List<String> input = Files.readAllLines(Paths.get(path));

        // Concatenate input
        String concatenated = "";

        for (String line : input) {
            concatenated += line;
        }

        // Match mul instructions
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        Matcher matcher = pattern.matcher(concatenated);

        // Calculate sum of products
        int sum = 0;

        while (matcher.find()) {
            String[] split = matcher.group().split(",");

            int num1 = Integer.parseInt(split[0].substring(4));
            int num2 = Integer.parseInt(split[1].substring(0, split[1].length() - 1));

            sum += num1 * num2;
        }

        return sum;
    }

    static int part2(String path) throws IOException {
        // Read input
        List<String> input = Files.readAllLines(Paths.get(path));

        // Concatenate input
        String concatenated = "";

        for (String line : input) {
            concatenated += line;
        }

        // Match mul, do, and don't instructions
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)");
        Matcher matcher = pattern.matcher(concatenated);

        // Calculate sum of products
        boolean enabled = true;
        int sum = 0;

        while (matcher.find()) {
            String group = matcher.group();

            if (group.equals("do()")) {
                enabled = true;
            } else if (group.equals("don't()")) {
                enabled = false;
            } else if (enabled) {
                String[] split = matcher.group().split(",");

                int num1 = Integer.parseInt(split[0].substring(4));
                int num2 = Integer.parseInt(split[1].substring(0, split[1].length() - 1));

                sum += num1 * num2;
            }
        }

        return sum;
    }
}
