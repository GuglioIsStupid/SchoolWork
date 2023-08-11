# Guglio
# This program calculates your salary from sales 
# 2022/10/17
NAME = input("Enter your name: ")
sales = int(input("Enter sales: "))
if sales > 200:
    salary = 300 + (sales * 0.05)
else:
    salary = 300

print(f"{NAME}'s Salary is: ${salary}")