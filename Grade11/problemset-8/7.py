#Guglio
# October 27th 2022 
# numList = []

for i  in range(0, 5):
    for j in range(5, 0, -1):
        curNum = j
        number = input("Enter a number ({} to go): ".format(curNum))
        numList.append(int(number))
        print(curNum)
        if curNum == 1:
            break
    break

#check how many negative numbers
negNum = 0
for i in range(0, len(numList)):
    if numList[i] < 0:
        negNum = negNum + 1

print("There are {} negative numbers".format(negNum))