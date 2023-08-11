# Guglio
# 2022/11/03
import sys
import pygame as pg
import math

pg.init()
screen = pg.display.set_mode((800, 600))
pg.display.set_caption("Exercise #2")
clock = pg.time.Clock()

RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
PURPLE = (255, 0, 255)
YELLOW = (255, 255, 0)
BROWN = (165, 42, 42)
MAROON = (128, 0, 0)
WHITE = (255, 255, 255)
LIGHTBLUE = (0, 191, 255)
BROWN2 = (145,82,45)
BLACK = (0, 0, 0)
THEROADCOLOUR = (60, 60, 60)
ORANGE = (255, 165, 0)
DARKPURPLE = (128, 0, 128)
MAGENTAPURPLE = (128, 0, 128)

MX, MY = pg.mouse.get_pos()

ground = pg.Surface((800, 500))
ground.fill(BROWN2)

window = [
    pg.Surface((35, 35)),
    pg.Surface((35, 35)),
    pg.Surface((35, 35)),
    pg.Surface((35, 35)),
]

for i in range(4):
    window[i].fill(BROWN2)
sidewalk = []
for i in range(16):
    sidewalk.append(pg.Surface((50, 50)))
    sidewalk[i].fill((128, 128, 128))

roadLines = []
for i in range(8):
    roadLines.append(pg.Surface((75, 15)))
    roadLines[i].fill((255, 255, 255))

while True:
    for event in pg.event.get():
        if event.type == pg.QUIT:
            pg.quit()
            sys.exit() # this breaks out of the while loop and ends the program

    MX, MY = pg.mouse.get_pos()
    screen.fill(LIGHTBLUE)

    # make a rainbow of red, orange, yellow, green, blue, dark purple, magenta
    # use arcs to make the rainbow to make a half circle
    # each colour moves down the screen by 50 pixels
    pg.draw.arc(screen, RED, (0, 0, 800, 800), 0, -math.pi/2, 10)
    pg.draw.arc(screen, ORANGE, (0, 10, 800, 800), 0, -math.pi/2, 10)
    pg.draw.arc(screen, YELLOW, (0, 20, 800, 800), 0, -math.pi/2, 10)
    pg.draw.arc(screen, GREEN, (0, 30, 800, 800), 0, -math.pi/2, 10)
    pg.draw.arc(screen, BLUE, (0, 40, 800, 800), 0, -math.pi/2, 10)
    pg.draw.arc(screen, DARKPURPLE, (0, 50, 800, 800), 0, -math.pi/2, 10)
    pg.draw.arc(screen, MAGENTAPURPLE, (0, 60, 800, 800), 0, -math.pi/2, 10)

    # draw the ground
    screen.blit(ground, (0, 250))

    # make a circle for the sun
    pg.draw.circle(screen, YELLOW, (0, 0), 85)
    pg.draw.line(screen, YELLOW, (30, 146), (0, 85), 5)
    pg.draw.line(screen, YELLOW, (131, 64), (85, 0), 5)
    pg.draw.line(screen, YELLOW, (100, 100), (0, -85), 5)
    pg.draw.line(screen, YELLOW, (65, 125), (37, 73), 5)

    # draw a little lake next to the house :) with a 1150x75 ellipse
    pg.draw.ellipse(screen, BLUE, (250, 250, 170, 100))
    # draw ellipses getting smaller to make the water ripple
    pg.draw.ellipse(screen, WHITE, (260, 260, 150, 80), 1)
    pg.draw.ellipse(screen, WHITE, (270, 270, 140, 60), 1)
    pg.draw.ellipse(screen, WHITE, (280, 280, 100, 40), 1)
    pg.draw.ellipse(screen, WHITE, (290, 290, 80, 20), 1)

    # draw the house
    pg.draw.rect(screen, MAROON, (350, 200, 350, 150))
    # draw a polygon for the roof
    pg.draw.polygon(screen, BROWN2, ((350, 200), (700, 200), (525, 100)))

    screen.blit(window[0], (550, 225))
    screen.blit(window[1], (600, 225))
    screen.blit(window[2], (550, 275))
    screen.blit(window[3], (600, 275))

    # make door
    pg.draw.rect(screen, BROWN2, (450, 275, 50, 75))
    # make door knob
    pg.draw.circle(screen, BLACK, (490, 325), 5)

    # draw the sidewalk
    for i in range(16):
        screen.blit(sidewalk[i], (i * 50, 360))
        # draw an outline around the sidewalk
        pg.draw.rect(screen, BLACK, (i * 50, 360, 50, 50), 1)

    # draw the road
    pg.draw.rect(screen, THEROADCOLOUR, (0, 400, 800, 200))
    # draw the road lines
    for i in range(8):
        screen.blit(roadLines[i], (i * 125, 485))
    
    # print the mouse position
    #print(MX, MY)
    pg.display.flip()
    pg.display.update()
    clock.tick(60)