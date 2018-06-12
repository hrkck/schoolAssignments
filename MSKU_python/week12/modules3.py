import random as r
from math import sin as s, cos, pi

print(s(pi))
# since we renamed the imported function, we can't use the original name
# print(sin(pi))

print(r.randint(1,16))

def doSomething(x):
   import math
   return math.sqrt(x)
   
   
print(doSomething(100))
math.sqrt(100)
