# Guglio
# This program calculates the amount of cash an employee earns
total_hours = 52
regular_hours = 40
OT_hours = 12
regular_rate = 9.58
OT_rate = 14.37
Total_regular_pay = regular_hours * regular_rate
Total_OT_pay = OT_hours * OT_rate
Total_pay = Total_regular_pay + Total_OT_pay

print(f"""
Total hours           {total_hours}
Regular hours         {regular_hours}
OT hours              {OT_hours}
Regular rate          {regular_rate}
OT rate               {OT_rate}
Total regular pay     {Total_regular_pay}
Total OT pay          {Total_OT_pay}
Total pay             {Total_pay}
""")