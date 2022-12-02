import pygame as pg
def goofyGradient(screen, left_colour, right_colour, target_rect):
    """ make a funny-looking gradient filled rectangle covering <target_rect> """
    colour_rect = pg.Surface( ( 2, 2 ) )                                   # tiny! 2x2 bitmap
    pg.draw.line( colour_rect, left_colour,  ( 0,0 ), ( 1,1 ) )            
    pg.draw.line( colour_rect, right_colour, ( 0,1 ), ( 1,0 ) )           
    colour_rect = pg.transform.smoothscale( colour_rect, ( target_rect.width, target_rect.height ) )  # stretch!
    screen.blit( colour_rect, target_rect )

class Button:
    def __init__(
        self, x=0, y=0, width=150, height=50, text="BUTTON", colour=(255, 255, 255), colour2=(0,0,0)):
        self.x = x
        self.y = y
        self.width = width
        self.height = height
        self.text = text
        self.colour = colour
        self.colour2 = colour2
        self.font = pg.font.SysFont("comicsansms", 20)
        self.textOutline = self.font.render(self.text, True, (200, 200, 200))
        self.textt = self.font.render(self.text, True, (0, 0, 0))
        self.textRect = self.textt.get_rect()
        self.rect = pg.Rect(self.x, self.y, self.width, self.height)

    def draw(self, screen):
        #pg.draw.rect(screen, self.colour, self.rect)
        # draw a slightly darker rectangle to make a 3D effect with slight transparency
        goofyGradient(screen, self.colour, self.colour2, self.rect)
        # get the center of the button
        self.textRect.center = (
            int(self.x + self.width / 2),
            int(self.y + self.height / 2),
        )
        screen.blit(self.textOutline, (self.textRect.x - 1, self.textRect.y - 1))
        screen.blit(self.textOutline, (self.textRect.x + 1, self.textRect.y - 1))
        screen.blit(self.textOutline, (self.textRect.x - 1, self.textRect.y + 1))
        screen.blit(self.textOutline, (self.textRect.x + 1, self.textRect.y + 1))
        screen.blit(self.textt, self.textRect)

    def checkCollision(
        self, a_x, a_y, a_width, a_height, b_x, b_y, b_width, b_height):  
        # check the collision of a current object with another object, returns a boolean
        return (
            (a_x + a_width > b_x)
            and (a_x < b_x + b_width)
            and (a_y + a_height > b_y)
            and (a_y < b_y + b_height)
        )

    def checkClick(self, x, y):
        if self.checkCollision(self.x, self.y, self.width, self.height, x, y, 1, 1):
            return True
        return False
