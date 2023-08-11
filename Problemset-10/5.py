# Guglio
# November 28th 2022
# Write a program that will help the user to analyze their bowling scores. 
# The user can enter as many scores as they like by clicking a button, entering -1 when they are done entering scores. 
# Your program will then display the highest, lowest, and average score entered. 

scores = []
score = 0
count = 0

while score != -1:
    score = int(input("Enter a score: "))
    if score != -1:
        scores.append(score) # add the score to the list
        count += 1

print("You entered", count, "scores.") # print the amount of scores entered
print("The best score entered is", max(scores)) # print the highest score 
print("The worst score entered is", min(scores)) # print the lowest score
print("The average score entered is", sum(scores) / count) # print the average score