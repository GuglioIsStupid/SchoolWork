# Guglio
# This program calculates gets the amunt of quarters, dimes, nickels, and pennies in the amount given
pennies = 0
nickels = 0
dimes = 0
quarters = 0
theChange = input("Enter an amount of change less than $1.00: ")
theChange = float(theChange)
theChange = theChange * 100
theChange = int(theChange)
while theChange >= 25:
    theChange = theChange - 25
    quarters = quarters + 1
while theChange >= 10:
    theChange = theChange - 10
    dimes = dimes + 1
while theChange >= 5:
    theChange = theChange - 5
    nickels = nickels + 1
while theChange >= 1:
    theChange = theChange - 1
    pennies = pennies + 1
print(f"""
Pennies: {pennies}
Nickels: {nickels}
Dimes: {dimes}
Quarters: {quarters}
""")