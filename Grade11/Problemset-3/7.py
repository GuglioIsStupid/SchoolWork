# Guglio
# This program calculates the tax on a purchase and the rate of tax
original_cost = 62.99
final_cost = 71.81
amount_of_tax = final_cost - original_cost
rate_of_tax = amount_of_tax / original_cost * 100
print(f"""
Original cost: ${original_cost}
Final cost: ${final_cost}
Amount of tax: ${amount_of_tax}
Rate of tax: {rate_of_tax:,.0f}%
""")