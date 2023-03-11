import sys
import numpy as np
import matplotlib.pyplot as plt
from numpy import pi

x = []
y = []

particle_index = int(sys.argv[1])

with open('./resources/input_static.txt') as static:
    s_lines = static.readlines()

N = int(s_lines.pop(0))
L = s_lines.pop(1)

r = float(s_lines.pop().split()[0])
print(r)

s = np.full(
  shape=N,
  fill_value=r*4,
  dtype=np.float32
).tolist()

with open('./resources/input_dynamic.txt') as dynamic:
    d_lines = dynamic.readlines()
d_lines.pop(0)


with open('./resources/output.txt') as output:
    o_lines = output.readlines()

for line in d_lines:
    x.append(float(line.split()[0]))
    y.append(float(line.split()[1]))

# plt.plot(x, y, 'o')
#

colors = []
for i in range(N):
    colors.append((0,0,0))

particle = o_lines[particle_index]
neighbours = particle[2:].split(",")
colors[particle_index] = (0,1,0)
for index in neighbours:
    colors[int(index)] = (0,0,1)

ax1 = plt.subplot(212)
ax1.margins(0.05)           # Default margin is 0.05, value 0 means fit
ax1.scatter(x, y, s= pi * r **2, alpha=0.5, c=colors)

# ax3 = plt.subplot(222)
# ax3.margins(x=0, y=-0.45)   # Values in (-0.5, 0.0) zooms in to center
# ax3.scatter(x, y, s= pi * r **2, alpha=0.5)
# ax3.set_title('Zoomed in')
plt.show()