#Guglio
# October 27th 2022 
# Ask the user for a dollar amount of deposit and rate of interest.  Determine how many years it would take to become a millionaire.  Output a chart similar to the one below.

depositing = float(input("How much are you depositing?  "))
interest = float(input("What is the interest rate? "))
years = 0
print("Year\tInterest Earned\tBalance Value")
while depositing < 1000000:
    depositing = depositing + (depositing * (interest / 100))
    years = years + 1
    print(f"{years:,.2f}\t{depositing * (interest / 100):,.2f}\t\t {depositing:,.2f}")
print("In " + str(years) + " years you will be a millionaire!")
