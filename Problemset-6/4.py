"""
Guglio
This program calulates one which number is the smallest
"""
numlist = [
    int(input("Input the first number: ")),
    int(input("Input the second number: ")),
    int(input("Input the third number: "))
]

# get the smallest number
smallest = numlist[0]
for num in numlist:
    if num < smallest:
        smallest = num
print(f"The smallest number is {smallest}.")