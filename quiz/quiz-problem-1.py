"""
Guglio
Quiz - 1 - problem-1.py
"""
allRoyals = [ # list of all the royals
    "queen", 
    "knight",
    "pawn"
]
allRoyalsPrices = [ # list of all the prices
    3.99,
    4.99,
    5.99
]

whatAmI = input("What are you? (Queen/Knight/Pawn): ").lower() # get the input and convert it to lowercase

if whatAmI in allRoyals:
    print(f"You are a {whatAmI} you pay ${allRoyalsPrices[allRoyals.index(whatAmI)]}") # print the price of the royal
else:
    print("You are not a royal. You pay $10.99") # print the price of a non-royal
