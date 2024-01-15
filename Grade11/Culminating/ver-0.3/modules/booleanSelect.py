import pygame as pg

class BooleanSelect:
    def __init__(self, setting, x=0, y=0, text="OPTION"):
        self.x = x
        self.y = y
        self.false = "False"
        self.true = "True"
        self.setting = setting
        self.falsePosX = x-35
        self.falsePosY = y+20
        self.truePosX = x+35
        self.truePosY = y+20
        if self.setting:
            self.falseColour = (0.2*255, 0.2*255, 0.2*255)
            self.trueColour = (0.4*255, 0.4*255, 0.4*255)
            self.trueClicked = True
            self.falseClicked = False
        else:
            self.falseColour = (0.4*255, 0.4*255, 0.4*255)
            self.trueColour = (0.2*255, 0.2*255, 0.2*255)
            self.trueClicked = False
            self.falseClicked = True
        self.text = text
        self.font = pg.font.SysFont("comicsansms", 20)
        self.textOutline = self.font.render(self.text, True, (200, 200, 200))
        self.text = self.font.render(self.text, True, (0, 0, 0))
        self.textRect = self.text.get_rect()
        self.textRect.center = (self.x, self.y-20)
        

        self.falseOutline = self.font.render(self.false, True, (0, 0, 0))
        self.trueOutline = self.font.render(self.true, True, (0, 0, 0))
        self.false = self.font.render(self.false, True, self.falseColour)
        self.true = self.font.render(self.true, True, self.trueColour)

        self.falseRect = self.false.get_rect()
        self.trueRect = self.true.get_rect()

        self.falseRect.center = (self.falsePosX, self.falsePosY)
        self.trueRect.center = (self.truePosX, self.truePosY)
    
    def checkCollision(
        self, a_x, a_y, a_width, a_height, b_x, b_y, b_width, b_height):  
        # check the collision of a current object with another object, returns a boolean
        return (
            (a_x + a_width > b_x)
            and (a_x < b_x + b_width)
            and (a_y + a_height > b_y)
            and (a_y < b_y + b_height)
        )

    def getBoolean(self):
        return self.trueClicked
    
    def checkHover(self, x, y):
        if not self.falseClicked:
            if self.checkCollision(self.falsePosX-25, self.falsePosY-10, 50, 20, x, y, 1, 1):
                self.falseColour = (0.4*255, 0.4*255, 0.4*255)
                self.false = self.font.render("False", True, self.falseColour)
            else:
                self.falseColour = (0.2*255, 0.2*255, 0.2*255)
                self.false = self.font.render("False", True, self.falseColour)
        if not self.trueClicked:
            if self.checkCollision(self.truePosX-25, self.truePosY-10, 50, 20, x, y, 1, 1):
                self.trueColour = (0.4*255, 0.4*255, 0.4*255)
                self.true = self.font.render("True", True, self.trueColour)
            else:
                self.trueColour = (0.2*255, 0.2*255, 0.2*255)
                self.true = self.font.render("True", True, self.trueColour)

    def checkClick(self, x, y):
        if self.checkCollision(self.falsePosX-25, self.falsePosY-10, 50, 20, x, y, 1, 1):
            if not self.falseClicked: 
                self.falseClicked = True
                self.trueClicked = False
        if self.checkCollision(self.truePosX-25, self.truePosY-10, 50, 20, x, y, 1, 1):
            if not self.trueClicked:
                self.trueClicked = True
                self.falseClicked = False

    def draw(self, screen):
        # make the text have a white outline
        screen.blit(self.textOutline, (self.textRect.x-1, self.textRect.y))
        screen.blit(self.textOutline, (self.textRect.x+1, self.textRect.y))
        screen.blit(self.textOutline, (self.textRect.x, self.textRect.y-1))
        screen.blit(self.textOutline, (self.textRect.x, self.textRect.y+1))
        screen.blit(self.text, (self.textRect.x, self.textRect.y))
        # make the true and false have a black outline
        screen.blit(self.falseOutline, (self.falseRect.x+1, self.falseRect.y+1))
        screen.blit(self.falseOutline, (self.falseRect.x-1, self.falseRect.y-1))
        screen.blit(self.falseOutline, (self.falseRect.x-1, self.falseRect.y+1))
        screen.blit(self.falseOutline, (self.falseRect.x+1, self.falseRect.y-1))
        
        screen.blit(self.trueOutline, (self.trueRect.x+1, self.trueRect.y+1))
        screen.blit(self.trueOutline, (self.trueRect.x-1, self.trueRect.y-1))
        screen.blit(self.trueOutline, (self.trueRect.x-1, self.trueRect.y+1))
        screen.blit(self.trueOutline, (self.trueRect.x+1, self.trueRect.y-1))

        screen.blit(self.false, (self.falseRect.x, self.falseRect.y))
        screen.blit(self.true, (self.trueRect.x, self.trueRect.y))
