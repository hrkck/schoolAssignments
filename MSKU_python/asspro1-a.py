# Hakkı Rıza Küçük 150 709 006


x = 2  # Stuff are defined...
y = 3
z = 4

inf = 2
first = 1

pisayisi = 3.  # ...Until this line of code.


def artidort(a, b, c):  # The step of 'adding' is defined.
    # This "payda" will be a topic to discuss below. Keep it in mind.
    payda = a * b * c
    return +4 / payda


def eksidort(a, b, c):  # The step of 'subtraction' is defined.
    # This "payda" will be a topic to discuss below. Keep it in mind.
    payda = a * b * c
    return -4 / payda


# This 'while loop' tells python when to stop iteration.
while artidort(x, y, z) > 0.0000001 or eksidort(x, y, z) > 0.0000001:

    # This 'for loop' tells python which function will be executed where.
    for yaptimlansonunda in range(first, inf):

        # Respect to this 'if', "kilkuyruk" will be attached to one of these
        # functions order by order.
        if first % 2 == 1:
            kilkuyruk = artidort(x, y, z)
        elif first % 2 == 0:
            kilkuyruk = eksidort(x, y, z)

        # These two attachments will update themselves over time. Therefore,
        # termination of the loop will not
        # ##depend on 'for loop'. Termination of the loop will always depend on
        # 'while loop'.
        first = first + 1
        inf = inf + 1

    # Probably it will not be wrong to say "this is the bleeding-edge of the
    # entire program".
    pisayisi = pisayisi + kilkuyruk
    # Because, that line updates 'pisayisi' over time and makes it closer to
    # the desired result.

    # Another important point is here: These three lines of code update the
    # value of "x,y,z" over time and makes "payda"
    # bigger. When "payda" gets bigger, the returns of the functions get
    # closer to zero(0). Therefore, 'while loop' will meet                 the
    # conditions to terminate itself."
    x = x + 2
    # Beside termination of 'while loop', increasing of "x,y,z" is also
    # essential to represent the formula to calculate
    # number Pi.
    y = y + 2
    z = z + 2
    # This line prints the calculation of "pisayisi" each time.
    print(pisayisi)

# And here is the ultimate consequence. 'while loop' updates the value of
# "pisayisi" each time it loops. Finally, "pisayisi" is no longer attached
# to value "3". At the end of loop, it is completly different, which is
# actually the value of real Pi, which is defined by mathematicians. The
# mathematicians who did not have python but their hardwork and ambition
# to explain the nature surrounds them...
print("At last, approximate value of number Pi is:\n", pisayisi)
