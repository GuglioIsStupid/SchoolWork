# Guglio
# November 28th 2022
# Create a slot machine program. The user starts with 100 tokens. 
# With each “pull” of the handle, the use loses 1 token and the computer spins 3 wheels, each consisting of the number 1, 2 and 3. 
# If all three numbers are 1, the user gets 4 tokens; if all are 2, the user gets 8 tokens; 
# if all are 3, the user gets 12 tokens. Display the number of tokens to the user after each “pull”, and the result of each spin should also be displayed. 
# The program ends when the user is either out of tokens, or decides to walk away.

import random

tokens = 100

while tokens > 0:
    print("You have", tokens, "tokens.")
    choice = input("Would you like to play or leave? ")
    if choice.lower() == "leave": # lower is used to convert the string to lowercase
        break
    else: 
        tokens -= 1 # subtract 1 token for the cost of playing
        num1 = random.randint(1, 3)
        num2 = random.randint(1, 3)
        num3 = random.randint(1, 3)
        print(num1, num2, num3) # print the numbers
        if num1 and num2 and num3 == 1: # if all three numbers are 1
            tokens += 4
        elif num1 and num2 and num3 == 2: # if all three numbers are 2
            tokens += 8
        elif num1 and num2 and num3 == 3: # if all three numbers are 3
            tokens += 12 
        else:
            tokens += 0