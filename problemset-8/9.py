#Guglio
# October 27th 2022 
# generate 1000 random numbers tetween 1 and 5. Display a chart showing the amount of times each number was generated
import random
numList = []
for i in range(0, 1000):
    numList.append(random.randint(1, 5))

for i in range(1, 6):
    print("Number {} was generated {} times".format(i, numList.count(i)))