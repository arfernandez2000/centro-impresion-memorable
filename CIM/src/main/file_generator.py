import random
import sys

N = int(sys.argv[1])
L = int(sys.argv[2])
R = float(sys.argv[3])

static_file = open("generated_static.txt", "w")

static_file.write(str(N) + "\n")
static_file.write(str(L) + "\n")
for i in range(N):
    static_file.write(str(R) + "\t" + "1.0" + "\n")

static_file.close()

f = open("generated_dynamic.txt", "w")

f.write("0\n")

for i in range(L):
    x = random.uniform(0, L)
    y = random.uniform(0, L)
    f.write(str(x) + " " + str(y) + "\n")

f.close()