"""
Guglio
This is a mastermind-like game
"""
import random
colours = [
    'red',
    'green',
    'blue',
    'yellow'
]

def get_guess():
    guess = []
    while len(guess) < 3:
        guess.append(input('Guess a colour: '))
    return guess

def generate_code():
    code = []
    while len(code) < 3:
        code.append(random.choice(colours))
    return code

def get_clues(code, guess):
    if guess == code:
        return 'You win!'
    clues = []
    for ind, num in enumerate(guess):
        if num == code[ind]:
            clues.append('Match')
        elif num in code:
            clues.append('Close')
    if clues == []:
        return ['Nope']
    else:
        return clues

def main():
    code = generate_code()
    #print(code)
    while True:
        guess = get_guess()
        clues = get_clues(code, guess)
        print(clues)
        if clues == 'You win!':
            break

main()