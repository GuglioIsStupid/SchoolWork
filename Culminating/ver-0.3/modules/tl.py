import pygame as pg

class TextLabel:
    def __init__(self, text, x, y, color=(0,0,0)):
        self.text = text
        self.x = x
        self.y = y
        self.font = pg.font.SysFont("comicsansms", 36)
        self.color = color
        self.textOutline = self.font.render(self.text, True, (200, 200, 200))
        self.text = self.font.render(self.text, True, self.color)
        self.textRect = self.text.get_rect()
        self.textRect.center = (self.x, self.y-20)

    def __str__(self):
        return "TextLabel at ({}, {})".format(self.x, self.y)

    def draw(self, DISPLAY):
        DISPLAY.blit(self.textOutline, (self.textRect.x-1, self.textRect.y-1))
        DISPLAY.blit(self.textOutline, (self.textRect.x+1, self.textRect.y+1))
        DISPLAY.blit(self.textOutline, (self.textRect.x-1, self.textRect.y+1))
        DISPLAY.blit(self.textOutline, (self.textRect.x+1, self.textRect.y-1))
        DISPLAY.blit(self.text, self.textRect)