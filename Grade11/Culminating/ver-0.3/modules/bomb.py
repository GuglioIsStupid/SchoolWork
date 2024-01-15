import pygame as pg

class Bomb:
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.img = pg.image.load("assets/sprites/bomb.png")
        self.img = pg.transform.scale(self.img, (48, 48))

    def __str__(self):
        return "Diamond at ({}, {})".format(self.x, self.y)

    def draw(self, DISPLAY):
        DISPLAY.blit(self.img, (self.x, self.y))
