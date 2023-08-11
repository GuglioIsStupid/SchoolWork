# Guglio

# 2022-09-21
# This program is for ...

# get current date
import datetime
now = datetime.datetime.now()
year = now.year
month = now.month
day = now.day

months = [ # inserts month names
    ('January', 31),
    ('February', 28),
    ('March', 31),
    ('April', 30),
    ('May', 31),
    ('June', 30),
    ('July', 31),
    ('August', 31),
    ('September', 30),
    ('October', 31),
    ('November', 30),
    ('December', 31)
]

firstname = input("Enter your first name: ").capitalize().split() # allow console input
firstname = firstname[0] # since spliting makes it end up like ['name'], get rid of the ['']
lastname = input("Enter your last name: ").capitalize().split() # remove extra spaces and capitalize first letter
lastname = lastname[0]
age = input("Enter your age: ")

print(f"{firstname} {lastname} Rocks!") # put the variables into the string via {} to make it so we dont need to use the + operator
print(f"{firstname} {lastname} will be 100 years old in the year {year + (100 - int(age))}")
print(f"{firstname} {lastname} was born in the year {year - int(age)}")
print(f"today is {months[month - 1][0]} {day}, {year}")

# print the type of everything
print(f"""
firstname = {type(firstname)}
lastname = {type(lastname)}
age = {type(age)}
months = {type(months)}
""")
