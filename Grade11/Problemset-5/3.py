# Guglio
# This program allows users to login
# 2022/10/17
USERNAME = 'admin'
PASSWORD = 'admin'

Username = input('Enter username: ')
Password = input('Enter password: ')

if Username == USERNAME and Password == PASSWORD:
    print('ACCESS GRANTED')
else:
    print('ACCESS DENIED')