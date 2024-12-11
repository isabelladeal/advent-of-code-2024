import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Day1 {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            int result = part1("test/day_1.txt");
            long end = System.currentTimeMillis();
            System.out.println("Part 1 test: " + result + " in " + (end - start) + "ms");

            start = System.currentTimeMillis();
            result = part1("input/day_1.txt");
            end = System.currentTimeMillis();
            System.out.println("Part 1 input: " + result + " in " + (end - start) + "ms");

            start = System.currentTimeMillis();
            result = part2("test/day_1.txt");
            end = System.currentTimeMillis();
            System.out.println("Part 2 test: " + result + " in " + (end - start) + "ms");

            start = System.currentTimeMillis();
            result = part2("input/day_1.txt");
            end = System.currentTimeMillis();
            System.out.println("Part 2 input: " + result + " in " + (end - start) + "ms");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static int part1(String path) throws IOException {
        // Read input
        List<String> input = Files.readAllLines(Paths.get(path));

        // Create arrays of the left and right sides
        int size = input.size();

        int[] left = new int[size];
        int[] right = new int[size];

        for (int i = 0; i < size; i++) {
            String[] nums = input.get(i).split("   ");

            left[i] = Integer.parseInt(nums[0]);
            right[i] = Integer.parseInt(nums[1]);
        }

        // Sort the left and right sides
        Arrays.sort(left);
        Arrays.sort(right);

        // Calculate the total distance
        int distance = 0;

        for (int i = 0; i < size; i++) {
            distance += Math.abs(left[i] - right[i]);
        }

        return distance;
    }

    static int part2(String path) throws IOException {
        // Read input
        List<String> input = Files.readAllLines(Paths.get(path));

        // Create arrays of the left and right sides
        int size = input.size();

        int[] left = new int[size];
        int[] right = new int[size];

        for (int i = 0; i < size; i++) {
            String[] nums = input.get(i).split("   ");

            left[i] = Integer.parseInt(nums[0]);
            right[i] = Integer.parseInt(nums[1]);
        }

        // Sort the left and right sides
        Arrays.sort(left);
        Arrays.sort(right);

        // Create a map of the count of numbers on the right side
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();

        for (int i = 0; i < size; i++) {
            int num = right[i];

            if (counts.containsKey(num)) {
                counts.put(num, counts.get(num) + 1);
            } else {
                counts.put(num, 1);
            }
        }

        // Calculate the similarity score
        int similarity = 0;

        for (int i = 0; i < size; i++) {
            int num = left[i];

            if (counts.containsKey(num)) {
                similarity += num * counts.get(num);
            }
        }

        return similarity;
    }
}
