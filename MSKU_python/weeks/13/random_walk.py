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

def print_scores():
  for turtle_dict in game_turtles:  
     print("{} ({}) = {}".format(turtle_dict["name"], turtle_dict["color"], turtle_dict["score"]))
    
def random_walk(turtle_dict):
  angle = random.randint(-turtle_dict["turn_angle"],turtle_dict["turn_angle"])
  a_turtle = turtle_dict["turtle_obj"]
  a_turtle.left(angle)
  a_turtle.forward(turtle_dict["speed"])
  if not isInScreen(wn, a_turtle):
     a_turtle.left(180)
     a_turtle.forward(turtle_dict["speed"])
  x1 = a_turtle.xcor()   
  y1 = a_turtle.ycor()
  for (treasure_x, treasure_y) in treasure_coords:
     if distance(x1, y1, treasure_x, treasure_y) < treasure_radius:
        turtle_dict['score'] += 10
        print("SCORE ", turtle_dict['name']) 
        print_scores()
     if (turtle_dict['score'] == 100):
        return True
  return False        
     
wn = turtle.Screen()             # Set up the window and its attributes
wn.bgcolor("lightgreen")

game_turtles = []

for (name, color) in [("Alex", "red"), ("Tess", "blue"), 
                      ("Bob", "green"), ("Cindy", "orange"),
                      ("David", "pink"), ("Elizabeth", "brown")
                      ]:
   new_turtle = {'name': name, 'color': color}
   new_turtle["score"] = 0
   new_turtle["turn_angle"] = random.randint(10,30)
   new_turtle["speed"] = random.randint(20,50)
   turtle_obj = turtle.Turtle() 
   turtle_obj.color("black", color)
   turtle_obj.pensize(1)
   turtle_obj.penup()  
   new_turtle["turtle_obj"] = turtle_obj   
   game_turtles.append(new_turtle)
   

treasure_radius = 15
drawer = turtle.Turtle()
drawer.shape("circle")
drawer.shapesize(0.25,0.25)
drawer.speed(0)
drawer.hideturtle()
# drawer.penup()
treasure_coords = []
for i in range(20):
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
    treasure_coords.append( (treasure_x, treasure_y) )

steps = 0           
while True:
   gameover = False
   for turtle_dict in game_turtles:   
       if random_walk(turtle_dict):
         print(turtle_dict["name"]," Won.")
         gameover = True
   if gameover: break
  


print_scores()
    
wn.exitonclick()
