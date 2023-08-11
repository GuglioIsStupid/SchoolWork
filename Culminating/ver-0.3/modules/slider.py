import pygame as pg

class Slider:
    def __init__(self, name, x, y, w, h, val):
        self.name = name # name is the sliders name   
        self.val = val 
        self.x = x
        self.y = y
        self.w = self.val * 200
        self.ow = w
        self.h = h
        #self.slider = pg.Rect(self.x-100, self.y, self.w, self.h)
        self.slider2 = pg.Rect(self.x, self.y, self.ow, self.h)
        self.slider = pg.Rect(self.x-100, self.y-10, self.w, self.h)
        self.slider2.center = (self.x, self.y)
        #self.slider2.center = (self.x, self.y)
        self.font = pg.font.SysFont("comicsansms", 20)
        self.nameOutline = self.font.render(self.name, True, (200, 200, 200))
        self.name = self.font.render(self.name, True, (0, 0, 0))
        self.textRect = self.name.get_rect()
        self.textRect.center = (self.x, self.y-22)

    def draw(self, DISPLAY):
        # draw a rectangle under the slider
        pg.draw.rect(DISPLAY, (128, 0, 128), pg.Rect(self.x-102, self.y-12, self.ow+4, self.h+4))
        pg.draw.rect(DISPLAY, (0, 0, 0), self.slider2)
        pg.draw.rect(DISPLAY, (255, 255, 255), self.slider)
        DISPLAY.blit(self.nameOutline, (self.textRect.x-1, self.textRect.y-1))
        DISPLAY.blit(self.nameOutline, (self.textRect.x+1, self.textRect.y+1))
        DISPLAY.blit(self.nameOutline, (self.textRect.x-1, self.textRect.y+1))
        DISPLAY.blit(self.nameOutline, (self.textRect.x+1, self.textRect.y-1))
        DISPLAY.blit(self.name, self.textRect)

    def getx(self):
        return self.x
    def gety(self):
        return self.y
    def getwidth(self):
        return self.w
    def getheight(self):
        return self.h

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
        if self.checkCollision(self.x, self.y-10, self.ow+1, self.h, x, y, 1, 1):
            # set the value of the slider to the x divided by the width of the slider
            self.update_val((x - self.x) / self.ow)
            

    def update_val(self, val):
        self.val = val
        self.w = self.val * 200 
        self.slider = pg.Rect(self.x-100, self.y-10, self.w, self.h)
        self.slider2 = pg.Rect(self.x-100, self.y-10, self.ow, self.h)
        #print(val)
        

    def get_val(self):
        return self.val

    def __str__(self):
        return "Slider at ({}, {})".format(self.x, self.y)