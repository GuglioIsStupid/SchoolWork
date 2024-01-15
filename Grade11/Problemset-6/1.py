"""
Guglio
This program calulates the price of all the floppy disks are wanted
"""
unit_cost = 0.30
amount = int(input("How many floppy disks would you like? "))
total_cost = unit_cost * amount
print(f"""
Mi,ber requested: {amount:,.2f}
Unit cost: ${unit_cost:,.2f}
Total cost: ${total_cost:,.2f}
""")