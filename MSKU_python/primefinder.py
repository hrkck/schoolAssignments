# ##Hi Guys, Lets find some primes...
import time

# Main idea of this code is, when all the code runs top to down, ultimate value of "x" will determine if "number" is prime or not.
# ##dfdg


def primefinder(number):
    # # Be aware that divider starts at 1. Becasuse we assume the number itself is already counted.
    divider = 1
    list1 = []

    # Be aware that stop condition of for loop is half of the number. It is a
    # very fundamental rule of dividing.
    for i in range(1, int((number / 2) + 1), 1):

        if number % i == 0:
            divider = divider + 1
            list1.append(i)

        if divider > 2:
            print(list1, "[...]")
            print(number, "is NOT a prime number.\n")
            break

    if divider == 2:
        list1.append(number)  # Number itself is added to the list manually.
        print(list1)
        print(number, "is a prime number.\n")


y = 34568970897654324567
while True:

    primefinder(y)
    y = y + 1
