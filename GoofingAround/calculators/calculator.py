"""
2022/11/01-2022/11/02
Calculator program that supports pemdas
"""
import pygame as pg
from modules.button import Button
from math import *
import os
currentDir = os.path.dirname(os.path.abspath(__file__))
# Initialize pygame
pg.init()
pg.mixer.init()
# make music
pg.mixer.music.load(currentDir + "/Fluffing-a-Duck.mp3")
pg.mixer.music.play(-1)
#pg.mixer.music.set_volume(0.1)

# Create the screen
screen = pg.display.set_mode((700, 875))
pg.display.set_caption("Ɔasio duck-300ES PLUS")
title = "ƀasio Duck-300ES PLUS"
# set font to a local font
font = pg.font.Font(currentDir + "/font.otf", 32)
buttons = []
pressed = False
shift = False
ans = 0

# make a list of button text
buttonText = [
    "1", "2", "3", "+",
    "4", "5", "6", "-", 
    "7", "8", "9", "*",
    "0", ".", "/", "(", 
    ")",  "%", "^", "sqrt", 
    "sin",  "cos", "tan", "log", 
    "abs", "clear", "del", "shift",
    "pi", "e", "ans", "="
]

equation = ""

for i in range(len(buttonText)):
    # make a button
    button = Button(
        x=20 + (i % 4) * 180,
         y=120 + (i // 4) * 100,
         width=100,
         height=50,
         text=buttonText[i],
        colour=( 
            buttonText[i] == "=" or 
            buttonText[i] == "sqrt" or 
            buttonText[i] == "sin" or 
            buttonText[i] == "cos" or 
            buttonText[i] == "tan" or
            buttonText[i] == "clear" or 
            buttonText[i] == "del" or 
            buttonText[i] == "log" or 
            buttonText[i] == "abs" or
            buttonText[i] == "shift" or
            buttonText[i] == "pi" or
            buttonText[i] == "e" or
            buttonText[i] == "ans"
        ) and (0, 255, 0) or (255, 255, 255), # if the button is an operator make it green, else make it white
        colour2=(255,255,255),
    )
    # add the button to the list
    buttons.append(button)

while True:
    # check for events
    for event in pg.event.get():
        # check for quit
        if event.type == pg.QUIT:
            pg.quit()
            quit()

        # check for mouse click
        if event.type == pg.MOUSEBUTTONDOWN:
            # get the mouse position
            mousePos = pg.mouse.get_pos()
            # check if any button was clicked
            for button in buttons:
                if button.checkClick(mousePos[0], mousePos[1]):
                    if button.text != "=":
                        if pressed:
                            equation = ""
                            pressed = False
                    if button.text == "=":
                        try:
                            equation = str(eval(equation)) # try to evaluate the equation
                            ans = equation
                        except:
                            try: # if the equation is invalid try to add a ) to the end
                                equation = str(eval(equation + ")"))
                                ans = equation
                            except: # if that doesn't work then the equation is invalid
                                equation = "Error"
                                ans = 0
                        pressed = True
                    elif button.text == "clear":
                        equation = ""
                        pressed = False
                    elif button.text == "sin":
                        equation += shift and "asin(" or "sin("
                    elif button.text == "cos":
                        equation += shift and "acos(" or "cos("
                    elif button.text == "tan":
                        equation += shift and "atan(" or "tan("
                    elif button.text == "^":
                        # replace ^ with ** for python
                        equation += "**"
                    elif button.text == "del":
                        equation = equation[:-1]
                    elif button.text == "sqrt":
                        equation += "sqrt("
                    elif button.text == "log":
                        equation += "log("
                    elif button.text == "abs":
                        equation += "abs("
                    elif button.text == "pi":
                        equation += "pi"
                    elif button.text == "e":
                        equation += "e"
                    elif button.text == "ans":
                        equation += ans
                    elif button.text == "shift":
                        shift = not shift
                        print(shift)
                    else:
                        if pressed:
                            equation = ""
                            pressed = False
                        equation += button.text
                    pass

    # fill the screen with black
    screen.fill((100, 100, 100))

    # draw the buttons
    for button in buttons:
        button.draw(screen)
   
   # if equation is not longer than the screen
    if len(equation) < 30:
        # draw the equation
        screen.blit(font.render(equation, True, (255, 255, 255)), (20, 40))
    else:
        # draw the equation
        screen.blit(font.render("..." + equation[-30:], True, (255, 255, 255)), (20, 40))

    screen.blit(font.render(title, 1, (255, 255, 255)), (20, 0))
    # update the screen
    pg.display.update()