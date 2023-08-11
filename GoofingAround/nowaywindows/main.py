import pygame as pg
from pygame.locals import *
import sys
import os
import time
import random
import math
currentDir = os.path.dirname(os.path.abspath(__file__))

# define pg constants
pg.init()
pg.display.set_caption("Michealsoft Binbows")

# define colors
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)
RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
YELLOW = (255, 255, 0)
CYAN = (0, 255, 255)
MAGENTA = (255, 0, 255)
# choose a random image from the images folder
image = random.choice(os.listdir(currentDir+ "/images"))
# load the image
image = pg.image.load(os.path.join(currentDir+ "/images", image))
# change the image to the same size as the screen
image = pg.transform.scale(image, (1280, 720))
# define fonts
# set font to arial
pg.font.init()
font = pg.font.SysFont("arial", 32)

# define screen
screen = pg.display.set_mode((1280, 720))
screen.fill(BLACK)

class textInput:
    def __init__(self, text="STANDERED TEXT", x=0, y=0):
        self.text = text
        self.x = x
        self.y = y
        self.text = font.render(self.text, True, WHITE)
        self.rect = pg.Rect(self.x-5, self.y, 250, 40)
        self.textt = ""

    def draw(self):
        pg.draw.rect(screen, WHITE, self.rect, 2)
        screen.blit(self.text, (self.x, self.y))
    
    def clicked(self):
        if pg.mouse.get_pressed()[0]:
            if pg.mouse.get_pos()[0] >= self.x and pg.mouse.get_pos()[0] <= self.x + self.text.get_width():
                if pg.mouse.get_pos()[1] >= self.y and pg.mouse.get_pos()[1] <= self.y + self.text.get_height():
                    self.text = font.render("", True, WHITE)
                    return True
        return False
    
    def textInput(self):
        #if self.clicked():
            for event in pg.event.get():
                if event.type == pg.KEYDOWN:
                    if event.key == pg.K_BACKSPACE:
                        self.textt = self.textt[:-1]
                    else:
                        self.textt = self.textt + event.unicode
                    self.text = font.render(self.textt, True, WHITE)
                    self.rect = pg.Rect(self.x-5, self.y, 250, 40)

username = textInput("Username", 530, 400)


# define clock
clock = pg.time.Clock()

USER = "Other User"
usertext = font.render(USER, True, WHITE)
usertext_rect = usertext.get_rect(center=(640, 360))
userImage = pg.image.load(currentDir+ "/user.png")
while True:
    # set fps
    clock.tick(60)
    # check for quit
    for event in pg.event.get():
        if event.type == QUIT:
            pg.quit()
            sys.exit()
        if event.type == KEYDOWN:
            if event.key == K_ESCAPE:
                pg.quit()
                sys.exit()
    


    # update screen
    screen.fill(BLACK)
    screen.blit(image, (0, 0))
    # show text at the middle of the screen
    screen.blit(usertext, usertext_rect)
    # show image at the middle of the screen aboe the text
    screen.blit(userImage, (580, 200))
    username.draw()
    username.textInput()

    pg.display.update()
    pg.display.flip()

    