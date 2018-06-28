import turtle
import random

def distance(x1, y1, x2, y2):
    dx = x2 - x1
    dy = y2 - y1
    dsquared = dx**2 + dy**2
    result = dsquared**0.5
    return result
    
def isInScreen(wn,t):
    leftBound = -(wn.window_width() / 2)
    rightBound = wn.window_width() / 2
    topBound = wn.window_height() / 2
    bottomBound = -(wn.window_height() / 2)

    turtleX = t.xcor()
    turtleY = t.ycor()

    stillIn = True
    if turtleX > rightBound or turtleX < leftBound:
        stillIn = False
    if turtleY > topBound or turtleY < bottomBound:
        stillIn = False

    return stillIn
    
def random_walk(a_turtle, speed, angle_range):
  angle = random.randint(-angle_range,angle_range)
  a_turtle.left(angle)
  a_turtle.forward(speed)
  if not isInScreen(wn, a_turtle):
     a_turtle.left(180)
     a_turtle.forward(speed)
  x1 = a_turtle.xcor()   
  y1 = a_turtle.ycor()
  if distance(x1, y1, treasure_x, treasure_y) < treasure_radius:
     return True  # game is won. treasure found
  else:
     return False # game continues  
     
wn = turtle.Screen()             # Set up the window and its attributes
wn.bgcolor("lightgreen")

tess = turtle.Turtle()           # create tess and set some attributes
tess.color("blue")
tess.pensize(1)
tess.penup()

alex = turtle.Turtle()           # create tess and set some attributes
alex.color("red")
alex.pensize(1)
alex.penup()

treasure_radius = 40
drawer = turtle.Turtle()
drawer.shape("circle")
drawer.shapesize(0.25,0.25)
# drawer.penup()
treasure_x = random.randint(-(wn.window_width() / 2),(wn.window_width()/2))
treasure_y = random.randint(-(wn.window_height() / 2),(wn.window_height()/2))
drawer.pu()
drawer.goto(treasure_x, treasure_y)
drawer.stamp()
drawer.right(90)
drawer.forward(treasure_radius)
drawer.pd()
drawer.left(90)
drawer.circle(treasure_radius,360)
drawer.hideturtle()
           
while True:
   if random_walk(tess, 10, 20):
     print("Tess Won.")
     break
   if random_walk(alex, 20, 5):
     print("Alex Won.")
     break   

wn.exitonclick()
