from functools import reduce

marks = [45, 67, 32, 89, 50, 76, 95]

passed = list(filter(lambda x: x >= 50, marks))
updated = list(map(lambda x: min(x + 5, 100), marks))
total = reduce(lambda x, y: x + y, updated)

print("Original Marks:", marks)
print("Students scoring 50 or above:", passed)
print("Marks after grace:", updated)
print("Total Marks:", total)