# Guglio
# November 28th 2022
# Write a program that will display the sum of all of the numbers between two values entered by the user. 
# The program should also display an expression showing what specific numbers were summed.

startNum = int(input("Enter the starting number: "))
endNum = int(input("Enter the ending number: "))
num = 0

for i in range(startNum, endNum + 1):
    num += i 
    if i == endNum:
        print(i, end=" = ") # end can be used to remove the newline character
    else:
        print(i, end=" + ")
    
print(num)