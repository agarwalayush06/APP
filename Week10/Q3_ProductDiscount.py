prices = [500, 1200, 2500, 800, 1500]

def discount(price):
    return price * 0.8 if price > 1000 else price

eligible = list(filter(lambda x: x > 1000, prices))
updated = list(map(discount, prices))

print("Original Prices:", prices)
print("Eligible Products:", eligible)
print("Discounted Prices:", updated)