import turtle               # allows us to use the turtles library
import random

"""
N = 10000000 # total points

inside_points = 0
total_points = 0
for i in range(1,N+1):
  # total_points = total_points + 1
  # total_points = i    # i + 1  if you use range(N)
  x = random.uniform(-100,100)   # random.randint(-100,100)
  y = random.uniform(-100,100)
  dist_to_origin = (x ** 2 + y ** 2) ** 0.5
  if dist_to_origin < r:
     inside_points = inside_points + 1
  # print("PI = ", 4 * inside_points / total_points)

print("PI = ", 4 * inside_points / N)
"""  
  
r = 150
 
wn = turtle.Screen()        # creates a graphics window
wn.bgcolor("lightgreen")        # set the window background color

alex = turtle.Turtle()      # create a turtle named alex

writer = turtle.Turtle() 
writer.penup()
writer.goto(0,-200)
# writer.write("I calculate PI as 2.7", 
#             align="center", 
#             font=("Arial", 24, "normal"))
# alex.circle(r, 360, 8)
# alex.circle(100, 360, 12)
# alex.circle(100, steps=12)
alex.penup()
alex.shape("circle")
alex.fillcolor("red")
alex.shapesize(0.25, 0.25)
# alex.stamp()
alex.pencolor("blue")
alex.goto(0,-r/2)
alex.pendown()
alex.circle(r)
# alex.goto(-r,0)
alex.backward(r)
for i in range(4):
   alex.forward(2*r)
   alex.left(90)
   
N = 500 # total points

alex.speed(0)
inside_points = 0
total_points = 0
alex.penup()
writer.speed(5)
writer.hideturtle()
writer.write(" ")
for i in range(1,N+1):
  # total_points = total_points + 1
  # total_points = i    # i + 1  if you use range(N)
  x = random.uniform(-r,r)   # random.randint(-100,100)
  y = random.uniform(-r/2,1.5*r)
  dist_to_origin = ( (x ** 2) + ( (y - 0.5*r) ** 2)) ** 0.5
  if dist_to_origin < r:
     inside_points = inside_points + 1
     alex.fillcolor("green")
  else:                          # outside the circle
     alex.fillcolor("red")
  alex.goto(x,y)
  alex.stamp()  
  writer.undo()  
  writer.write("PI = " + str( round(4 * inside_points / i,4) ), 
             align="center", 
             font=("Courier", 24, "normal"))
  
        

print("PI = ", 4 * inside_points / N)
   
wn.exitonclick()                # wait for a user click on the canvas
print("End of program")

