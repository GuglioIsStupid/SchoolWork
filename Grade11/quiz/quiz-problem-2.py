"""
Guglio
Quiz - 1 - problem-2.py
"""
num = input("Please enter a number: ")

try:
    num = int(num)
except:
    print("Please enter a valid number.")
    exit()

if num < 0:
    print(f"The number {num} is less than 0.")
elif num > 0:
    print(f"The number {num} is greater than.")
else:
    print(f"The number {num} is zero.")
