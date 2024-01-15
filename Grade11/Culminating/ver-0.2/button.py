import pygame as pg

class Button():
    def __init__(self, x=0, y=0, width=150, height=50, text="BUTTON", colour=(255, 255, 255)):
        self.x = x
        self.y = y
        self.width = width
        self.height = height
        self.text = text
        self.colour = colour
        self.font = pg.font.SysFont("comicsansms", 20)
        self.text = self.font.render(self.text, True, (0, 0, 0))
        self.textRect = self.text.get_rect()
        self.rect = pg.Rect(self.x, self.y, self.width, self.height)
    
    def draw(self, screen):
        pg.draw.rect(screen, self.colour, self.rect)
        # get the center of the button
        self.textRect.center = (int(self.x + self.width/2), int(self.y + self.height/2))
        screen.blit(self.text, self.textRect)
    
    def checkCollision(self, a_x, a_y, a_width, a_height, b_x, b_y, b_width, b_height): # check the collision of a current object with another object, returns a boolean
        return (a_x + a_width > b_x) and (a_x < b_x + b_width) and (a_y + a_height > b_y) and (a_y < b_y + b_height)
    
    def checkClick(self, x, y):
        if self.checkCollision(self.x, self.y, self.width, self.height, x, y, 1, 1):
            return True
        return False