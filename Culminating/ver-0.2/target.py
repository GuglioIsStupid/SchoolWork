import pygame as pg
import random as rd

class Target():
    def __init__(self, img, x, y):
        self.img = img
        self.x = x
        self.y = y
        self.img = pg.transform.scale(self.img, (64, 64))
    def __str__(self):
        return "Target at ({}, {})".format(self.x, self.y)
    def draw(self, DISPLAY):
        DISPLAY.blit(self.img, (self.x, self.y))
