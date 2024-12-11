import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day5 {
    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            int result = part1("test/day_5.txt");
            long end = System.currentTimeMillis();
            System.out.println("Part 1 test: " + result + " in " + (end - start) + "ms");

            start = System.currentTimeMillis();
            result = part1("input/day_5.txt");
            end = System.currentTimeMillis();
            System.out.println("Part 1 input: " + result + " in " + (end - start) + "ms");

            start = System.currentTimeMillis();
            result = part2("test/day_5.txt");
            end = System.currentTimeMillis();
            System.out.println("Part 2 test: " + result + " in " + (end - start) + "ms");

            start = System.currentTimeMillis();
            result = part2("input/day_5.txt");
            end = System.currentTimeMillis();
            System.out.println("Part 2 input: " + result + " in " + (end - start) + "ms");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    static int part1(String path) throws IOException {
        // Read input
        List<String> input = Files.readAllLines(Paths.get(path));

        // Section flag for input, true = order rules, false = updates
        boolean sectionFlag = true;

        // Read order rules and then sum the middle numbers of valid updates
        Map<Integer, Set<Integer>> order = new HashMap<Integer, Set<Integer>>();
        int sum = 0;

        for (String line : input) {
            // Continue to the next section when an empty line is found
            if (line.equals("")) {
                sectionFlag = false;
                continue;
            }

            if (sectionFlag) {
                // Add order rules
                String[] split = line.split("\\|");

                int before = Integer.parseInt(split[0]);
                int after = Integer.parseInt(split[1]);

                if (order.containsKey(before)) {
                    order.get(before).add(after);
                } else {
                    order.put(before, new HashSet<Integer>());
                    order.get(before).add(after);
                }
            } else {
                // Check update for validity
                String[] split = line.split(",");
                int[] update = new int[split.length];

                for (int i = 0; i < split.length; i++) {
                    update[i] = Integer.parseInt(split[i]);
                }

                Set<Integer> previousPages = new HashSet<Integer>();
                boolean isValid = true;

                for (int page : update) {
                    if (order.containsKey(page)) {
                        Set<Integer> afters = order.get(page);

                        for (int after : afters) {
                            if (previousPages.contains(after)) {
                                isValid = false;
                                break;
                            }
                        }
                    }

                    previousPages.add(page);
                }

                if (isValid) {
                    // Add the middle number to sum
                    sum += update[update.length / 2];
                }
            }
        }

        return sum;
    }

    static int part2(String path) throws IOException {
        // Read input
        List<String> input = Files.readAllLines(Paths.get(path));

        // Section flag for input, true = order rules, false = updates
        boolean sectionFlag = true;

        // Read order rules and then sort invalid updates and sum the middle numbers
        Map<Integer, Set<Integer>> order = new HashMap<Integer, Set<Integer>>();
        int sum = 0;

        for (String line : input) {
            // Continue to next section when an empty line is found
            if (line.equals("")) {
                sectionFlag = false;
                continue;
            }

            if (sectionFlag) {
                // Add order rules
                String[] split = line.split("\\|");

                int before = Integer.parseInt(split[0]);
                int after = Integer.parseInt(split[1]);

                if (order.containsKey(before)) {
                    order.get(before).add(after);
                } else {
                    order.put(before, new HashSet<Integer>());
                    order.get(before).add(after);
                }
            } else {
                // Check update for validity
                String[] split = line.split(",");
                int[] update = new int[split.length];

                for (int i = 0; i < split.length; i++) {
                    update[i] = Integer.parseInt(split[i]);
                }

                Set<Integer> seenPages = new HashSet<Integer>();
                boolean isValid = true;

                for (int page : update) {
                    if (order.containsKey(page)) {
                        Set<Integer> afters = order.get(page);

                        for (int after : afters) {
                            if (seenPages.contains(after)) {
                                isValid = false;
                                break;
                            }
                        }
                    }

                    seenPages.add(page);
                }

                if (!isValid) {
                    // Sort the update if it's invalid
                    LinkedList<Integer> sorted = new LinkedList<Integer>();
                    sorted.add(update[0]);

                    for (int i = 1; i < update.length; i++) {
                        // Put the element as far back as possible while following order rules
                        boolean wasAdded = false;

                        for (int j = 0; j < sorted.size(); j++) {
                            if (order.containsKey(update[i])) {
                                if (order.get(update[i]).contains(sorted.get(j))) {
                                    sorted.add(j, update[i]);
                                    wasAdded = true;

                                    break;
                                }
                            }
                        }

                        if (!wasAdded) {
                            sorted.add(update[i]);
                        }
                    }

                    // Add the middle number to the sum
                    sum += sorted.get(sorted.size() / 2);
                }
            }
        }

        return sum;
    }
}
