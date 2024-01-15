#Guglio
# October 27th 2022 
# Make a number guessing game. Generate a random number from 1 - 100 and ask the user to guess it. To help them guess more accurately, tell the user if their guess was too high or too low after each incorrect guess.

import random
count = 0
number = random.randint(1, 100)
guess = 0
while guess != number:
    guess = int(input("Guess a number from 1 to 100: "))
    if guess > number:
        print("Too high!")
    elif guess < number:
        print("Too low!")
    count = count + 1
print("You got it!")
print("It took you " + str(count) + " tries to guess the number.")