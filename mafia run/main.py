"""
Camel-like game
2022/10/26
"""
from inspect import get_annotations
import random, os, sys, time, math, time
from asciiArt import *
global water
global food
global money
global caughtChance
global distanceFromMafia
global happyMealPrice
global gameProgress
global tiredness
global needSleep
global needFood
global needWater
global hunger
global thirst
global distance
water = 100.0
food = 100.0
money = 1000.0
caughtChance = 0.0
distanceFromMafia = 20.0
happyMealPrice = 3.99 * 1.13
gameProgress = 0
tiredness = 0.0
needSleep = False
needFood = False
needWater = False
hunger = 0
thirst = 0
random.seed(time.time())
def tutorial():
    print("""
                                        Welcome to the tutorial!
                        In this game, You just stole a bunch of money from the mafia.
                          you are now on the run from the mafia as long as you can.
                You will be given a list of options every round and you will have to choose one.
             The goal of the game is to run away from the mafia and survive for as long as possible.
    """)

def readTutorial():
    if input("Do you want to read the tutorial? (y/n + enter): ").lower() == "y":
        os.system("cls")
        tutorial()
        input("Press enter to continue.")
        os.system("cls")
        readTutorial()
    else:
        # continue to game
        os.system("cls")
        game()

def shop():
    global money
    global water
    global food
    inShop = True
    while inShop:
        print("""
1. Water - 2.00 + 0.13 tax (20ml)
2. Food - 3.00 + 0.13 tax (10)
3. Go back
        """)
        choiceMoment = int(input("What do you want to buy? (1-3): "))
        if choiceMoment == 3:
            game()
            inShop = False
        elif choiceMoment == 1:
            if money >= 2.13:
                water += 20
                money -= 2.13
            else:
                print("You don't have enough money")
        elif choiceMoment == 2:
            if money >= 3.13:
                food += 10
                money -= 3.13
            else:
                print("You don't have enough money")

def game():
    global water
    global food
    global money
    global caughtChance
    global distanceFromMafia
    global happyMealPrice
    global gameProgress
    global tiredness
    global needSleep
    global needFood
    global needWater
    global hunger
    global thirst
    if hunger >= 100:
        print("You died of hunger")
        time.sleep(3)
        main()
    elif thirst >= 100:
        print("You died of thirst")
        time.sleep(3)
        main()
    elif distanceFromMafia <= 0:
        print("The mafia caught you")
        time.sleep(3)
        main()
    elif tiredness >= 100:
        print("You died of exhaustion")
        time.sleep(3)
        main()
    resources = f"""
Food: {food}
Water: {water}ml
Money: {money}
Hunger Level: {hunger}
Thirst Level: {thirst}
Caught Chance: {caughtChance}%
Tiredness: {tiredness}
Distance from Mafia: {distanceFromMafia}km
"""
    if needWater:
        print("You need water")
    if needFood:
        print("You need food")
    if needSleep:
        print("You need sleep")
    if caughtChance >= 100:
        print("You got caught by the mafia and died")
        pass
    if gameProgress == 0:
        water = 100.0
        food = 100.0
        money = 100000.0
        caughtChance = 0.0
        distanceFromMafia = 20.0
        tiredness = 0.0
        happyMealPrice = 3.99 * 1.13
    # game code here
    if random.randint(1, 1000) == 500: # 
        print("You died. (got hit by a little tikes truck)")
        input("Press enter to continue.")
        os.system("cls")
        main()
    randomNum = random.randint(1, 3)
    if tiredness >= 80:
        print("You need to sleep... NOW")
    if randomNum == 1:
        if random.randint(1, 10) != 5:
            print(f"""
{resources}
{options}
6. Shop
""")
            selectedChoice(input("What do you want to do? (1-6): "), "Shop")
        else:
            print(f"""
{resources}
{options}
""")
            selectedChoice(input("What do you want to do? (1-5): "))
    elif randomNum == 2:
        if random.randint(1, 2) == 1:
            print(f"""
{resources}
{options}
6. Buy McDonalds Happy Meal - {happyMealPrice:,.2f}
""")
            selectedChoice(input("What do you want to do? (1-6): "), "happyMeal")
        else:
            print(f"""
{resources}
{options}
""")
            selectedChoice(input("What do you want to do? (1-5): "))
    elif randomNum == 3:
        print(f"""
{resources}
{options}
""")
        selectedChoice(input("What do you want to do? (1-5): "))

def main():
    global water
    global food
    global money
    global caughtChance
    global distanceFromMafia
    global happyMealPrice
    global gameProgress
    global tiredness
    global needSleep
    global needFood
    global needWater
    global hunger
    global thirst
    water = 100.0
    food = 100.0
    money = 1000.0
    caughtChance = 0.0
    distanceFromMafia = 20.0
    happyMealPrice = 3.99 * 1.13
    gameProgress = 0
    os.system("@echo off")
    # set console name to "Mafia Run"
    os.system("title Mafia Run")

    # set console size to 100x25
    os.system("mode con: cols=102 lines=25")
    input("""
                                        Welcome to Mafia Run!
                                     Press enter to start the game.
    \n""" + gunart)
    os.system("cls")
    readTutorial()

def selectedChoice(choice, randomSelection=None):
    global water
    global food
    global money
    global caughtChance
    global distanceFromMafia
    global happyMealPrice
    global gameProgress
    global tiredness
    global needSleep
    global needFood
    global needWater
    global hunger
    global thirst
    gameProgress = gameProgress + 1
    try:
        int(choice)
        if choice == 1:
            global water # python confuses me, i have to put it here or else it doesn't work in the other elifs statements
            global food
            global thirst
            global hunger
            global tiredness
            global caughtChance
            global money
            # sleep, honk shoo
            dotStr = ""
            for _ in range(3):
                time.sleep(0.65)
                dotStr += "."
                # remove the last line
                os.system("cls") # clear the command prompt to make it look like it's loading
                print("You slept for a bit")
                distanceFromMafia -= 8
                print(dotStr)
            tiredness -= 40
            if tiredness < 0:
                tiredness = 0
            game()
        elif choice == 2:
            # run
            hunger += random.randint(1, 5)
            thirst += random.randint(5, 10)
            distanceFromMafia += random.randint(5, 10)
            dotStr = ""
            tiredness += 20
            for _ in range(3):
                time.sleep(0.65)
                dotStr += "."
                # remove the last line
                os.system("cls") # clear the command prompt to make it look like it's loading
                print("You ran away some more")
                print(dotStr)
            game()
        elif choice == 3:
            # drink water
            if water >= 0:
                water -= random.uniform(1, 10)
                water = str(water)
                water = water[:water.find(".") + 3]
                water = float(water)
                distanceFromMafia -= 5
                thirst -= 8
                if thirst < 0:
                    thirst = 0
                os.system("cls")
                # time.sleep and print"." 3 times"
                dotStr = ""
                for _ in range(3):
                    time.sleep(0.65)
                    dotStr += "."
                    # remove the last line
                    os.system("cls") # clear the command prompt to make it look like it's loading
                    print("You drank some water.")
                    print(dotStr)
                print(f"\n{water}ml left")
                time.sleep(1)
                game()
            else:
                print("You don't have any water left")
                game()
        elif choice == 4:
            # eat food
            if food >= 0:
                food -= random.uniform(1, 10)
                food = str(food)
                food = food[:food.find(".") + 3]
                food = float(food)
                hunger -= 8
                distanceFromMafia -= 5
                if hunger < 0:
                    hunger = 0
                os.system("cls")
                # time.sleep and print"." 3 times"
                dotStr = ""
                for _ in range(3):
                    time.sleep(0.65)
                    dotStr += "."
                    # remove the last line
                    os.system("cls")
                    print("You ate some food.")
                    print(dotStr)
            else:
                print("You don't have any food left")
                game()
            game()
        elif choice == 5: 
            # Hide
            print(caughtChance)
            caughtChance += random.uniform(1, 5)
            caughtChance = str(caughtChance)
            caughtChance = caughtChance[:caughtChance.find(".") + 3]
            caughtChance = float(caughtChance)
            print(f"You hide for a bit. {caughtChance}% chance of getting caught")
            distanceFromMafia -= 5
            return caughtChance and game()
        elif choice == 6:
            if randomSelection == "happyMeal":
                os.system("mode con: cols=200 lines=200")
                print(mcdonaldsart)
                print(f"You bought a McDonalds Happy Meal for {happyMealPrice:,.2f}, but due to the long lineup, the mafia found you and killed you. It was very tasty though")
                pass
            else:
                print("You went to the shop")
                distanceFromMafia -= 5
                shop()
        else:
            print("Invalid choice")
            game()
    except:
        print("Please enter a number")
        game()

if __name__ == "__main__":
    resources = f"""
Food: {food}
Water: {water}
Money: {money}ml
Hunger Level: {hunger}
Thirst Level: {thirst}
Caught Chance: {caughtChance}%
Tiredness: {tiredness}
Distance from Mafia: {distanceFromMafia}km
"""
    options = """
1. Rest
2. Run
3. Drink
4. Eat
5. Hide
"""
    main()
