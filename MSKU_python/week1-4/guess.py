import random

picked_number = random.randint(1,100)
guess = -1

while (guess != picked_number):
    guess = int(input("Guess a number between 1-100: "))
    if (guess == picked_number):
       print("YOU WON.")
    else:
       if (guess <= picked_number):
          print("Your guess is LESS than my number")
       else:
          print("Your guess is GREATER than my number")


print("My number was",picked_number)
print("End of program")
