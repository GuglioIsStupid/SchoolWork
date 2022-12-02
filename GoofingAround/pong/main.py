import sys
from xml.sax.handler import DTDHandler
 
import pygame as pg
from pygame.locals import *
import random, os
currentDir = os.path.dirname(os.path.abspath(__file__))
def gradientRectHorz(left_colour, right_colour, target_rect):
    """ Draw a horizontal-gradient filled rectangle covering <target_rect> """
    colour_rect = pg.Surface( ( 2, 2 ) )                                   # tiny! 2x2 bitmap
    pg.draw.line( colour_rect, left_colour,  ( 0,0 ), ( 0,1 ) )            # left colour line
    pg.draw.line( colour_rect, right_colour, ( 1,0 ), ( 1,1 ) )            # right colour line
    colour_rect = pg.transform.smoothscale( colour_rect, ( target_rect.width, target_rect.height ) )  # stretch!
    screen.blit( colour_rect, target_rect )
def gradientRectVert(left_colour, right_colour, target_rect):
    """ Draw a horizontal-gradient filled rectangle covering <target_rect> """
    colour_rect = pg.Surface( ( 2, 2 ) )                                   # tiny! 2x2 bitmap
    pg.draw.line( colour_rect, left_colour,  ( 0,0 ), ( 1,1 ) )            # bottom colour line
    pg.draw.line( colour_rect, right_colour, ( 0,1 ), ( 1,0 ) )            # top colour line
    colour_rect = pg.transform.smoothscale( colour_rect, ( target_rect.width, target_rect.height ) )  # stretch!
    screen.blit( colour_rect, target_rect )
class gradientHorz:
    # (255,0,0), (0,0,255)
    def __init__(self, x=0, y=0, width=640, height=600, colour1=(0,0,0), colour2=(255,255,255)):
        self.rect = pg.Rect(x, y, width, height)
        self.colour1 = colour1
        self.colour2 = colour2
    def draw(self):
        gradientRectVert(self.colour1, self.colour2, self.rect)
grad = gradientHorz(0, 0, 640, 600, (255,0,0), (0,0,255))
pg.init()
c11, c12, c13 = 255, 0, 0
c21, c22, c23 = 0, 0, 255
fps = 60
clock = pg.time.Clock()
 
width, height = 640, 480
screen = pg.display.set_mode((width, height))
p1X = 10
p1Y = 240
p2X = 620
p2Y = 240
pointp1 = 0
pointp2 = 0
ballX, ballY = 640/2-15/2, 480/2-15/2
ballDX = random.randint(1, 2) == 1 and 150 or -150
ballDY = random.randint(-175, 175)
font = pg.font.Font(currentDir+"/font.ttf", 36)

pg.key.set_repeat(True)
# Game loop.
def checkCollision(a_x, a_y, a_width, a_height, b_x, b_y, b_width, b_height): # check the collision of a current object with another object, returns a boolean
    return (a_x + a_width > b_x) and (a_x < b_x + b_width) and (a_y + a_height > b_y) and (a_y < b_y + b_height)
while True:
    screen.fill((0, 0, 0))
    grad.colour1 = (c11, c12, c13)
    grad.colour2 = (c21, c22, c23)
  
    for event in pg.event.get():
      if event.type == QUIT:
        pg.quit()
        sys.exit()
  
    # Update.
    ballX = ballX + ballDX / fps
    ballY = ballY + ballDY / fps
    key = pg.key.get_pressed()
    p1score = font.render(str(pointp1), True, (255, 255, 255))
    p2score = font.render(str(pointp2), True, (255, 255, 255))



    if key[K_RETURN]:
        ballX, ballY = 640/2-15/2, 480/2-15/2

    if checkCollision(ballX, ballY, 8, 8, 0, 480, 640, 1) or checkCollision(ballX, ballY, 8, 8, 0, 0, 640, 1):
        ballDY = -ballDY
    
    if checkCollision(ballX, ballY, 8, 8, p1X, p1Y, 10, 100) or checkCollision(ballX, ballY, 8, 8, p2X, p2Y, 10, 100):
        ballDX = -ballDX
        ballDY = random.randint(-175, 175)
    
    if not checkCollision(p1X, p1Y, 10, 100, 0, 480, 640, 1) or checkCollision(p1X, p1Y, 10, 100, 0, 0, 640, 1):
        if key[K_DOWN] or key[K_s]:
            p1Y += 200 / fps
        elif key[K_UP] or key[K_w]:
            p1Y -= 200 / fps

    if ballX < 0:
        pointp2 += 1
        ballDX = -ballDX
        ballDY = random.randint(-175, 175)
        ballX, ballY = 640/2-15/2, 480/2-15/2
    if ballX > 640:
        pointp1 += 1
        ballDX = -ballDX
        ballDY = random.randint(-175, 175)
        ballX, ballY = 640/2-15/2, 480/2-15/2

    if checkCollision(p1X, p1Y, 10, 100, 0, 480, 640, 1):
        p1Y -= 5
    if checkCollision(p1X, p1Y, 10, 100, 0, 0, 640, 1):
        p1Y += 5

    if not checkCollision(p2X, p2Y, 10, 100, 0, 480, 640, 1) or checkCollision(p2X, p2Y, 10, 100, 0, 0, 640, 1):
        if ballDX == 150:
            if p2Y+50 != ballY:
                if p2Y+50 > ballY:
                    p2Y -= 120 / fps
                else:
                    p2Y += 120 / fps
    
    if checkCollision(p2X, p2Y, 10, 100, 0, 480, 640, 1):
        p2Y -= 5
    if checkCollision(p2X, p2Y, 10, 100, 0, 0, 640, 1):
        p2Y += 5
    
    # Draw.
    grad.draw()
    pg.draw.rect(screen, (255,255,255), pg.Rect(p1X, p1Y, 10, 100))
    pg.draw.rect(screen, (255,255,255), pg.Rect(p2X, p2Y, 10, 100))
    pg.draw.circle(screen, (255,255,255), (ballX, ballY), 8, 0)

    screen.blit(p1score, (150, 50))
    screen.blit(p2score, (460, 50))
    pg.display.flip()
    clock.tick(fps)