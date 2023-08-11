import pygame as pg
import random as rd
try:
    import win32api
    import win32con
    import win32gui
except ImportError:
    print("pywin32 is not installed. Please install it.")
    quit()
import os, sys, time, math
targetHit = False
health = 3
score = 0
def main():
    pg.init()
    pg.display.set_caption("shoot go pow")
    pg.mixer.init()
    hurtSound = pg.mixer.Sound("assets/audio/hurt.wav")
    targetPling = pg.mixer.Sound("Assets/audio/targetpling.wav")
    pg.mixer.Sound.set_volume(targetPling, 0.12)
    health = 3
    score = 0
    pg.mouse.set_visible(False)
    DISPLAY = pg.display.set_mode((800, 600))
    fuchsia = (255, 0, 128)  # Transparency color
    RED = (153,0,0)
    crosshair = pg.image.load("assets/sprites/crosshair.png")
    crosshair = pg.transform.scale(crosshair, (17*2, 17*2))
    bulletHole = pg.image.load("assets/sprites/bulletHole.png")
    bulletHole = pg.transform.scale(bulletHole, (8*5, 8*5))
    bulletHoleNew1 = pg.transform.rotate(bulletHole, 0)
    bulletHoleNew2 = pg.transform.rotate(bulletHole, 0)
    bulletHoleNew3 = pg.transform.rotate(bulletHole, 0)
    font = pg.font.Font("assets/fonts/font.ttf", 36)

    gameOver = False

    target = pg.image.load("assets/sprites/target.png")
    targetxy = (0, 0)
    target = pg.transform.scale(target, (64, 64))

    pos = [
        (0,0),
        (0,0),
        (0,0)
    ]

    def checkCollision(a_x, a_y, a_width, a_height, b_x, b_y, b_width, b_height): # check the collision of a current object with another object, returns a boolean
        return (a_x + a_width > b_x) and (a_x < b_x + b_width) and (a_y + a_height > b_y) and (a_y < b_y + b_height)

    bulletHoles = [
        #pg.image.load("assets/sprites/bulletHole.png")
    ]
    for i in range(1, len(bulletHoles)):
        bulletHoles[i] = pg.transform.scale(bulletHoles[i], (8*5, 8*5))

    # heart spritesheet, 7x7 2 frames
    heart = pg.image.load("assets/sprites/hearts.png")
    #heart = pg.transform.chop(heart, (0, 0, 0, 7))
    heartsON = [
        heart.subsurface((0, 0, 14, 14)),  #x, y, width, height
        heart.subsurface((0, 0, 14, 14)),
        heart.subsurface((0, 0, 14, 14))
    ]
    heartsOFF = [
        heart.subsurface((14, 0, 14, 14)),
        heart.subsurface((14, 0, 14, 14)),
        heart.subsurface((14, 0, 14, 14))
    ]

    for i in range(len(heartsON)):
        heartsON[i] = pg.transform.scale(heartsON[i], (7*5,7*5))
    
    for i in range(len(heartsOFF)):
        heartsOFF[i] = pg.transform.scale(heartsOFF[i], (7*5,7*5))
    dark_red = (139, 0, 0)
    BGCOLOUR = (128, 128, 128)
    hwnd = pg.display.get_wm_info()["window"]
    win32gui_windowLong = win32gui.GetWindowLong(hwnd, win32con.GWL_EXSTYLE) | win32con.WS_EX_LAYERED
    win32gui.SetWindowLong(hwnd, win32con.GWL_EXSTYLE, win32gui_windowLong)
    # Set window transparency color
    win32gui.SetLayeredWindowAttributes(hwnd, win32api.RGB(*fuchsia), 0, win32con.LWA_COLORKEY) # if the colour is fushia, the window is transparent

    while True:
        for event in pg.event.get():
            if event.type == pg.QUIT:
                pg.quit()
                quit()
            if event.type == pg.MOUSEBUTTONDOWN:
                if event.button == 1:
                    if not gameOver:
                        if not checkCollision(targetxy[0], targetxy[1], 64, 64, pg.mouse.get_pos()[0], pg.mouse.get_pos()[1], 1, 1):
                            pg.mixer.Sound.play(hurtSound)
                            if health == 3:
                                pos[0] = (pg.mouse.get_pos()[0]-crosshair.get_width()/2, pg.mouse.get_pos()[1]-crosshair.get_height()/2)
                                bulletHoleNew1 = pg.transform.rotate(bulletHole, rd.randint(0,360))
                            elif health == 2:
                                pos[1] = (pg.mouse.get_pos()[0]-crosshair.get_width()/2, pg.mouse.get_pos()[1]-crosshair.get_height()/2)
                                bulletHoleNew2 = pg.transform.rotate(bulletHole, rd.randint(0,360))
                            elif health == 1:
                                pos[2] = (pg.mouse.get_pos()[0]-crosshair.get_width()/2, pg.mouse.get_pos()[1]-crosshair.get_height()/2)
                                bulletHoleNew3 = pg.transform.rotate(bulletHole, rd.randint(0,360))
                            health -= 1
                            print(health)
                            if health == 0:
                                gameOver = True
                        else:
                            pg.mixer.Sound.play(targetPling)
                            score += 1
                            targetxy = (rd.randint(0, 800-32), rd.randint(0, 600-32))
                    #bulletHoles[len(bulletHoles)].xy = (pg.mouse.get_pos()[0]-crosshair.get_width()/2, pg.mouse.get_pos()[1]-crosshair.get_height()/2)
                    #bulletHoles.append(bulletHole)

        if not gameOver:
            DISPLAY.fill(BGCOLOUR)
        else:
            DISPLAY.fill(RED)
        if not gameOver:
            if health < 3:
                DISPLAY.blit(bulletHoleNew1, pos[0])
            if health < 2:
                DISPLAY.blit(bulletHoleNew2, pos[1])
            if health < 1:
                DISPLAY.blit(bulletHoleNew3, pos[2])
            if health >= 1:
                DISPLAY.blit(heartsON[0], (5, 560))
            else:
                DISPLAY.blit(heartsOFF[0], (5, 560))
            if health >= 2:
                DISPLAY.blit(heartsON[1], (5+7*5+5, 560))
            else:
                DISPLAY.blit(heartsOFF[1], (5+7*5+5, 560))
            if health >= 3:
                DISPLAY.blit(heartsON[2], (5+14*5+10, 560))
            else:
                DISPLAY.blit(heartsOFF[2], (5+14*5+10, 560))
            
            DISPLAY.blit(target, targetxy)

            # print score to screen
            scoreText = font.render("Score: " + str(score), True, (0, 0, 0))
            DISPLAY.blit(scoreText, (5, 5))
            
            DISPLAY.blit(crosshair, (pg.mouse.get_pos()[0]-crosshair.get_width()/2, pg.mouse.get_pos()[1]-crosshair.get_height()/2))
        else:
            DISPLAY.blit(font.render("Game Over", True, (0, 0, 0)), (5, 5))
        pg.display.update()

        pg.display.flip()

if __name__ == "__main__":
    
    main()

"""
# Create layered window


while True:
    for event in pg.event.get():
        if event.type == pg.QUIT:
            pg.quit()
            quit()

    screen.fill(fuchsia)  # Transparent background
    pg.draw.rect(screen, dark_red, pg.Rect(30, 30, 60, 60))
    pg.display.update()
"""