import math
firstNum = float(input("Enter the first number: "))
secondNum = float(input("Enter the second number: "))
# round to 1 decimal place
firstNum = round(firstNum, 1)
secondNum = round(secondNum, 1)
print(f"""
{firstNum} + 4.9 = {firstNum + 4.9}
{firstNum} - 4.9 = {firstNum - 4.9}
{firstNum} x 4.9 = {firstNum * 4.9}
{firstNum} / 4.9 = {firstNum / 4.9}
{firstNum} % 4.9 = {firstNum % 4.9}
{firstNum} ^ 4.9 = {math.pow(firstNum, 4.9)}
""")