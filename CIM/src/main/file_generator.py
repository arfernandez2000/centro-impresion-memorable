import random
import sys
import os

N = int(sys.argv[1])
L = int(sys.argv[2])
R = float(sys.argv[3])
path = sys.argv[4]

static_file = open(path + "test_static.txt", "w")

static_file.write(str(N) + "\n")
static_file.write(str(L) + "\n")
for i in range(N):
    static_file.write(str(R) + "\t" + "1.0" + "\n")

static_file.close()

f = open(path + "test_dynamic.txt", "w")

f.write("0\n")

for i in range(N):
    x = random.uniform(0, L)
    y = random.uniform(0, L)
    f.write(str(x) + " " + str(y) + "\n")

f.seek(f.tell() - 1, os.SEEK_SET)
f.write('')

f.close()