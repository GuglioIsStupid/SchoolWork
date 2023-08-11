# Guglio
# 2022-09-21
# This program is for printing a multi-line string
import random                # there are 2 ways to print this type of thing

if random.randint(0,1) == 1: # 1st way (triple quotes)
    print('''                
A slash is "/"
while
a backslash is "\\"
    ''')
else:                        # 2nd way (w/ \n's)
    print('A slash is "/"\nwhile\na backslash is "\\"')
