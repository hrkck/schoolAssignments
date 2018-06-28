import random

picked_number = random.randint(1,200)
guess = -1
guess_lower_bound = 1
guess_upper_bound = 200
guesses_made = 0
while (guess != picked_number and guesses_made < 5):
    guesses_made = guesses_made + 1
    print("Your "+ str(guesses_made) + ". guess")
    guess = int(input("Guess a number between "     \
                 + str(guess_lower_bound) + "-"     \
                 + str(guess_upper_bound) + ": "))
    if guess < guess_lower_bound or guess > guess_upper_bound:
      print("I said between " + str(guess_lower_bound) + "-"   \
             + str(guess_upper_bound) + "...")
    else:
        if (guess == picked_number):
           print("YOU WON.")
        else:
           if (guess <= picked_number):
              print("Your guess is LESS than my number")
              guess_lower_bound = guess + 1
           else:
              print("Your guess is GREATER than my number")
              guess_upper_bound = guess - 1
              


print("My number was",picked_number)
print("End of program....")
