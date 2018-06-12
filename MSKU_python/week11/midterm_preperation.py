"""
In the exam, for most of the questions you will put yourself into the shoes of
Phyton interpreter (i.e. evaluate the expressions, keep the values of the
variables, determine the flow based on conditional and loop statements) and
write the output of the given code. After you come up with an answer by paper
and pencil, run the actual code and see if you were correct. If you weren't,
think once more about the the given code.

I am supplying the example questions as functions so you can test them
individually.
"""

def q1():
    """Write the output of the following code"""
    a = 0
    b = 0
    while (a+b < 20):
       a += 5
       print(a, b)
       b += 2

# to test your answer just uncomment the following or run the code once
# (so functions became defined and then call q1 at the interpreter
# q1()

def q2():
    """Write the output of the following code. Hint: there will be 8 lines."""
    i=1
    j=1
    for i in range(10,21):
       if (i % 3 == 0):  continue
       else: j = i + 7
       print(i, j)
       while (j >= i):
          if (j % 3 == 0): break
          elif (j % 2 == 0):
            j -= 3
            continue
          j -= 1

       print(i, j)
       if (i+j > 30): break

# q2()

def q3(a,b):
    """Give 8 different calls of this function (i.e. 8 different pair of values
       for a and b) where each call will print a different message. For each
       call also specify what will be the printed answer."""
    if (a+b > 10):
      if (a%2 == 0 or b%2 == 0): print("Answer1")
      elif (a+b < 20): print("Answer2")
      else: print("Answer3")
    else:
      if (a%2 == 0 and b%2 == 0): print("Answer4")
      elif (a%2 == 0): print("Answer5")
      elif (a == b):
        if (a <= 3): print("Answer6")
        else: print("Answer7")
      else: print("Answer8")

def q4():
   """Convert the following while loop to a corresponding for loop.
      Hint: You need to use the break statement."""
   i=3
   answer = 0
   while i < 20 and answer != i:
      print(i)
      i += 5
      answer = int(input("Enter some number:"))

def q5():
    """Write the output of the following code."""
    (a,b,c)=(2,1,3)
    a = b * 10
    b = a - 4
    c = a + b
    b = 8
    a = c - (b - a)
    print(a,b,c)
    b = c
    c = a
    a = b
    b *= 2
    a -= 10
    print("{2} {0} {1}".format(a**2, b // 10 , c));
    
# q5()    

def q6():
    """Write the output of the following code."""
    list1 = [ 7,  6,  5,  4,  3, 2,  1 ]
    list2 = [ 11, 4, 13, 12, 15, 9, 17 ]
    for i in range(0,7):
      for j in range(i,7):
        if((list1[i]+list2[j]) % 4 == 0):
          print(list1[i], list2[j])

# q6()

def q7():
    """Write the output of the following code."""
    list1 = [ 2, 7, 4, 1, 8 ];
    list2 = [ 1, 2, 3, 11, 12, 13, 21, 22, 23, 24 ]
    total = 0;
    for i in list1:
      total += list2[i-1];
    print(total);

def q8():
    """Write the output of the following code."""
    a = ( [1,2,3,4,5], 456, "Hello", 123)
    print(a[0][2], a[2][2:], a[1] + a[-1])

def q9():
    """Write the output of the following code."""
    str = "01234567"
    print(str[2:5], str[:4], str[4:], str[-3], str[2:-2], str[-5:-2])

def q10():
    """Write the output of the following code."""
    str1 = "Python rocks"
    str2 = "Oh my God, Brad Pitt."
    str3 = "012345678901234567890"
    for c in str1:
        i = str2.find(c)
        if (i > 0): print(i, end=":")

q10()

def q11():
    """Write the output of the following code."""
    str = "How to think like a computer scientist"
    result = "--"
    for w in str.split():
        if w[0] in "cost": result += w[:2] + "--"
    print(result)

def f(a,b):
   """To be used in q12"""
   result = a
   for i in range(abs(b)):
     if (b > 0): result += 1
     else: result -= 1
   return result;

def q12():
    """First examine the function f above and try to understand how it
       computes its result. Then give the output of the following code."""
    print(10*f(9, -4))
    a = 3
    b = f(a, 5)
    print(f(2*a, a+b));
    x = 4
    y = 1 - x
    x = 7
    print(f(f(x, 2), f(-10, y)));

# q12()

def q13(alex):
    """Assuming parameter alex is a turtle, what will the below code draw?
       Your drawing can be approximate.
    """
    a = 30
    (x,y) = alex.pos()
    alex.forward(a)
    alex.back(a)
    alex.left(90)
    alex.forward(a*2)
    alex.right(90)
    alex.forward(a)
    alex.penup()
    alex.goto(x,y+a)
    alex.pendown()
    alex.forward(a/2)
    alex.hideturtle()

def q14(alex):
    """Assuming parameter alex is a turtle, what will the below code draw?
       Your drawing can be approximate.
    """
    a = 80
    for i in range(10):
       alex.forward(a)
       if i%2 == 0: alex.left(90)
       else: alex.right(90)
       a = a - 5
       
"""
import turtle
wn = turtle.Screen()
tess = turtle.Turtle() 
q13(tess)
wn.exitonclick()
"""       

