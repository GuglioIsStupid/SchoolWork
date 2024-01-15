# Guglio
# November 28th 2022
# Write a program that allows the user to determine for how long they will need to invest their money in order to reach a desired target amount. 
# The user will provide the initial amount of money they are to invest, as well as their annual interest rate. They will also provide their desired target amount. 
# Using this information, output a chart which shows the user how many years their investment will take to reach the target value.

dep = int(input("How much $ are you initially depositing? "))
rate = int(input("What is your annual interest rate? "))
target = int(input("What is your target amount? "))
years = 0

print("Year", "Interest Earned", "Value", sep="\t")
while dep < target:
    print(
        f"{years}",  # Year
        f"${dep * rate / 100:,.2f}",  # Interest Earned
        f"\t${dep:,.2f}",  # Value
        sep="\t" # the separator between each value
    )
    dep = dep + dep * rate / 100 # add the interest to the deposit
    years += 1 # add 1 to the years

print(f"It will take {years} years to reach your target amount of {target}.") # print the amount of years it will take to reach the target amount