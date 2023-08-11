#Guglio
# October 27th 2022 
# Ask the user for the capital of Indonesia. Count how many incorrect guesses it takes until the correct answer is entered.
count = 0
answer = ""
while answer != "jakarta":
    answer = input("What is the capital of Indonesia? ").lower()
    count = count + 1

print("You guess wrong " + str(count) + " times.")