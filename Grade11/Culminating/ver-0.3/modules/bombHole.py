import pygame as pg

class bombhole:
    img = pg.image.load("assets/sprites/bullethole.png")
    img = pg.transform.scale(img, (72, 72))

    def __init__(self, x, y):
        self.x = x
        self.y = y

    def __str__(self):
        return "Bullet at ({}, {})".format(self.x, self.y)

    def draw(self, DISPLAY):
        DISPLAY.blit(self.img, (self.x, self.y))
