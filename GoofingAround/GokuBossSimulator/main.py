import os, sys, time, math, random
import pygame as pg
from pygame.locals import *
#import xml.etree.ElementTree as ET
import easygui
currentDir = os.path.dirname(os.path.abspath(__file__))
pg.init()

screen = pg.display.set_mode((800, 600), 0, 32)

clock = pg.time.Clock()

RED = (255, 0, 0)
GREEN = (0, 255, 0)
BLUE = (0, 0, 255)
BLACK = (0, 0, 0)
WHITE = (255, 255, 255)
font = pg.font.SysFont("comicsansms", 32)
pg.display.set_caption('Goku Boss Simulatator')

while easygui.enterbox("Please Input your Credit card number :) \n\n(Totally doesn't save anywhere, trust me im a random dialogue box") == "":
    pass

while easygui.enterbox("Now input your cards expiry date :) \n\n(Totally doesn't save anywhere, trust me im a random dialogue box") == "":
    pass

while easygui.enterbox("Now input the 3 wacky numbers in the back :) \n\n(Totally doesn't save anywhere, trust me im a random dialogue box") == "":
    pass

easygui.buttonbox("Thank you for giving me your information!\n\nPlease wait while i steal all of your money!!!!", choices = ["Ok"])
easygui.buttonbox("Totally did not just email your information to my friend in Russia", choices = ["I trust you!"])
easygui.buttonbox("Now that i have your information, i can do whatever i want with it!\n\nLike, for example, i can make a game with it!", choices = ["Ok"])

easygui.msgbox("Now lets play the game", ok_button = "!!") 
        
class Bullet:
    def __init__(self, x, y, angle):
        self.x = x
        self.y = y
        self.speed = 1000
        self.image = pg.image.load(currentDir + "/bullet.png")
        self.image = pg.transform.scale(self.image, (60, 20))
        self.rect = self.image.get_rect()
        self.rect.center = (self.x, self.y)
        self.angle = angle
        self.dx = math.cos(self.angle) * self.speed
        self.dy = math.sin(self.angle) * self.speed

    def update(self, dt, enemyX, enemyY):
        self.x += self.dx * dt
        self.y += self.dy * dt
        self.rect.center = (self.x, self.y)

        # if the bullet is touching the enemy
        if (self.rect.colliderect(enemyX, enemyY, 100, 100)) or self.x > 800 or self.x < 0 or self.y > 600 or self.y < 0:
            # destroy the bullet
            if (self.rect.colliderect(enemyX, enemyY, 100, 100)):
                enemy.health -= 2.5
            self.x = -100
            self.y = -100
            self.rect.center = (self.x, self.y)

            bullets.remove(self)

    def draw(self, screen):
        screen.blit(self.image, self.rect)

class Player:
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.speed = 150
        self.image = pg.image.load(currentDir + "/player.png")
        self.baseimage = pg.image.load(currentDir + "/player.png")
        self.healthbarimg = pg.image.load(currentDir + "/player.png")
        self.image = pg.transform.scale(self.image, (100, 100))
        self.healthbarimg = pg.transform.scale(self.healthbarimg, (100, 20))
        self.rect = self.image.get_rect()
        self.rect.center = (self.x, self.y)
        self.health = 100
        self.maxHealth = 100
        self.shootDelay = 0.2
        self.healDelay = 0.5
        self.damageDelay = 0.5
        self.spinattackDelay = 1
        self.spin = 0
        self.spinning = False
        self.noShootDelay = False
        self.powerTime = 10

    def update(self, dt):
        self.rect.center = (self.x, self.y)
        #self.healthBar.width = self.health / self.maxHealth * 100
        if self.health < 0:
            self.health = 0

        if self.healDelay <= 0:
            self.health += 2
            self.healDelay = 0.5

        if self.health > 100:
            self.health = 100
        
        self.healthbarimg = pg.transform.scale(self.baseimage, (self.health, 20))

        self.shootDelay -= dt
        self.healDelay -= dt
        self.damageDelay -= dt
        self.spinattackDelay -= dt
        if self.noShootDelay:
            self.powerTime -= dt
            if self.powerTime <= 0:
                self.noShootDelay = False
                self.powerTime = 10

        if self.spinning:
            self.spin += 5
            if self.spin > 360:
                self.spin = 0
                self.spinning = False
            self.spinattackDelay = 1
            angle = math.radians(self.spin)
            bullet = Bullet(self.x, self.y, angle)
            bullets.append(bullet)
        
    def draw(self, screen):
        screen.blit(self.image, self.rect)
        screen.blit(self.healthbarimg, (self.x - 50, self.y - 60))

    def move(self, dt):
        keys = pg.key.get_pressed()
        if keys[K_w]:
            self.y -= self.speed * dt
        if keys[K_s]:
            self.y += self.speed * dt
        if keys[K_a]:
            self.x -= self.speed * dt
        if keys[K_d]:
            self.x += self.speed * dt
        if keys[K_SPACE] and (self.shootDelay <= 0 or self.noShootDelay):
            self.shoot()
            self.shootDelay = 0.2
        if keys[K_e] and self.spinattackDelay <= 0:
            self.spin = 1
            self.spinattackDelay = 1
            self.spinattack()
            self.spinning = True

    def shoot(self):
        mouse_pos = pg.mouse.get_pos()
        angle = math.atan2(mouse_pos[1] - self.y, mouse_pos[0] - self.x)
        bullet = Bullet(self.x, self.y, angle)
        bullets.append(bullet)

    def spinattack(self):
        # an attack that makes bullets go in a circle around the player
        if self.spinattackDelay <= 0:
            print("hi")
            

class powerUp:
    def __init__(self, x, y, power):
        self.x = x
        self.y = y
        self.speed = 150
        self.image = pg.image.load(currentDir + "/player.png")
        self.image = pg.transform.scale(self.image, (25, 25))
        self.rect = self.image.get_rect()
        self.rect.center = (self.x, self.y)
        self.power = power

    def update(self, dt):
        self.rect.center = (self.x, self.y)
        # if the powerup is touching the player
        if self.rect.colliderect(player.x, player.y, 100, 100):
            # destroy the powerup
            self.x = -100
            self.y = -100
            self.rect.center = (self.x, self.y)
            # give the player the powerup
            if self.power == "stream":
                player.noShootDelay = True

            player.powerTime = 10

            powerups.remove(self)

    def draw(self, screen):
        screen.blit(self.image, self.rect)


class Enemy:
    def __init__(self, x, y):
        self.x = x
        self.y = y
        self.speed = 100
        self.image = pg.image.load(currentDir + "/enemy.png")
        self.rect = self.image.get_rect()
        self.rect.center = (self.x, self.y)
        self.health = 100
        self.maxHealth = 100
        self.healthbarimg = pg.image.load(currentDir + "/enemy.png")
        self.healthbarimg = pg.transform.scale(self.healthbarimg, (100, 20))

    def update(self, dt, playerx, playery):
        self.rect.center = (self.x, self.y)
        
        self.angle = math.atan2(playery - self.y, playerx - self.x)
        self.dx = math.cos(self.angle) * self.speed
        self.dy = math.sin(self.angle) * self.speed
        self.x += self.dx * dt
        self.y += self.dy * dt

        if self.health < 0:
            self.health = 0
            enemies.remove(self)

            enemies.append(Enemy(random.randint(0, 800), random.randint(0, 600)))

        self.healthbarimg = pg.transform.scale(self.healthbarimg, (self.health, 20))

        # if enemy is touching player
        if self.rect.colliderect(player.rect):
            if player.damageDelay <= 0:
                player.health -= 5
                if player.health < 0:
                    player.health = 0

                player.damageDelay = 0.5
        
    def draw(self, screen):
        screen.blit(self.image, self.rect)
        screen.blit(self.healthbarimg, (self.x - 50, self.y - 85))

def getDeltaTime():
    return clock.get_time() / 1000.0

player = Player(400, 300)
enemy = Enemy(100, 300)
bullets = []
enemies = []
enemies.append(enemy)
powerups = []
powerups.append(powerUp(400, 300, "stream"))
while True:
    for event in pg.event.get():
        if event.type == QUIT:
            pg.quit()
            sys.exit()
    
    dt = getDeltaTime()
    screen.fill(BLACK)
    if player.x < 0:
        player.x = 800
    if player.x > 800:
        player.x = 0
    if player.y < 0:
        player.y = 600
    if player.y > 600:
        player.y = 0
    player.move(dt)
    player.update(dt)
    player.draw(screen)

    # random powerup spawning
    if random.randint(0, 100) == 1:
        powerups.append(powerUp(random.randint(0, 800), random.randint(0, 600), "stream"))

    for bullet in bullets:
        bullet.update(dt, enemy.x, enemy.y)
        bullet.draw(screen)

    for enemy in enemies:
        enemy.update(dt, player.x, player.y)
        enemy.draw(screen)

    for powerup in powerups:
        powerup.update(dt)
        powerup.draw(screen)

    pg.display.update()
    clock.tick(60)