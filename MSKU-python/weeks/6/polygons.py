import turtle
import math

wn = turtle.Screen()             # Set up the window and its attributes
wn.bgcolor("lightgreen")

alex = turtle.Turtle()           # create alex

"""
for i in range(3):
   alex.forward(80)
   alex.left(120)

for i in range(4):
   alex.forward(80)
   alex.left(90)

n = 5
r = 100
for i in range(n):
   angle = 360 / n
   alex.forward(r * math.sin(math.radians(angle)))
   alex.left(angle)

n = 8
r = 100
for i in range(n):
   angle = 360 / n
   alex.forward(r * math.sin(math.radians(angle)))
   alex.left(angle)

n = 20
r = 100
for i in range(n):
   angle = 360 / n
   alex.forward(r * math.sin(math.radians(angle)))
   alex.left(angle)
"""
alex.penup()
alex.setx(-200)
alex.sety(-50)
alex.pendown()
r = 200
for n in [5,8,20, 3, 120]:
    for i in range(n):
       angle = 360 / n
       alex.forward(r * math.sin(math.radians(angle / 2)))
       alex.left(angle)

wn.exitonclick()
