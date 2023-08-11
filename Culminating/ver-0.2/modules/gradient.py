from ast import Import
from logging import error

try:
    import numpy as np
except ImportError:
    error("Numpy is not installed")
    quit()
try:
    from PIL import Image
except ImportError:
    error("PIL is not installed")
    quit()
a = np.array([0, 1, 2, 3])

def get_gradient_2d(start, stop, width, height, is_horizontal):
    if is_horizontal:
        return np.tile(np.linspace(start, stop, width), (height, 1))
    else:
        return np.tile(np.linspace(start, stop, height), (width, 1)).T
def get_gradient_3d(width, height, start_list, stop_list, is_horizontal_list):
    result = np.zeros((height, width, len(start_list)), dtype=np.float)

    for i, (start, stop, is_horizontal) in enumerate(zip(start_list, stop_list, is_horizontal_list)):
        result[:, :, i] = get_gradient_2d(start, stop, width, height, is_horizontal)

    return result
def makeGrad(width=800, height=600, col1=(0,0,0), col2=(255,255,255)):
    array = get_gradient_3d(width, height, col1, col2, (False, False, False))
    Image.fromarray(np.uint8(array)).save('assets/menu/menuBG.jpg', quality=100)