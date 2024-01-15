import pygame as pg


class Bullet:
    img = pg.image.load("assets/sprites/bullethole.png")

    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __str__(self):
        return "Bullet at ({}, {})".format(self.x, self.y)

    def draw(self, DISPLAY):
        DISPLAY.blit(self.img, (self.x, self.y))
