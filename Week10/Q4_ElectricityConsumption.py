from functools import reduce

consumption = [8, 12, 15, 6, 11, 9]

high = list(filter(lambda x: x > 10, consumption))
cost = list(map(lambda x: x * 6, consumption))
total = reduce(lambda x, y: x + y, consumption)

def bill(units):
    return units * 6

print("Consumption:", consumption)
print("Days exceeding 10 units:", high)
print("Daily Electricity Cost:", cost)
print("Total Consumption:", total)
print("Total Electricity Bill:", bill(total))