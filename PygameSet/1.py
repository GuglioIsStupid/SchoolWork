# Guglio
# 2022/11/03
import sys
import pygame as pg

pg.init()
screen = pg.display.set_mode((800, 600))
pg.display.set_caption("Exercise #1")
clock = pg.time.Clock()

RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
YELLOW = (255, 255, 0)
WHITE = (255, 255, 255)
ORANGE = (255, 165, 0)
PURPLE = (128, 0, 128)

redBox = pg.Surface((100, 99))
redBox.fill(RED)

greenBox = pg.Surface((100, 99))
greenBox.fill(GREEN)

blueBox = pg.Surface((100, 99))
blueBox.fill(BLUE)

purpleBox = pg.Surface((100, 99))
purpleBox.fill(PURPLE)

yellowBOX = pg.Surface((50, 100))
yellowBOX.fill(YELLOW)

orangeBOX = pg.Surface((100, 50))
orangeBOX.fill(ORANGE)

whiteBOX = pg.Surface((25, 25))
whiteBOX.fill(WHITE)

redOutlineRects = []
for i in range(12):
    redOutlineRects.append(pg.Rect(150, 5, 550, 95))
    redOutlineRects[i].center = (400, 550)
    redOutlineRects[i].inflate_ip(-i*55, -i*10)

while True:
    for event in pg.event.get():
        if event.type == pg.QUIT:
            pg.quit()
            sys.exit() # this breaks out of the while loop and ends the program
    screen.fill((0, 0, 0))

    screen.blit(redBox, (0, 0))
    screen.blit(greenBox, (700, 0))
    screen.blit(blueBox, (0, 501))
    screen.blit(purpleBox, (700, 501))
    for i in range(100, 700, 50):
        screen.blit(yellowBOX, (i, 0))
        pg.draw.rect(yellowBOX, (0, 0, 0), yellowBOX.get_rect(), 1)

    for i in range(0, 800, 700):
        for j in range(100, 500, 50):
            screen.blit(orangeBOX, (i, j))
            pg.draw.rect(orangeBOX, (0, 0, 0), orangeBOX.get_rect(), 1)

    for i in range (100, 700, 25):
        for j in range (100, 500, 25):
            screen.blit(whiteBOX, (i, j))
            pg.draw.rect(whiteBOX, (0, 0, 0), whiteBOX.get_rect(), 1)
            
    for i in range(12):
        pg.draw.rect(screen, (255, 0, 0), redOutlineRects[i], 1)

    pg.display.flip()
    pg.display.update()
    clock.tick(60)