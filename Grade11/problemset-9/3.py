#Guglio
# October 27th 2022 
#Ask the user for several names.  Count how many names were entered.  The name “Done” can be used to indicate the user wishes to stop entering.  Do not count “Done” as one of the names. Display a list of all the names after the user is finished.

nameList = []

name = ""

while name != "done":
    name = input("Enter a name: ").lower()
    if name != "done":
        nameList.append(name)

for name in nameList:
    print(name.title())