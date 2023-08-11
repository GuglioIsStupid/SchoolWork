# Guglio
# November 28th 2022
# Write a program that displays the sum of all of the odd numbers from 1 to a maximum value entered by the user.

maximum = int(input("Enter a maximum value: "))
num = 0
for i in range(1, max + 1, 2):
    num += i

print("The sum of all of the odd numbers from 1 to", maximum, "is", num)