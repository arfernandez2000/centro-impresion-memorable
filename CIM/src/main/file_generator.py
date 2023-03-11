import random

static_file = open("./resources/input_static.txt", "r")

N = int(static_file.readline())
L = int(static_file.readline())

static_file.close()

f = open("generated_dynamic.txt", "w")

f.write("0\n")

for i in range(L):
    x = random.uniform(0, L)
    y = random.uniform(0, L)
    f.write(str(x) + " " + str(y) + "\n")

f.close()