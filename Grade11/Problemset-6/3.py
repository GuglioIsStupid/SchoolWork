"""
Guglio
This program calulates the pay of a worker
"""
name = input("What is your name? ")
regular_hours = int(input("How many regular hours did you work? "))
regular_rate = int(input("What is your regular rate of pay? "))
# if the employee worked more than 40 hours, they get overtime
if regular_hours >= 40:
    overtime_hours = regular_hours - 40
    overtime_rate = regular_rate * 2
    overtime_pay = overtime_hours * overtime_rate
    regular_hours = 40
    regular_pay = regular_hours * regular_rate
    total_pay = regular_pay + overtime_pay
else:
    regular_pay = regular_hours * regular_rate
    overtime_hours = 0
    overtime_rate = regular_rate * 2
    overtime_pay = 0
    total_pay = regular_pay

print(f"""
FINANCIAL REPORT FOR {name.upper()}
_____________________________________
Regular hours: {regular_hours:,.2f}
Regular rate: ${regular_rate:,.2f}
Regular pay: ${regular_pay:,.2f}
_____________________________________
Overtime hours: {overtime_hours:,.2f}
Overtime rate: ${overtime_rate:,.2f}
Overtime pay: ${overtime_pay:,.2f}
_____________________________________
Gross pay: ${total_pay:,.2f}
""")
