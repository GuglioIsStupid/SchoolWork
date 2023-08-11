# Guglio
# This program 
itemname = input("Enter the name of the item you are buying: ")
cost = float(input(f"Enter the cost of a {itemname} cost: "))
howmanywillbuy = int(input(f"How many {itemname} will you buy: "))
totalcost = cost * howmanywillbuy
totalcostwithtax = totalcost * 1.13
payingamount = float(input(f"How much money are you paying with: "))
change = payingamount - totalcostwithtax
print(f"The total cost of your {howmanywillbuy} {itemname} is ${totalcostwithtax:.2f}")
print(f"Your change is ${change:.2f}")
print("Thank you for your business!")