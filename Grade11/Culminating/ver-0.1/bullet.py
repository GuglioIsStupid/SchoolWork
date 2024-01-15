import pygame as pg
class Bullet:
    def __init__(self):
        self.sprite = pg.image.load("assets/sprites/bulletHole.png")
        self.sprite = pg.transform.scale(self.sprite, (8*5, 8*5))
        self.posistion = pg.Vector2()
        self.posistion.xy
        self.rotation = 0