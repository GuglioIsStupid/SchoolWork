"""
Guglio
This is a quiz game
"""
from time import sleep

answer = ""
curQuestion = ""
curAnswer = ""
questionNum = 0
correct = 0
questions = [ # make a list of question qith the answers
    ["What is 2^9?", "512"],
    ["Who created Python?", "Guido van Rossum"],
    ["What is the capital of France?", "Paris"],
    ["What is the capital of England?", "London"],
    ["What is the capital of Germany?", "Berlin"],
    ["How many sides does a triangle have?", "3"],
    ["How many sides does a square have?", "4"],
]
answers = [ 
    [
        "512", "1024", 
        "2048", "4096"
    ],
    [
        "James Gosling", "Bill Gates",
        "Steve Jobs", "Guido van Rossum"
    ],
    [
        "Paris", "London",
        "Berlin", "Rome"
    ],
    [
        "Paris", "London",
        "Berlin", "Rome"
    ],
    [
        "Paris", "London",
        "Berlin", "Rome"
    ],
    [
        "3", "4",
        "5", "6"
    ],
    [
        "3", "4",
        "5", "6"
    ]
]

def askQuestion():
    global curQuestion, curAnswer, questionNum, questions, answers, correct
    curQuestion = questions[questionNum][0]
    curAnswer = questions[questionNum][1]
    print(curQuestion)
    for i in range(4):
        print(answers[questionNum][i])

def checkAnswer():
    global answer, curAnswer, questionNum, correct
    if answer.lower() == curAnswer.lower() :
        print("Correct!")
        questionNum += 1
        correct += 1
        sleep(0.5)
    else:
        print("Correct answer is: " + curAnswer, "Your answer is: " + answer)
        print("Wrong!")
        questionNum += 1
        sleep(0.5)

def main():
    global answer, questionNum
    while questionNum < len(questions):
        askQuestion()
        answer = str(input("Your answer: "))
        checkAnswer()
    else:
        average = correct/len(questions)*100
        print("You got", correct, "out of", len(questions), "correct!")
        print(f"You got {average:,.2f}% average!")

main()
