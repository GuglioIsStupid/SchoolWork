# My math module for advanced math functions

def lerp(a, b, t):
    return a + (b - a) * t

def clamp(x, a, b):
    return max(a, min(x, b))

def distance(a, b):
    return abs(a - b)

def round(x):
    return int(x + 0.5)

def unlerp(a, b, x):
    return (x - a) / (b - a)

def unlerp_clamp(a, b, x):
    return clamp(unlerp(a, b, x), 0, 1)

def bezier(t, p0, p1, p2, p3):
    t2 = t * t
    t3 = t2 * t
    return (p0 * (1 - t) ** 3) + (p1 * 3 * t * (1 - t) ** 2) + (p2 * 3 * t2 * (1 - t)) + (p3 * t3)
