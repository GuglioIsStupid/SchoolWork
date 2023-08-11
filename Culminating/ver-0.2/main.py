# 12/09/2022 - ??/??/????
from distutils.log import error
from inspect import FullArgSpec
import pygame as pg
from bullet import Bullet
from target import Target
from diamond import Diamond
from button import Button
import modules.gradient
import random, os
false, true = False, True # i tend to write lowercase a lot
try:
    import win32api
    import win32con
    import win32gui
except ImportError:
    error("pywin32 is not installed. Please install it.")
    quit()
menu = "MainMenu"
def curMenu(): # return the current menu
    return menu
def setMenu(newMenu): # set the current menu
    global menu
    menu = newMenu
    print(menu)
def checkCollision(a_x, a_y, a_width, a_height, b_x, b_y, b_width, b_height): # check the collision of a current object with another object, returns a boolean
    return (a_x + a_width > b_x) and (a_x < b_x + b_width) and (a_y + a_height > b_y) and (a_y < b_y + b_height)
def mainGame():
    try:
        USERNAME = os.environ.get("USERNAME")
        fp = open(USERNAME+"-highscore.txt")
        highscore = int(fp.read())
        fp.close()
    except FileNotFoundError:
        USERNAME = os.environ.get("USERNAME")
        highscore = 0
        fp = open(USERNAME+"-highscore.txt", "w")
        fp.write(str(highscore))
        fp.close()
    highscoreText = font.render("Highscore: " + str(highscore), True, (0, 0, 0))
    score = 0
    health = 3
    bullets = []
    diamondAvailable = False
    hitTarget = False
    
    diamond = Diamond(-5000, -5000) # just to get rid of some syntax errors
    #modules.gradient.makeGrad(800, 100, MENUBGCOLOURS[0], MENUBGCOLOURS[1]) # uncomment to make bg
    MENUBG = pg.image.load("assets/menu/menuBG.jpg")
    while 1:
        if curMenu() == "play":
            if pg.mouse.get_visible() == True:
                pg.mouse.set_visible(False)
            for event in pg.event.get():
                if event.type == pg.QUIT:
                    pg.quit()
                    quit()
                if event.type == pg.KEYDOWN: 
                    if event.key == pg.K_ESCAPE: 
                        if score > highscore:
                            highscore = score
                            fp = open(USERNAME+"-highscore.txt", "w")
                            fp.write(str(highscore))
                            fp.close()
                            highscoreText = font.render("Highscore: " + str(highscore), True, (0, 0, 0))
                        score = 0
                        health = 3
                        diamondAvailable = False
                        pg.mouse.set_visible(True)
                        for i in range(0, len(hearts)):
                            hearts[i] = heart.subsurface((0, 0, 14, 14))
                            hearts[i] = pg.transform.scale(hearts[i], (7*5, 7*5))
                        for target in targets:
                            target.x, target.y = random.randint(0, 800-64), random.randint(0, 600-64)
                        diamond = Diamond(-5000, -5000)
                        bullets = []
                        setMenu("MainMenu")
                if event.type == pg.MOUSEBUTTONDOWN:
                    for target in targets:
                        if checkCollision(target.x, target.y, 64, 64, event.pos[0], event.pos[1], 1, 1):
                            hitTarget = True
                            score += int(1/len(targets))
                            target.x, target.y = random.randint(0, 800-64), random.randint(0, 600-64)
                            pg.mixer.Sound.play(targetPling)
                            if random.randint(0, 15) == 2 and not diamondAvailable:
                                diamondAvailable = True
                                diamond = Diamond(random.randint(0, 800-64), random.randint(0, 600-64))
                        elif not checkCollision(target.x, target.y, 64, 64, event.pos[0], event.pos[1], 1, 1) and not diamondAvailable:
                            pg.mixer.Sound.play(hurtSound)
                            # set the position of the crosshair to the mouse position
                            bullets.append(Bullet(pg.mouse.get_pos()[0]-crosshair.get_width()/2, pg.mouse.get_pos()[1]-crosshair.get_height()/2))
                            bullets[len(bullets)-1].img = pg.transform.scale(bullets[len(bullets)-1].img, (17*2, 17*2))
                            bullets[len(bullets)-1].img = pg.transform.rotate(bullets[len(bullets)-1].img, random.randint(0, 360))
                            # change heart to gray heart
                            if health != 0:
                                hearts[health-1] = heart.subsurface((14, 0, 14, 14))
                                hearts[health-1] = pg.transform.scale(hearts[health-1], (7*5, 7*5))
                                health -= 1
                                
                    if diamondAvailable:
                        if checkCollision(diamond.x, diamond.y, 56, 56, event.pos[0], event.pos[1], 1, 1):
                            score += 10
                            diamondAvailable = False
                            pg.mixer.Sound.play(diamondPling)
                        elif not checkCollision(diamond.x, diamond.y, 56, 56, event.pos[0], event.pos[1], 1, 1) and not hitTarget:
                            pg.mixer.Sound.play(hurtSound)
                            # set the position of the crosshair to the mouse position
                            bullets.append(Bullet(pg.mouse.get_pos()[0]-crosshair.get_width()/2, pg.mouse.get_pos()[1]-crosshair.get_height()/2))
                            bullets[len(bullets)-1].img = pg.transform.scale(bullets[len(bullets)-1].img, (17*2, 17*2))
                            bullets[len(bullets)-1].img = pg.transform.rotate(bullets[len(bullets)-1].img, random.randint(0, 360))
                            # change heart to gray heart
                            if health != 0:
                                hearts[health-1] = heart.subsurface((14, 0, 14, 14))
                                hearts[health-1] = pg.transform.scale(hearts[health-1], (7*5, 7*5))
                                health -= 1
                    hitTarget = False
            screen.fill(BGCOLOUR)
            for bullet in bullets:
                bullet.draw(screen)
            for target in targets:
                target.draw(screen)
            for i in range(0, len(hearts)):
                screen.blit(hearts[i], (5+i*7*5+5*i, 560))
            if diamondAvailable:
                diamond.draw(screen)
            scoreText = font.render("Score: " + str(score), True, (0, 0, 0))
            screen.blit(scoreText, (5, 5))
            screen.blit(crosshair, (pg.mouse.get_pos()[0]-crosshair.get_width()/2, pg.mouse.get_pos()[1]-crosshair.get_height()/2))
            # set new highscore
            if health == 0:
                if score > highscore:
                    highscore = score
                    fp = open(USERNAME+"-highscore.txt", "w")
                    fp.write(str(highscore))
                    fp.close()
                    highscoreText = font.render("Highscore: " + str(highscore), True, (0, 0, 0))
                score = 0
                health = 3
                diamondAvailable = False
                pg.mouse.set_visible(True)
                for i in range(0, len(hearts)):
                    hearts[i] = heart.subsurface((0, 0, 14, 14))
                    hearts[i] = pg.transform.scale(hearts[i], (7*5, 7*5))
                for target in targets:
                    target.x, target.y = random.randint(0, 800-64), random.randint(0, 600-64)
                diamond = Diamond(-5000, -5000)
                bullets = []
                setMenu("MainMenu")
                
        elif curMenu() == "MainMenu":
            if pg.mouse.get_visible() == False:
                pg.mouse.set_visible(True)
            for event in pg.event.get():
                if event.type == pg.QUIT:
                    pg.quit()
                    quit()
                if event.type == pg.MOUSEBUTTONDOWN:
                    if playButton.checkClick(pg.mouse.get_pos()[0], pg.mouse.get_pos()[1]):
                        setMenu("play")
                    if settingsButton.checkClick(pg.mouse.get_pos()[0], pg.mouse.get_pos()[1]):
                        setMenu("SettingsMenu")
                    if creditsButton.checkClick(pg.mouse.get_pos()[0], pg.mouse.get_pos()[1]):
                        setMenu("CreditsMenu")

            screen.blit(MENUBG, (0,0))
            playButton.draw(screen)
            settingsButton.draw(screen)
            creditsButton.draw(screen)
            screen.blit(highscoreText, (5, 5))
        elif curMenu() == "SettingsMenu": # WIP
            for event in pg.event.get():
                if event.type == pg.QUIT:
                    pg.quit()
                    quit()
                if event.type == pg.KEYDOWN:
                    if event.key == pg.K_ESCAPE:
                        setMenu("MainMenu")
            screen.blit(MENUBG, (0,0))
            screen.blit(font.render("TODO", True, (0, 0, 0)), (5, 5))
        elif curMenu() == "CreditsMenu": # WIP
            for event in pg.event.get():
                if event.type == pg.QUIT:
                    pg.quit()
                    quit()
                if event.type == pg.KEYDOWN:
                    if event.key == pg.K_ESCAPE:
                        setMenu("MainMenu")
            screen.blit(MENUBG, (0,0))
            for i in range(len(creditsText)):
                screen.blit(creditsText[i], (5, 5+i*30))
    
        pg.display.flip()
        pg.display.update()
        clock.tick(60)
    

if __name__ == "__main__":
    pg.init()
    pg.mixer.init()
    screen = pg.display.set_mode((800, 600))
    pg.display.set_caption("WIP NAME")
    clock = pg.time.Clock()

    hurtSound = pg.mixer.Sound("assets/audio/hurt.wav")
    targetPling = pg.mixer.Sound("Assets/audio/targetpling.wav")
    diamondPling = pg.mixer.Sound("Assets/audio/diamondpling.wav")
    pg.mixer.Sound.set_volume(targetPling, 0.12)
    pg.mixer.Sound.set_volume(hurtSound, 0.8)
    pg.mixer.Sound.set_volume(diamondPling, 0.5)

    # make 3 hearts with 2 animations, each heart is 7x7
    heart = pg.image.load("assets/sprites/hearts.png")
    hearts = [
        heart.subsurface((0, 0, 14, 14)),
        heart.subsurface((0, 0, 14, 14)),
        heart.subsurface((0, 0, 14, 14))
    ]
    for i in range(0, len(hearts)):
        hearts[i] = pg.transform.scale(hearts[i], (7*5, 7*5))

    font = pg.font.Font("assets/fonts/font.ttf", 36)
    fontCred = pg.font.Font("assets/fonts/font.ttf", 30)
    creditsText = [
        fontCred.render("Made by: - Guglio + Classmates", True, (0, 0, 0)),
        # TODO: Add more credits
    ]
    bullets = []
    targets = []
    targets.append(Target(pg.image.load("assets/sprites/target.png"), random.randint(0, 800-32), random.randint(0, 600-32)))
    RED = (153,0,0)
    MENUBGCOLOURS = [
        (0,255,251),
        (0,0,255)
    ]
    dark_red = (139, 0, 0)
    BGCOLOUR = (128, 128, 128)
    BLUE = (0,0,0)
    RED2 = (0,15,0)
    crosshair = pg.image.load("assets/sprites/crosshair.png")
    crosshair = pg.transform.scale(crosshair, (17*2, 17*2))
    hitTarget = False

    fuchsia = (255, 0, 128)  # The colour we will use for transparency 
    hwnd = pg.display.get_wm_info()["window"] # get our windows info
    win32gui_windowLong = win32gui.GetWindowLong(hwnd, win32con.GWL_EXSTYLE) | win32con.WS_EX_LAYERED # get our windowLong to then modify it to make us colour key fushia out
    win32gui.SetWindowLong(hwnd, win32con.GWL_EXSTYLE, win32gui_windowLong)
    # if the colour is fushia, become slightly transparent
    win32gui.SetLayeredWindowAttributes(hwnd, win32api.RGB(*fuchsia), 50, win32con.LWA_COLORKEY)# if the colour is fushia, the window is transparent
    #win32gui.SetLayeredWindowAttributes(hwnd, win32api.RGB(*fuchsia), 200, win32con.LWA_COLORKEY) 

    target = pg.image.load("assets/sprites/target.png")
    playButton = Button(300, 250, 200, 100, "Play", (200, 10, 10))
    settingsButton = Button(300, 360, 200, 100, "Settings", (10, 200, 10))
    creditsButton = Button(300, 470, 200, 100, "Credits", (10, 10, 200))
    
    mainGame()