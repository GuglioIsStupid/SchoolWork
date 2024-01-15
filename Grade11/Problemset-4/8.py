# Guglio
# This program splits a whole 2 digit number into its digits
twoDigitNum = int(input("Enter a two digit number: "))
firstDigit = twoDigitNum // 10
secondDigit = twoDigitNum % 10
print(f"""
The first digit is:  {firstDigit}
The second digit is: {secondDigit}
""")