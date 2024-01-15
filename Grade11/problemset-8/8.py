#Guglio
# October 27th 2022 
nums = []
squares = []
cubes = []
for i in range(1, 11):
    nums.append(i)
    squares.append(i**2)
    cubes.append(i**3)
print("Number, Square, Cube")
for i in range(0, 10):
    print(f"""
{nums[i]}, {squares[i]}, {cubes[i]}
    """)