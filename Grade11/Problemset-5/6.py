# Guglio
# This program calculates your net loss/gain 
# 2022/10/17
Revenue = float(input("Enter revenue: "))
expenses = int(input("Enter expenses: "))
NetProfit = Revenue - expenses
if NetProfit > 0:
    print(f"You earned a profit of: ${NetProfit}")
elif NetProfit < 0:
    print(f"You suffered a loss of ${-NetProfit}")
else:
    print("You broke even")