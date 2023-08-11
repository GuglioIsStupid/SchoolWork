# Guglio
# This program calculates how many packages are produced in a day
import math

total_widgets = 1022957
number_in_pack = 12
total_full_boxes = 1022957 / 12
amount_in_partial_box = total_widgets % 12 
print(f"""
Total Widgets           {total_widgets}
Number in pack          {number_in_pack}
Total full boxes        {math.floor(total_full_boxes)/1}
Amount in partial box   {amount_in_partial_box:,.1f}
""")
