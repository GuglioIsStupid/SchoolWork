# 12/09/2022 - ??/??/????

# load our libraries
from inspect import FullArgSpec
import pygame as pg
from modules.bullet import Bullet
from modules.target import Target
from modules.diamond import Diamond
from modules.button import Button
from modules.slider import Slider
from modules.tl import TextLabel
from modules.booleanSelect import BooleanSelect
import modules.math as math
import math
import random, os, sys
import configparser

DEBUG = True # debug mode
# try to import pywin32
try:
    import win32api
    import win32con
    import win32gui
except ImportError:
    quit()

# define our gradient functions
def gradientRectHorz(left_colour, right_colour, target_rect):
    """ Draw a horizontal-gradient filled rectangle covering <target_rect> """
    colour_rect = pg.Surface( ( 2, 2 ) )                                   # tiny! 2x2 bitmap
    pg.draw.line( colour_rect, left_colour,  ( 0,0 ), ( 0,1 ) )            # left colour line
    pg.draw.line( colour_rect, right_colour, ( 1,0 ), ( 1,1 ) )            # right colour line
    colour_rect = pg.transform.smoothscale( colour_rect, ( target_rect.width, target_rect.height ) )  # stretch!
    screen.blit( colour_rect, target_rect )

def goofyGradient(left_colour, right_colour, target_rect):
    """ make a funny-looking gradient filled rectangle covering <target_rect> """
    colour_rect = pg.Surface( ( 2, 2 ) )                                   # tiny! 2x2 bitmap
    pg.draw.line( colour_rect, left_colour,  ( 0,0 ), ( 1,1 ) )            # bottom colour line
    pg.draw.line( colour_rect, right_colour, ( 0,1 ), ( 1,0 ) )            # top colour line
    colour_rect = pg.transform.smoothscale( colour_rect, ( target_rect.width, target_rect.height ) )  # stretch!
    screen.blit( colour_rect, target_rect )

# make a gradient class
class gradientHorz:
    # (255,0,0), (0,0,255)600
    def __init__(self, x=0, y=0, width=800, height=600, colour1=(0,0,0), colour2=(255,255,255)):
        self.rect = pg.Rect(x, y, width, height)
        self.colour1 = colour1
        self.colour2 = colour2
    def changeColour(self, colour1=(0,0,0), colour2=(255,255,255)):
        self.colour1 = colour1
        self.colour2 = colour2
    def draw(self):
        goofyGradient(self.colour1, self.colour2, self.rect)

gradBG = gradientHorz(0, 0, 800, 600, (255,0,0), (0,0,255))
menu = "MainMenu" # the current game state
def curMenu(): # return the current menu
    return menu

def setMenu(newMenu): # set the current menu
    global menu
    menu = newMenu
    #print(menu)

def checkCollision(a_x, a_y, a_width, a_height, b_x, b_y, b_width, b_height): # check the collision of a current object with another object, returns a boolean
    return (
        (a_x + a_width > b_x) and
        (a_x < b_x + b_width) and 
        (a_y + a_height > b_y) and 
        (a_y < b_y + b_height)
    )

def mainGame():
    #create a folder in appdata for the game
    if not os.path.exists(os.getenv("APPDATA") + "/PyShooter"):
        os.mkdir(os.getenv("APPDATA") + "/PyShooter")
    #create a config file in the folder
    if not os.path.exists(os.getenv("APPDATA") + "/PyShooter/config.ini"):
        config = configparser.ConfigParser()
        config["DEFAULT"] = {
            "fullscreen": "false",
            "music": "true",
            "sound": "true",
            "TransparentWindow": "true",
            "; Allows the window to be transparent.": "",
        }
        config["CONFIG"] = {
            "version": "0.1"
        }
        with open(os.getenv("APPDATA") + "/PyShooter/config.ini", "w") as configfile:
            config.write(configfile)
    #read the config file
    config = configparser.ConfigParser()
    config.read(os.getenv("APPDATA") + "/PyShooter/config.ini")
    #set the variables
    _GAMEVERSION = config.getfloat("CONFIG", "version")
    if _GAMEVERSION != 0.2:
        config = configparser.ConfigParser()
        config["DEFAULT"] = {
            "fullscreen": "false",
            "music": "true",
            "sound": "true",
            "musicVol": "1",
            "soundVol": "0.5",
            "TransparentWindow": "true",
            "; Allows the window to be transparent.": "",
        }
        config["CONFIG"] = {
            "version": "0.2"
        }
        with open(os.getenv("APPDATA") + "/PyShooter/config.ini", "w") as configfile:
            config.write(configfile)
    isFullscreen = config.getboolean("DEFAULT", "fullscreen")
    useMusic = config.getboolean("DEFAULT", "music")
    useSound = config.getboolean("DEFAULT", "sound")
    musicVol = config.getfloat("DEFAULT", "musicVol")
    soundVol = config.getfloat("DEFAULT", "soundVol")

    if isFullscreen:
        # set the screen to fullscreen
        screen = pg.display.set_mode((800, 600), pg.FULLSCREEN)
    else:
        screen = pg.display.set_mode((800, 600))
    settingWindowTransparency = config.getboolean("DEFAULT", "TransparentWindow")
    fuchsia = (255, 0, 128)  # The colour we will use for transparency 
    hwnd = pg.display.get_wm_info()["window"] # get our windows info
    win32gui_windowLong = win32gui.GetWindowLong(hwnd, win32con.GWL_EXSTYLE) | win32con.WS_EX_LAYERED # get our windowLong to then modify it to make us colour key fushia out
    win32gui.SetWindowLong(hwnd, win32con.GWL_EXSTYLE, win32gui_windowLong)
    # if the colour is fushia, become slightly transparent
    if settingWindowTransparency:
        win32gui.SetLayeredWindowAttributes(hwnd, win32api.RGB(*fuchsia), 200, win32con.LWA_COLORKEY)# if the colour is fushia, the window is transparent
    #win32gui.SetLayeredWindowAttributes(hwnd, win32api.RGB(*fuchsia), 200, win32con.LWA_COLORKEY) 
    try:
        with open(os.getenv("APPDATA") + "/PyShooter/save", mode="r") as f:
            highscore = f.read()
    except FileNotFoundError:
        highscore = 0
        with open(os.getenv("APPDATA") + "/PyShooter/save", "w") as savefile:
            savefile.write(str(highscore))

    AudioLabel = TextLabel("Audio Settings", 135, 45)
    GameLabel = TextLabel("Game Setings", 385, 45)
    musicSlider = Slider("Music Volume", 120, 80, 200, 20, float(musicVol))
    soundSlider = Slider("Sound Volume", 120, 125, 200, 20, float(soundVol))
    fullscreenBoolean = BooleanSelect(isFullscreen, 380, 80, "Fullscreen")
    windowTransparencyBoolean = BooleanSelect(settingWindowTransparency, 380, 145, "Window Transparency")
    highscoreText = font.render("Highscore: " + str(highscore), True, (0, 0, 0))
    score = 0
    health = 3
    bullets = []
    diamondAvailable = False
    hitTarget = False
    
    diamond = Diamond(-5000, -5000) # just to get rid of some syntax errors
    
    while True:
        if DEBUG:
            DEBUGSTR = f"""
            FPS: {int(clock.get_fps())}
            """ # the debug string
            DEBUGSTRend = font.render(DEBUGSTR, True, (0, 0, 0))
        if curMenu() == "play":
            if pg.mouse.get_visible() == True:
                pg.mouse.set_visible(False)
            for event in pg.event.get():
                if event.type == pg.QUIT:
                    pg.quit()
                    quit()
                if event.type == pg.KEYDOWN: 
                    if event.key == pg.K_ESCAPE: 
                        if score > int(highscore):
                            highscore = score
                            with open(os.getenv("APPDATA") + "/PyShooter/save", "w") as savefile:
                                savefile.write(str(highscore))
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
                        gradBG.changeColour((255,0,0), (0,0,255))
                        setMenu("MainMenu")
                if event.type == pg.MOUSEBUTTONDOWN:
                    if event.button == 1:
                        for target in targets:
                            if checkCollision(target.x, target.y, 64, 64, event.pos[0], event.pos[1], 1, 1):
                                hitTarget = True
                                distanceToBullseyeX = math.sqrt((event.pos[0] - target.x)**2 + (event.pos[1] - target.y)**2)
                                print(distanceToBullseyeX)
                                # left side of the target
                                if distanceToBullseyeX >= 43 and distanceToBullseyeX <= 48:
                                    score += int(5/len(targets)) 
                                elif distanceToBullseyeX >= 38 and distanceToBullseyeX <= 43:
                                    score += int(4/len(targets)) 
                                elif distanceToBullseyeX >= 33 and distanceToBullseyeX <= 38:
                                    score += int(3/len(targets)) 
                                elif distanceToBullseyeX >= 28 and distanceToBullseyeX <= 33:
                                    score += int(2/len(targets)) 
                                elif distanceToBullseyeX >= 23 and distanceToBullseyeX <= 28:
                                    score += int(1/len(targets)) 
                                # right side of the target
                                elif distanceToBullseyeX >= 49 and distanceToBullseyeX <= 54:
                                    score += int(5/len(targets)) 
                                elif distanceToBullseyeX >= 54 and distanceToBullseyeX <= 59:
                                    score += int(4/len(targets))
                                elif distanceToBullseyeX >= 59 and distanceToBullseyeX <= 64:
                                    score += int(3/len(targets))
                                elif distanceToBullseyeX >= 64 and distanceToBullseyeX <= 69:
                                    score += int(2/len(targets))
                                elif distanceToBullseyeX >= 69 and distanceToBullseyeX <= 74:
                                    score += int(1/len(targets))

                                pg.mixer.Sound.play(targetPling) 

                                if random.randint(0, 15) == 2 and not diamondAvailable:
                                    diamondAvailable = True
                                    diamond = Diamond(random.randint(0, 800-64), random.randint(0, 600-64))

                                target.x, target.y = random.randint(0, 800-64), random.randint(0, 600-64)
                                       
                            elif not checkCollision(target.x, target.y, 64, 64, event.pos[0], event.pos[1], 1, 1) and not diamondAvailable:
                                pg.mixer.Sound.play(hurtSound)
                                
                                # set the position of the crosshair to the mouse position
                                bullets.append(Bullet(pg.mouse.get_pos()[0]-crosshair.get_width()/2, pg.mouse.get_pos()[1]-crosshair.get_height()/2))
                                bullets[len(bullets)-1].img = pg.transform.scale(bullets[len(bullets)-1].img, (17*2, 17*2))
                                bullets[len(bullets)-1].img = pg.transform.rotate(bullets[len(bullets)-1].img, random.randint(0, 360))
                                # if window transparency is disabled, make the bullet holes black
                                if not settingWindowTransparency:
                                    bullets[len(bullets)-1].img.fill((0, 0, 0), special_flags=pg.BLEND_RGBA_MULT)

                                
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
            #screen.fill(BGCOLOUR)
            gradBG.draw()
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
                if score > int(highscore):
                        highscore = score
                        with open(os.getenv("APPDATA") + "/PyShooter/save", "w") as savefile:
                            savefile.write(str(highscore))
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

                gradBG.changeColour((255,0,0), (0,0,255))
                    
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
                    if event.button == 1:
                        if playButton.checkClick(pg.mouse.get_pos()[0], pg.mouse.get_pos()[1]):
                            setMenu("play")
                            gradBG.changeColour((0.4*255, 0.4*255, 0.4*255), (0, 0, 0))
                        if settingsButton.checkClick(pg.mouse.get_pos()[0], pg.mouse.get_pos()[1]):
                            setMenu("SettingsMenu")
                            gradBG.changeColour((180,0,0), (0,0,180))
                        if creditsButton.checkClick(pg.mouse.get_pos()[0], pg.mouse.get_pos()[1]):
                            setMenu("CreditsMenu")
                            gradBG.changeColour((180,0,0), (0,0,180))

            gradBG.draw()
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
                        gradBG.changeColour((255,0,0), (0,0,255))
                        musicVol = musicSlider.get_val()
                        soundVol = soundSlider.get_val()

                        config = configparser.ConfigParser()
                        config.read(os.getenv("APPDATA") + "/PyShooter/config.ini")
                        config.set("DEFAULT", "musicVol", str(musicVol))
                        config.set("DEFAULT", "soundVol", str(soundVol))

                        pg.mixer.Sound.set_volume(targetPling, soundVol)
                        pg.mixer.Sound.set_volume(hurtSound, soundVol)
                        pg.mixer.Sound.set_volume(diamondPling, soundVol)
                        pg.mixer.music.set_volume(musicVol)


                        config.set("DEFAULT", "fullscreen", str(fullscreenBoolean.getBoolean()))
                        config.set("DEFAULT", "TransparentWindow", str(windowTransparencyBoolean.getBoolean()))
                        settingWindowTransparency = windowTransparencyBoolean.getBoolean()
                        if fullscreenBoolean.getBoolean():
                            screen = pg.display.set_mode((800, 600), pg.FULLSCREEN)
                        else:
                            screen = pg.display.set_mode((800, 600))
                        if settingWindowTransparency:
                            win32gui.SetLayeredWindowAttributes(hwnd, win32api.RGB(*fuchsia), 200, win32con.LWA_COLORKEY)# if the colour is fushia, the window is transparent
                        else:
                            win32gui.SetLayeredWindowAttributes(hwnd, win32api.RGB(*fuchsia), 255, win32con.LWA_ALPHA)# if the colour is fushia, the window is transparent

                        with open(os.getenv("APPDATA") + "/PyShooter/config.ini", "w") as configfile:
                            config.write(configfile)
                
                if pg.mouse.get_pressed()[0] == 1: 
                    musicSlider.checkClick(pg.mouse.get_pos()[0]+100, pg.mouse.get_pos()[1])
                    soundSlider.checkClick(pg.mouse.get_pos()[0]+100, pg.mouse.get_pos()[1])
                    windowTransparencyBoolean.checkClick(pg.mouse.get_pos()[0], pg.mouse.get_pos()[1])
                    fullscreenBoolean.checkClick(pg.mouse.get_pos()[0], pg.mouse.get_pos()[1])
                
                fullscreenBoolean.checkHover(pg.mouse.get_pos()[0], pg.mouse.get_pos()[1])
                windowTransparencyBoolean.checkHover(pg.mouse.get_pos()[0], pg.mouse.get_pos()[1])
                
            
            gradBG.draw()
            # draw the Text Labels
            AudioLabel.draw(screen)
            GameLabel.draw(screen)
            # draw our sliders
            musicSlider.draw(screen)
            soundSlider.draw(screen)
            # draw our boolean selects
            fullscreenBoolean.draw(screen)
            windowTransparencyBoolean.draw(screen)
            
        elif curMenu() == "CreditsMenu": # WIP
            for event in pg.event.get():
                if event.type == pg.QUIT:
                    pg.quit()
                    quit()
                    
                if event.type == pg.KEYDOWN:
                    if event.key == pg.K_ESCAPE:
                        gradBG.changeColour((255,0,0), (0,0,255))
                        setMenu("MainMenu")
                        
            #screen.blit(MENUBG, (0,0))
            gradBG.draw()
            
            for i in range(len(creditsText)):
                screen.blit(creditsText[i], (5, 5+i*30))

        if DEBUG:
            screen.blit(DEBUGSTRend, (5, 500))
    
        pg.display.flip()
        pg.display.update()
        clock.tick(60)
    

if __name__ == "__main__":
    pg.init()
    pg.mixer.init()
    pg.display.set_caption("Get Better")
    clock = pg.time.Clock()

    hurtSound = pg.mixer.Sound("assets/audio/hurt.wav")
    targetPling = pg.mixer.Sound("assets/audio/targetpling.wav")
    diamondPling = pg.mixer.Sound("assets/audio/diamondPling.wav")
    music = pg.mixer.music.load("assets/audio/Fluffing-a-Duck.mp3")

    pg.mixer.music.play(-1)

    pg.mixer.Sound.set_volume(targetPling, 0.12)
    pg.mixer.Sound.set_volume(hurtSound, 0.8)
    pg.mixer.Sound.set_volume(diamondPling, 0.5)
    pg.mixer.music.set_volume(0.5)

    # make 3 hearts each heart is 14x14
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
        fontCred.render("Made by:  Guglio + Classmates", True, (0, 0, 0)),
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

    target = pg.image.load("assets/sprites/target.png")
    playButton = Button(300, 250, 200, 100, "Play", (200, 10, 10), (100, 0, 0))
    settingsButton = Button(300, 360, 200, 100, "Settings", (10, 200, 10), (0, 100, 0))
    creditsButton = Button(300, 470, 200, 100, "Credits", (10, 10, 200), (0, 0, 100))
    screen = pg.display.set_mode((800, 600))
    mainGame()

