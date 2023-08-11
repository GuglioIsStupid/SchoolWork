# Guglio
# This program checks if a number is negative or positive
# 2022/10/17
num = int(input("Enter a number: "))
if num < 0:
    print(f"{num} is negative")
elif num > 0:
    print(f"{num} is positive")
else:
    print(f"{num} is zero")