import turtle

fileref = open("ranks_table.txt", "r")
lines = fileref.readlines()
fileref.close()

teams = {}

for (i,line) in enumerate(lines):
    if i==0: continue
    line = line.strip()
    values = line.split("\t")
    team_name = values[0]
    teams[team_name] = values[1:17]

print(teams) 
   
wn = turtle.Screen()             # Set up the window and its attributes
wn.bgcolor("lightgreen")

drawer = turtle.Turtle()           # create tess and set some attributes
drawer.color("blue")
drawer.pensize(2)


x0 = -350
y0 = -240
dx = 42
dy = 30
colors = ["red", "green", "blue", "orange"]

i = 0
for (name,ranks) in teams.items():
   drawer.color(colors[i%4])
   i += 1
   drawer.penup()    
   drawer.goto(x0,y0 + (18-int(ranks[3])) * dy)
   drawer.pendown()
   for rank in ranks[4:]:
     (x,y) = drawer.pos()
     x += dx
     y = y0 + (18-int(rank)) * dy  
     drawer.goto(x,y)
   drawer.color("black")  
   drawer.write(name)  

wn.exitonclick()   
