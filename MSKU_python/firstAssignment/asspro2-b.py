# Hakkı Rıza Küçük 150 709 006

import turtle  # turtle module is imported.
import random  # random module is imported.
wn = turtle.Screen()
turtle.bgcolor("yellowgreen")  # screen is loaded from turtle module.
hakki = turtle.Turtle()  # turtle is loaded from turtle module.
hakki.up()  # his pen is up to move back into the screen clearly.
hakki.goto(-150, 0)  # he goes back into the screen.
hakki.down()  # the pen is down again for future work.


# the function of the basic movement is defined...
def hareket(rengi, boyutu, sayi):
    hakki.color(rengi)
    hakki.left(45)
    hakki.forward(boyutu)
    hakki.write(sayi)
    hakki.right(90)
    hakki.forward(boyutu)
    hakki.left(45)  # ...until this line of code.

for sayi in range(1, 10):  # the loop for nine steps.
    # to draw random mountains, a randomly evaluated "x" is created.
    x = random.randrange(1, 3)
    if x is 1:  # respect to the value of "x", the color and lenght of the mountains are CHOOSED.
        rengi = "blue"
    else:
        rengi = "red"

    if x is 1:
        boyutu = 40
    else:
        boyutu = 20

    # finally, it is time to recall the function. this time parameters are
    # defined.
    hareket(rengi, boyutu, sayi)


# LAST IMPORTANT POINT: the "for" loop also includes the "hareket"
# function, which holds the algorithm of the one-basic-step-movement.
# Whenever the function comes to an end, "for" loop starts again,
# everytime picking up "1" or "2" randomly. this is what makes the
# mountains random.

wn.exitonclick()
