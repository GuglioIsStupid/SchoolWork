#Guglio
# October 27th 2022 
# Repeatedly ask the user for a number.  Count how many times it takes until the user enters a negative number. Stop asking for numbers when a negative is entered.

count = 0

number = 0

while number >= 0:
    number = int(input("Enter a number: "))
    count = count + 1

print("You took " + str(count) + " tries to enter a negative number.")