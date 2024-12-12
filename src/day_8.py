def part_1(filename):
    lines = open(filename).read().splitlines()

    height = len(lines)
    width = len(lines[0])

    antinodes = set()

    for y, line in enumerate(lines):
        for x, char in enumerate(line):
            if char == '.':
                continue

            for y2, line2 in enumerate(lines):
                for x2, char2 in enumerate(line2):
                    if char != char2:
                        continue

                    if x == x2 and y == y2:
                        continue

                    antinode_x = x - (x2 - x)
                    antinode_y = y - (y2 - y)

                    if 0 <= antinode_x < width and 0 <= antinode_y < height:
                        antinodes.add((antinode_x, antinode_y))

    return len(antinodes)


def part_2(filename):
    lines = open(filename).read().splitlines()

    height = len(lines)
    width = len(lines[0])

    antinodes = set()

    for y, line in enumerate(lines):
        for x, char in enumerate(line):
            if char == '.':
                continue

            for y2, line2 in enumerate(lines):
                for x2, char2 in enumerate(line2):
                    if char != char2:
                        continue

                    if x == x2 and y == y2:
                        continue

                    antinode_x = x
                    antinode_y = y

                    while 0 <= antinode_x < width and 0 <= antinode_y < height:
                        antinodes.add((antinode_x, antinode_y))

                        antinode_x -= (x2 - x)
                        antinode_y -= (y2 - y)

    return len(antinodes)


print(f'Part one test: {part_1('test/day_8.txt')}')
print(f'Part one input: {part_1('input/day_8.txt')}')
print(f'Part two test: {part_2('test/day_8.txt')}')
print(f'Part two input: {part_2('input/day_8.txt')}')
