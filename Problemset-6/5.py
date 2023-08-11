"""
Guglio
This program calulates how many of the given numbers are the same
"""
numlist = [
    int(input("Input the first number: ")),
    int(input("Input the second number: ")),
    int(input("Input the third number: "))
]
# write a program that finds out how many numbers are the same
number = numlist[0]
count = -1
for num in numlist:
    if num == number:
        count += 1
if count > 0:
    print(f"{count+1} were the same!")
if count == 0:
    print("All were different!")