"""
Guglio
Quiz - 1 - problem-3.py
"""
import datetime

date = datetime.datetime.now()

birthday = input("Please enter your birthday in the format MM/DD/YYYY: ")
try: # try to convert the input to a datetime object, else print an error message and exit
    birthday = datetime.datetime.strptime(birthday, "%m/%d/%Y")
except:
    print("Please enter a valid date in the format MM/DD/YYYY")
    exit()
if date.month == birthday.month and date.day == birthday.day: # check if birthday is today
    print("Happy Birthday!")
else:
    print("Today is not your birthday.")
