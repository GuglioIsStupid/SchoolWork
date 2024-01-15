# Guglio
# This program says how many negative numbers are in a list
# 2022/10/17
a = [
    int(input("Enter the first number: ")),
    int(input("Enter the second number: ")),
    int(input("Enter the third number: ")),
    int(input("Enter the fourth number: ")),
    int(input("Enter the fifth number: "))
]
underZero = 0
for i in a:
    if i < 0:
        underZero += 1
print(f"There are {underZero} negative numbers")
