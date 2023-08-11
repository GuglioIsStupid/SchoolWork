# Guglio
# Tic-Tac-Toe program using Python and ascii art
# 2022/10/17
xPos = []
oPos = []
board = [1,2,3,4,5,6,7,8,9]
def printBoard():
    print(f"""
    {board[0]} | {board[1]} | {board[2]}
    ---------
    {board[3]} | {board[4]} | {board[5]}
    ---------
    {board[6]} | {board[7]} | {board[8]}
    """)
def checkWin():
    if 1 in xPos and 2 in xPos and 3 in xPos:
        print("X Wins!")
        return True
    elif 4 in xPos and 5 in xPos and 6 in xPos:
        print("X Wins!")
        return True
    elif 7 in xPos and 8 in xPos and 9 in xPos:
        print("X Wins!")
        return True
    elif 1 in xPos and 4 in xPos and 7 in xPos:
        print("X Wins!")
        return True
    elif 2 in xPos and 5 in xPos and 8 in xPos:
        print("X Wins!")
        return True
    elif 3 in xPos and 6 in xPos and 9 in xPos:
        print("X Wins!")
        return True
    elif 1 in xPos and 5 in xPos and 9 in xPos:
        print("X Wins!")
        return True
    elif 3 in xPos and 5 in xPos and 7 in xPos:
        print("X Wins!")
        return True
    elif 1 in oPos and 2 in oPos and 3 in oPos:
        print("O Wins!")
        return True
    elif 4 in oPos and 5 in oPos and 6 in oPos:
        print("O Wins!")
        return True
    elif 7 in oPos and 8 in oPos and 9 in oPos:
        print("O Wins!")
        return True
    elif 1 in oPos and 4 in oPos and 7 in oPos:
        print("O Wins!")
        return True
    elif 2 in oPos and 5 in oPos and 8 in oPos:
        print("O Wins!")
        return True
    elif 3 in oPos and 6 in oPos and 9 in oPos:
        print("O Wins!")
        return True
    elif 1 in oPos and 5 in oPos and 9 in oPos:
        print("O Wins!")
        return True
    elif 3 in oPos and 5 in oPos and 7 in oPos:
        print("O Wins!")
        return True
    else:
        return False
def checkTie():
    if 1 in board or 2 in board or 3 in board or 4 in board or 5 in board or 6 in board or 7 in board or 8 in board or 9 in board:
        return False
    else:
        print("Tie!")
        return True

printBoard()
while True:
    x = int(input("X's Turn: "))
    xPos.append(x)
    board[x-1] = "X"
    printBoard()
    if checkWin() == True:
        break
    elif checkTie() == True:
        break
    o = int(input("O's Turn: "))
    oPos.append(o)
    board[o-1] = "O"
    printBoard()
    if checkWin() == True:
        break
    elif checkTie() == True:
        break
    