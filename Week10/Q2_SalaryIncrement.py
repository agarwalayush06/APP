from functools import reduce

salaries = [35000, 45000, 28000, 50000, 38000]

eligible = list(filter(lambda x: x < 40000, salaries))
revised = list(map(lambda x: x * 1.10 if x < 40000 else x, salaries))
total = reduce(lambda x, y: x + y, revised)

print("Original Salaries:", salaries)
print("Eligible Salaries:", eligible)
print("Revised Salaries:", revised)
print("Total Salary Expenditure:", total)