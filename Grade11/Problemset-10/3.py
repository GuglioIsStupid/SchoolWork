# Guglio
# November 28th 2022
# Write a program that calculates the average of the product of all of the numbers from 1 to a number entered by a user.
num = int(input("Enter a number: "))
product = 0 
newNum = ""
for i in range(1, num + 1):
    product += i
    if i == num:
        newNum = newNum + f"{i}"
    else:
        newNum = newNum + f"{i} * "
    
print("The product of all of the numbers from 1 to", num, "is", eval(newNum)) # eval is used to evaluate a string as a python expression
print("The average of the product of all of the numbers from 1 to", num, "is", eval(newNum) / num) # eval is used to evaluate a string as a python expression