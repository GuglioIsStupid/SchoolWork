# Guglio
# 26/09/2022
# This program will calculate the amount of credits left needed to graduate
credits = int(input("How many credits do you currently have?: "))
total_needed = 30
credits_to_go = total_needed - credits
print("If you have", credits ,"credits now and you need a total of", total_needed, "then you have" , credits_to_go, "left to go.")
