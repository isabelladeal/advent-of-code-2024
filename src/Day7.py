import time
import re


def branch_1(nums, target, current):
    if len(nums) == 0:
        return current == target

    a = branch_1(nums[1:], target, current + nums[0])
    b = branch_1(nums[1:], target, current * nums[0])

    return a or b


def part_1(filename):
    with open(filename) as file:
        lines = file.readlines()

    sum = 0

    for line in lines:
        input = re.split(': | ', line)

        target = int(input[0])

        nums = input[1:]
        nums = [int(num) for num in nums]

        if branch_1(nums[1:], target, nums[0]):
            sum += target

    return sum


def branch_2(nums, target, current):
    if len(nums) == 0:
        return current == target

    a = branch_2(nums[1:], target, current + nums[0])
    b = branch_2(nums[1:], target, current * nums[0])
    c = branch_2(nums[1:], target, int(str(current) + str(nums[0])))

    return a or b or c


def part_2(filename):
    with open(filename) as file:
        lines = file.readlines()

    sum = 0

    for line in lines:
        input = re.split(': | ', line)

        target = int(input[0])

        nums = input[1:]
        nums = [int(num) for num in nums]

        if branch_2(nums[1:], target, nums[0]):
            sum += target

    return sum


start = time.time()
result = part_1('test/day_7.txt')
end = time.time()
print(f'Part 1 test: {result} in {round((end - start) * 1000)}ms')

start = time.time()
result = part_1('input/day_7.txt')
end = time.time()
print(f'Part 1 input: {result} in {round((end - start) * 1000)}ms')

start = time.time()
result = part_2('test/day_7.txt')
end = time.time()
print(f'Part 2 test: {result} in {round((end - start) * 1000)}ms')

start = time.time()
result = part_2('input/day_7.txt')
end = time.time()
print(f'Part 2 input: {result} in {round((end - start) * 1000)}ms')
